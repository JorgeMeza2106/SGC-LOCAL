package pe.com.fisi.cenpro.sigeco.mgc.controller;

import static pe.com.fisi.cenpro.sigeco.mgc.utils.Constantes.SPLIT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsignacionForm;
import pe.com.fisi.cenpro.sigeco.mgc.controller.form.CitaForm;
import pe.com.fisi.cenpro.sigeco.mgc.controller.form.HistoriaClinicaForm;
import pe.com.fisi.cenpro.sigeco.mgc.services.AlumnoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.AsignacionService;
import pe.com.fisi.cenpro.sigeco.mgc.services.CitaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.ClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.CursoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.DoctorService;
import pe.com.fisi.cenpro.sigeco.mgc.services.HistoriaClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.HorarioClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.UbigeoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AlumnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaParametrosBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CursoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.DoctorBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.EstadoAsignacion;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UbigeoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.impl.UtilPoiService;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Constantes;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensaje;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes;

/*LA GENERACION DE DAOS Y SERVICES BASE DEBERIA SER GESTIONADA POR MEDIO DE LA PERSONALZIACION DE ECPLISE 
 * NO HACERLO MANUALMENTE*/
@Controller
@SessionAttributes("citas")
public class AdminController {

	@Autowired
	private UtilPoiService util;

	@Autowired
	private CitaService citaService;
	@Autowired
	private ClinicaService clinicaService;
	@Autowired
	private CursoService cursoService;
	@Autowired
	private AsignacionService asignacionService;
	@Autowired
	private HorarioClinicaService horarioClinicaService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private HistoriaClinicaService historiaClinicaService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private UbigeoService ubigeoService;

	@ModelAttribute("estadosAsignacion")
	public List<EstadoAsignacion> estadoAsignacion() {
		return Arrays.asList(EstadoAsignacion.ALL);
	}

	@ModelAttribute("estadosAsignacionNoArchivada")
	public List<EstadoAsignacion> estadoAsignacionNoArchivada() {
		return Arrays.asList(EstadoAsignacion.NO_ARCHIVADA);
	}

	/**********************************************************************************/
	/* ADMINISTRACION DE CITAS */
	/**********************************************************************************/

	@RequestMapping(value = "/admin_cc/citas/registrarAdicional", method = RequestMethod.GET)
	public String registrarCita(Model modelo) {
		modelo.addAttribute("resultadoVacio", true);
		return "adminAlumnos";
	}

	// Con spring data
	@RequestMapping(value = "/admin_cc/citas/buscarAsignacionesDeAlumno", method = RequestMethod.GET)
	public String listarAsignacionesDeAlumno(Model model, @RequestParam("dniAlumno") String dniAlumno) {

		if (alumnoService.existe(dniAlumno)) {

			model.addAttribute("listaAsignaciones", asignacionService.listarAsignacionesPorDniAlumno(dniAlumno));
			model.addAttribute("clinicas", new ArrayList<ClinicaBO>());
			model.addAttribute("horarios", horarioClinicaService.obtenerTodosHorarios());
			model.addAttribute("citaForm", new CitaForm());

			AlumnoBO alumnoBO = alumnoService.obtenerAlumnoConCursos(dniAlumno);
			model.addAttribute("alumnoBO", alumnoBO);
			model.addAttribute("resultadoVacio", false);
		} else {
			model.addAttribute("lstErrores", "El dni del alumno no es válido");
			model.addAttribute("resultadoVacio", true);
		}
		return "adminAlumnos";
	}

	// spring dateado
	@RequestMapping(value = "/admin_cc/citas/registrar")
	public @ResponseBody Mensaje registrarCita(Model modelo, @Valid CitaForm citaForm, BindingResult br) {
		if (!br.hasErrors()) {
			String mensaje = citaService.registrarCita(citaForm, citaForm.getDniAlumno(), false);
			modelo.addAttribute("mensaje", mensaje);
			if (mensaje.equals(Mensajes.M_CITA_CORRECTA) || mensaje
					.equals(Mensajes.M_CITA_ADICIONAL_CORRECTA + " \n" + Mensajes.M_ADVERTENCIA_CITAS_NO_VALIDADA)) {
				return new Mensaje(Constantes.OK, mensaje);
			} else {
				return new Mensaje(Constantes.ERROR, mensaje);
			}
		}
		return new Mensaje(Constantes.ERROR, "hay errores");
	}

	// con spring data
	@RequestMapping(value = { "/admin_cc/citas/listar", "/admin_oa/citas/listar" })
	public String Adminlistar(Model model, HttpSession session, Authentication authentication) {
		CitaBO citaBo = new CitaBO();
		citaBo.setFechaAtencion(new Date());
		citaBo.setIsRecita(false);

		List<CitaBO> listCitaBo = citaService.listarCitasPorBo(citaBo);
		System.out.println("tamaño: " + listCitaBo.size());
		List<ClinicaBO> listClinicaBo = clinicaService.findAll();
		List<CursoBO> listCursoBo = cursoService.findAll();

		String adminType = null;
		boolean hasCCrRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN_CC"));
		adminType = (hasCCrRole) ? "/admin_cc" : "/admin_oa";
		model.addAttribute("adminType", adminType);
		model.addAttribute("citas", listCitaBo);
		model.addAttribute("clinicas", listClinicaBo);
		model.addAttribute("cursos", listCursoBo);
		model.addAttribute("consultaCitas", new CitaBO());

		session.setAttribute("turno", "MAÑANA - TARDE");

		return "adminCitas";
	}

	// con spring data
	@RequestMapping(value = { "/admin_cc/citas/buscar", "/admin_oa/citas/buscar" }, method = RequestMethod.GET)
	public String adminFiltrarCitasPorCriterios(Model model, @ModelAttribute("consultaCitas") CitaBO consultaCitas,
			BindingResult bindingResult, HttpSession session) {
		List<CitaBO> listaCitasFiltradasPorCriterio = citaService.listarCitasPorBo(consultaCitas);
		model.addAttribute("citas", listaCitasFiltradasPorCriterio);
		return "/template/fragments/fragmentosCita :: resultadoBusquedaCitas";
	}

	// no necesita sprin data
	@RequestMapping(value = "/admin_cc/citas/exportar", method = RequestMethod.POST)
	public ModelAndView generarExcel(@ModelAttribute("citas") List<CitaBO> citas,
			@ModelAttribute CitaParametrosBO parametros, ModelMap modelMap, HttpSession session,
			ModelAndView modelAndView) {

		if (citas.size()==0){
			System.out.println("Esta vacio");
			CitaBO vacio = new CitaBO();
			vacio.setNumeroHC(0);
			citas.add(vacio);
		}
		modelMap.put("datasource", new JRBeanCollectionDataSource(citas));
		modelMap.put("fecha_excel", parametros.getPFechaAtencion());
		modelMap.put("turno_excel", (parametros.getPTurno() != null)
				? (!parametros.getPTurno().equals("Seleccione turno")) ? parametros.getPTurno() : "-" : "-");
		modelMap.put("clinica", (parametros.getClinica() != null)
				? (!parametros.getClinica().equals("Seleccione clinica")) ? parametros.getClinica() : "-" : "-");

		modelMap.put("format", "xlsx");
		modelAndView = new ModelAndView("rpt_Citas", modelMap);
		return modelAndView;
	}

	// Nadie lo usa
	// springdateado
	@RequestMapping("/admin_cc/recitas/listar")
	public String adminListarRecitas(Model model) {
		CitaBO citaBo = new CitaBO();
		citaBo.setIsRecita(true);

		List<CitaBO> regCitas = citaService.listarCitasPorBo(citaBo);
		List<ClinicaBO> listClinicaBo = clinicaService.findAll();
		model.addAttribute("clinicas", listClinicaBo);
		model.addAttribute("citas", regCitas);
		return "adminRecitas";
	}

	// springdateado
	@RequestMapping(value = "/admin_cc/historias/importarExcel", params = "action=procesar")
	public String adminImportar(Model model, @RequestParam("fichero") MultipartFile uploaded) {

		List<HistoriaClinicaForm> pacientes = null;
		List<Mensaje> mensajes = new ArrayList<>();
		try {
			util.importar(uploaded);
			pacientes = util.procesar();
			boolean validado = false;
			System.out.println("VALOR DE BOOLEAN: " + validado);
			System.out.println("VALOR ERROR UTILPOI: " + util.error);
			if (util.error || util.errorCab) {
				if (util.error) {
					model.addAttribute("errorCargar", true);
				} else {
					model.addAttribute("errorCab", true);
				}
				model.addAttribute("error", false);
				model.addAttribute("mensajeCargar", util.getMensajeError());
				model.addAttribute("mostrar", "inline-block");
				model.addAttribute("pacienteError", util.getNombre());
				model.addAttribute("confirmar", "true");
			} else {

				validado = historiaClinicaService.validarDatos(util.getListaForms(), mensajes);
				if (validado) {
					model.addAttribute("datos", pacientes);
					model.addAttribute("confirmar", "false");
					model.addAttribute("errorCargar", false);
					model.addAttribute("errorCab", false);
					model.addAttribute("error", false);
					model.addAttribute("mensaje", "Se cargo correctamente el excel");
				} else {
					List<HistoriaClinicaForm> pacientesErrados = new ArrayList<>();
					List<String> errores = new ArrayList<>();
					System.out.println("ENTRE A PACIENTES ERRADOS");
					for (Mensaje mensaje : mensajes) {
						pacientesErrados.add((HistoriaClinicaForm) mensaje.getResultado());
						errores.add(mensaje.getDescripcion());
						System.out.println("MENSAJE DEL CONTROLLER: " + mensaje.getDescripcion());
					}
					model.addAttribute("confirmar", "true");
					model.addAttribute("errorIE", true);
					model.addAttribute("mensaje", "Corrija los errores de las siguientes filas");
					model.addAttribute("datos", pacientesErrados);
					model.addAttribute("errores", errores);
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
			model.addAttribute("errorCargar", true);
			model.addAttribute("confirmar", "false");
		}
		model.addAttribute("mensajeIBD", false);
		model.addAttribute("procesar", "true");
		return "adminHistorias";
	}

	// springdateado
	@RequestMapping(value = "/admin_cc/historias/importarExcel", params = "action=confirmar")
	public String adminAgregar(Model model) {
		Mensaje mensaje = new Mensaje();

		List<HistoriaClinicaForm> pacientesAgregados = historiaClinicaService
				.agregarHistoriasImportadas(util.getListaForms(), mensaje);

		model.addAttribute("mensajeIBD", true);
		model.addAttribute("errorIE", false);
		model.addAttribute("errorCargar", false);
		model.addAttribute("errorCab", false);
		model.addAttribute("datos", pacientesAgregados);
		model.addAttribute("mensaje", mensaje.getDescripcion());
		model.addAttribute("confirmar", "true");
		model.addAttribute("procesar", "true");
		return "adminHistorias";
	}

	@RequestMapping("/admin_cc/historias/importar")
	public String adminImportar(Model model) {
		model.addAttribute("mensajeIBD", null);
		model.addAttribute("errorIE", null);
		model.addAttribute("errorCargar", null);
		model.addAttribute("errorCab", null);
		model.addAttribute("confirmar", "true");
		model.addAttribute("procesar", "true");
		return "adminHistorias";
	}

	/*******************************************************/
	/******* ADMINISTRACION DE HISTORIAS CLINICAS **********/
	/*******************************************************/

	// con spring data
	@RequestMapping(value = "/admin_cc/historias/registrarla", method = RequestMethod.POST)
	public String registrarHC(Model model, @Valid @ModelAttribute("historiaClinicaForm") HistoriaClinicaForm HCForm,
			BindingResult br) {
		System.out.println("Ubigeo nacimiento: " + HCForm.getDatosPersonaBO().getUbigeoNacimiento());
		System.out.println("Ubigeo domicilio: " + HCForm.getDatosPersonaBO().getUbigeoProcedencia());
		if (br.hasErrors()) {
			List<ObjectError> erro = br.getAllErrors();
			for (ObjectError objectError : erro) {
				System.out.println(objectError.getDefaultMessage());
				cargarDepartamentos(model);
			}
			return "adminRegistrarHistoria";
		} else {
			List<String> lstErrores = historiaClinicaService.registrarHistoriaClinica(HCForm);
			if (lstErrores != null && lstErrores.isEmpty()) {
				System.out.println("ENTRE 2");
				return "redirect:/admin_cc/historias/listar/";
			} else {
				System.out.println("ENTRE 3");
				model.addAttribute("lstErrores", lstErrores);
				return "adminRegistrarHistoria";
			}
		}
	}

	@RequestMapping(value = "/admin_cc/historias/registrar", method = RequestMethod.GET)
	public String registrarHClinica(Model model) {

		cargarDepartamentos(model);
		model.addAttribute("historiaClinicaForm", new HistoriaClinicaForm());

		return "adminRegistrarHistoria";
	}

	@RequestMapping(value = { "/admin_cc/historias/registrar/provincias", "/admin_cc/historias/editar/provincias",
			"/admin_cc/historias/provincias" }, method = RequestMethod.GET)
	@ResponseBody
	public List<UbigeoBO> obtenerProvincias(Model model, @ModelAttribute("idDepartamento") String idDepa) {
		// model.addAttribute("provincias",
		// ubigeoService.obtenerProvinciasPorDepa(idDepa));
		model.addAttribute("distritos", new ArrayList<UbigeoBO>());

		model.addAttribute("historiaClinicaForm", new HistoriaClinicaForm());

		return ubigeoService.obtenerProvinciasPorDepa(idDepa);
	}

	@RequestMapping(value = { "/admin_cc/historias/registrar/distritos", "/admin_cc/historias/editar/distritos",
			"/admin_cc/historias/distritos" }, method = RequestMethod.GET)
	@ResponseBody
	public List<UbigeoBO> obtenerDistrito(Model model, @ModelAttribute("idDepartamento") String idDepa,
			@ModelAttribute("idProvincia") String idProvincia) {
		model.addAttribute("historiaClinicaForm", new HistoriaClinicaForm());
		return ubigeoService.obtenerDistritosPorDepaProvincia(idDepa, idProvincia);
	}

	// springdateado -- modificando ...
	@RequestMapping(value = { "/admin_cc/historias/modificar",
			"/admin_ad/historias/modificar" }, method = RequestMethod.POST)
	public String modificarHClinica(Model model, Authentication authentication,
			@Valid @ModelAttribute("historiaClinicaDetalle") HistoriaClinicaBO hcbo, BindingResult br) {

		boolean hasCCrRole = authentication.getAuthorities().stream()
				.anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN_CC"));
		String adminType = (hasCCrRole) ? "/admin_cc" : "/admin_ad";

		if (br.hasErrors()) {
			List<ObjectError> erro = br.getAllErrors();
			for (ObjectError objectError : erro) {
				System.out.println(objectError.getDefaultMessage());
				cargarUbigeo(model, hcbo);
				model.addAttribute("error", true);
			}
			model.addAttribute("adminType", adminType);
			return "adminDetalleHistoria";
		} else {
			HistoriaClinicaForm hcform = new HistoriaClinicaForm();
			hcform.setDatosPersonaBO(hcbo.getDatosAdicionalesPaciente());
			hcform.setHistoriaClinicaGeneralBO(hcbo.getDatosGeneralesPaciente());
			historiaClinicaService.modificarHistoriaClinica(hcform);

			return "redirect:" + adminType + "/historias/editar/" + hcbo.getDatosGeneralesPaciente().getNroHC();
		}
	}

	// Con spring data
	@RequestMapping(value = "/admin_cc/historias/listar/", method = RequestMethod.GET)
	public String listarHistorias(Model modelo) {
		List<HistoriaClinicaGeneralBO> listHistoriaClinicaBo = historiaClinicaService.obtenerTodasHistoriasclinicas();
		modelo.addAttribute("listaHistorias", listHistoriaClinicaBo);
		modelo.addAttribute("asignacionForm", new AsignacionForm());
		return "adminListarHistorias";
	}

	// Springadateado
	@RequestMapping(value = "/admin_cc/historias/editar/{nroHC}", method = RequestMethod.GET)
	public String editarHistoria(@PathVariable Integer nroHC, Model modelo) {
		System.out.println(nroHC);
		HistoriaClinicaBO hcBO = historiaClinicaService.obtnerHistoriaClinica(nroHC);
		cargarDepartamentos(modelo);
		modelo.addAttribute("adminType", "/admin_cc");
		modelo.addAttribute("historiaClinicaDetalle", hcBO);
		modelo.addAttribute("asignacionForm", new AsignacionForm());

		modelo.addAttribute("departamentos", ubigeoService.obtenerDepartamentos());
		modelo.addAttribute("error", false);
		cargarUbigeo(modelo, hcBO);

		return "adminDetalleHistoria";
	}

	/***************************/
	/*** GESTIÓN DE ALUMNOS ****/
	/***************************/

	// springdateado
	@RequestMapping(value = { "/admin_cc/historias/editar/obtenerNombreCompletoAlumnoPorCodigo",
			"/admin_oa/historias/listar/obtenerNombreCompletoAlumnoPorCodigo" }, method = RequestMethod.GET)
	public @ResponseBody String obtenerNombreCompletoPorCodigoAlumno(Model modelo,
			@RequestParam("codigoAlumno") String codigoAlumno) {

		String nombreCompletoAlumno = alumnoService.obtenerNombreCompletoPorCodigo(codigoAlumno);
		if (nombreCompletoAlumno == null) {
			nombreCompletoAlumno = "No se encontró un alumno con el código indicado.";
		}

		modelo.addAttribute("nombreCompletoAlumno", nombreCompletoAlumno);
		return nombreCompletoAlumno;
	}

	/***************************/
	/*** GESTIÓN DE DOCTORES ****/
	/***************************/

	// con spring data
	@RequestMapping(value = { "/admin_cc/historias/editar/obtenerCodigoDoctorPorApellidoPaterno",
			"/admin_cc/historias/listar/obtenerCodigoDoctorPorApellidoPaterno" }, method = RequestMethod.GET)
	public String obtenerCodigoDoctorPorApellidoPaterno(Model modelo,
			@RequestParam("apellidoPaterno") String apellidoPaterno) {

		List<DoctorBO> doctorListBO = doctorService.obtenerTodosDoctoresPorPaterno(apellidoPaterno);
		modelo.addAttribute("listaDoctores", doctorListBO);
		return "/template/fragments/fragmentosCita :: resultadosBusquedaDoctor";
	}

	/****** METODO DE LEONARDO PARA OBTENER DOCTORES ****/
	// Con spring dota
	@RequestMapping(value = { "/admin_cc/historias/editar/obtenerDoctores",
			"/admin_od/historias/listar/obtenerDoctores" }, method = RequestMethod.GET)
	public @ResponseBody String obtenerDoctores(Model modelo) {

		List<DoctorBO> doctorListBO = doctorService.obtenerTodosDoctores();

		String json = "";
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

			json = ow.writeValueAsString(doctorListBO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/****************************************************/

	private void cargarDepartamentos(Model model) {
		model.addAttribute("departamentos", ubigeoService.obtenerDepartamentos());
		model.addAttribute("provincias", new ArrayList<UbigeoBO>());
		model.addAttribute("distritos", new ArrayList<UbigeoBO>());
	}

	private void cargarUbigeo(Model modelo, HistoriaClinicaBO hcBO) {
		cargarDepartamentos(modelo);
		if (hcBO.getDatosAdicionalesPaciente().getUbigeoNacimiento() != null) {
			if (!hcBO.getDatosAdicionalesPaciente().getUbigeoNacimiento().equals("-1")) {
				String ubiNac[] = hcBO.getDatosAdicionalesPaciente().getUbigeoNacimiento().split(SPLIT);
				System.out.println("ubigeo nac: " + hcBO.getDatosAdicionalesPaciente().getUbigeoNacimiento());
				modelo.addAttribute("provinciasNac", ubigeoService.obtenerProvinciasPorDepa(ubiNac[0]));
				modelo.addAttribute("distritosNac",
						ubigeoService.obtenerDistritosPorDepaProvincia(ubiNac[0], ubiNac[1]));
			}

		}
		if (hcBO.getDatosAdicionalesPaciente().getUbigeoProcedencia() != null) {
			if (!hcBO.getDatosAdicionalesPaciente().getUbigeoProcedencia().equals("-1")) {
				System.out.println(hcBO.getDatosAdicionalesPaciente().getUbigeoProcedencia());
				String ubiPro[] = hcBO.getDatosAdicionalesPaciente().getUbigeoProcedencia().split(SPLIT);
				modelo.addAttribute("provinciasPro", ubigeoService.obtenerProvinciasPorDepa(ubiPro[0]));
				modelo.addAttribute("distritosPro",
						ubigeoService.obtenerDistritosPorDepaProvincia(ubiPro[0], ubiPro[1]));
			}

		}
	}

}
