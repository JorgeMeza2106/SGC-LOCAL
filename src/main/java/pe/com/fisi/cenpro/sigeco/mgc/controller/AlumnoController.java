package pe.com.fisi.cenpro.sigeco.mgc.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.com.fisi.cenpro.sigeco.mgc.configuration.security.SecurityContextFacade;
import pe.com.fisi.cenpro.sigeco.mgc.controller.form.CitaForm;
import pe.com.fisi.cenpro.sigeco.mgc.services.AsignacionService;
import pe.com.fisi.cenpro.sigeco.mgc.services.CitaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.ClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.CursoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.HorarioClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AlumnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsignacionBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CursoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HorarioClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Constantes;
import pe.com.fisi.cenpro.sigeco.mgc.utils.DateUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensaje;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes;
import com.google.gson.*;

@Controller
@SessionAttributes("horariosClinicas")
public class AlumnoController {

	@Autowired
	private AsignacionService asignacionService;
	@Autowired
	private HorarioClinicaService horarioClinicaService;
	@Autowired
	private CitaService citaService;
	@Autowired
	private ClinicaService clinicaService;
	@Autowired
	private CursoService cursoService;

	private static final String VISTA_ALUMNO_ASIGNACIONES = "alumnoHistorias";

	/**************************************************************/
	/****************** ASIGNACIONES DEL ALUMNO *******************/
	/**************************************************************/

	@RequestMapping("/alumno/asignaciones/listar")
	public String listarHClinicas(Model model, RedirectAttributes redirectAttributes, HttpSession session) {

		AlumnoBO alum = (AlumnoBO) SecurityContextFacade.getAuthenticatedUser().getTipoUsuario();
		List<AsignacionBO> listAsignacionBO = asignacionService
				.listarAsignacionesPorDniAlumno(alum.getUsuarioBO().getDni());
		List<HorarioClinicaBO> list = horarioClinicaService.obtenerTodosHorarios();

		model.addAttribute("listaAsignaciones", listAsignacionBO);
		model.addAttribute("cursos", alum.getCursosActuales());
		model.addAttribute("clinicas", new ArrayList<ClinicaBO>());
		model.addAttribute("horarios", list);
		model.addAttribute("citaForm", new CitaForm());

		if (redirectAttributes.containsAttribute("agregadoA")) {
			model.addAttribute("agregadoA", redirectAttributes.getFlashAttributes().get("agregadoA"));
			model.addAttribute("agregadoH", redirectAttributes.getFlashAttributes().get("agregadoH"));
		}

		return VISTA_ALUMNO_ASIGNACIONES;
	}

	/************************************************************/
	/************* ASIGNACIONES ---> CITAS DEL ALUMNO ***********/
	/************************************************************/
	@RequestMapping(value = "/alumno/asignaciones/citas/registrar")
	public @ResponseBody Mensaje registrarCita(Model modelo, @ModelAttribute @Valid CitaForm citaForm,
			BindingResult br) {
		
		AlumnoBO alumno = (AlumnoBO) SecurityContextFacade.getAuthenticatedUser().getTipoUsuario();

		if (br.hasErrors()) {
			return new Mensaje(Constantes.ERROR, "Hay un error en el llenado de datos");
		}

		String mensaje = citaService.registrarCita(citaForm, alumno.getUsuarioBO().getDni());
		modelo.addAttribute("mensaje", mensaje);

		if (mensaje.equals(Mensajes.M_CITA_CORRECTA)) {
			return new Mensaje(Constantes.OK, mensaje);
		} else {
			return new Mensaje(Constantes.ERROR, mensaje);
		}
	}

	@RequestMapping("/alumno/asignaciones/citas/historialPorPaciente")
	public String listarCitasPorAsignacion(@RequestParam("idAsig") String idAsig, Model model) {
//		AlumnoBO alumno = (AlumnoBO) SecurityContextFacade.getAuthenticatedUser().getTipoUsuario();
		List<CitaBO> listaCitas = citaService.listarCitasPorIdAsignacion(idAsig);

		model.addAttribute("listaCitasPorAsignacion", listaCitas);
		model.addAttribute("idAsignacion", idAsig);
		model.addAttribute("dateUtil", new DateUtil());
		model.addAttribute("hoy", new java.sql.Date(new Date().getTime())	);
		return "alumnoHistorias :: #historialCitas";
	}

	@RequestMapping("/alumno/citas/historialCompleto")
	public String listarCitas(Model model, HttpSession session) {
		AlumnoBO alum = (AlumnoBO) SecurityContextFacade.getAuthenticatedUser().getTipoUsuario();

		CitaBO citaBo = new CitaBO();
		citaBo.setCodigoOperador(alum.getCodigo());
		citaBo.setIsRecita(false);

		List<CitaBO> listCitaBo = citaService.listarCitasPorBo(citaBo);
		List<ClinicaBO> listClinicaBo = clinicaService.findAll();
		List<CursoBO> listCursoBo = cursoService.findAll();

		model.addAttribute("clinicas", listClinicaBo);
		model.addAttribute("citas", listCitaBo);
		model.addAttribute("cursos", listCursoBo);
		session.setAttribute("listaCitas", listCitaBo);
		session.setAttribute("turno", "MA�ANA - TARDE");
		return "alumnoCitas";
	}
	
	@RequestMapping("/alumno/citas/historialCompletoCalendario")
	public String mostrarCalendarioCitas(Model model, HttpSession session) {
		AlumnoBO alum = (AlumnoBO) SecurityContextFacade.getAuthenticatedUser().getTipoUsuario();

		CitaBO citaBo = new CitaBO();
		citaBo.setCodigoOperador(alum.getCodigo());
		citaBo.setIsRecita(false);

		List<CitaBO> listCitaBo = citaService.listarCitasPorBo(citaBo);
		List<ClinicaBO> listClinicaBo = clinicaService.findAll();
		List<CursoBO> listCursoBo = cursoService.findAll();

		model.addAttribute("clinicas", listClinicaBo);
		model.addAttribute("citas", listCitaBo);
		model.addAttribute("cursos", listCursoBo);
		session.setAttribute("listaCitas", listCitaBo);
		session.setAttribute("turno", "MAï¿½ANA - TARDE");
		return "alumnoCalendarioCitas";
	}
	
	@RequestMapping("/alumno/citas/mensajeGsonCitas")
	@ResponseBody
	public String mensajeGsonCitas(Model model, HttpSession session) {
		AlumnoBO alum = (AlumnoBO) SecurityContextFacade.getAuthenticatedUser().getTipoUsuario();

		CitaBO citaBo = new CitaBO();
		citaBo.setCodigoOperador(alum.getCodigo());
		citaBo.setIsRecita(false);
		
		Gson gson = new Gson();
		return gson.toJson(citaService.listarCitasEventosPorBo(citaBo));
	}
	
	@RequestMapping("/alumno/recitas/listar")
	public String listarRecitas(Model model) {

		CitaBO citaBo = new CitaBO();
		citaBo.setIsRecita(true);

		List<CitaBO> listCitaBo = citaService.listarCitasPorBo(citaBo);
		List<ClinicaBO> listClinicaBo = clinicaService.findAll();
		List<CursoBO> listCursoBo = cursoService.findAll();

		model.addAttribute("clinicas", listClinicaBo);
		model.addAttribute("citas", listCitaBo);
		model.addAttribute("cursos", listCursoBo);
		return "alumnoRecitas";
	}

	@RequestMapping(value = "/alumno/citas/buscar", method = RequestMethod.GET)
	public String alumnoFiltrarCitasPorCriterios(Model model, @ModelAttribute("consultaCitas") CitaBO consultaCitas,
			BindingResult bindingResult, HttpSession session) {
		AlumnoBO alum = (AlumnoBO) SecurityContextFacade.getAuthenticatedUser().getTipoUsuario();

		consultaCitas.setCodigoOperador(alum.getCodigo());

		List<CitaBO> listaCitasFiltradasPorCriterio = citaService.listarCitasPorBo(consultaCitas);
		model.addAttribute("citas", listaCitasFiltradasPorCriterio);
		model.addAttribute("tipoUsuario","alumno");
		return "/template/fragments/fragmentosCita :: resultadoBusquedaCitas";
	}

	@RequestMapping(value = "/alumno/asignaciones/citas/cancelarCita", method = RequestMethod.GET)
	public @ResponseBody String cancelarCita(@RequestParam("idCita") Integer idCita, Model model) {
		citaService.cancelarCita(idCita);		
		String json = "";
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

			json = ow.writeValueAsString("OK");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
}
