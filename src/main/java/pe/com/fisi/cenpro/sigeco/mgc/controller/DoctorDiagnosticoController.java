package pe.com.fisi.cenpro.sigeco.mgc.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import pe.com.fisi.cenpro.sigeco.mgc.configuration.security.SecurityContextFacade;
import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsignacionForm;
import pe.com.fisi.cenpro.sigeco.mgc.services.AlumnoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.AsignacionService;
import pe.com.fisi.cenpro.sigeco.mgc.services.ClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.DoctorService;
import pe.com.fisi.cenpro.sigeco.mgc.services.HistoriaClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.TurnoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.DoctorBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.EstadoAsignacion;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;

@SessionAttributes("adminType")
@Controller
public class DoctorDiagnosticoController {
	
	@Autowired
	private ClinicaService clinicaService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private TurnoService turnoService;
	@Autowired
	private HistoriaClinicaService historiaClinicaService;
	@Autowired
	private AsignacionService asignacionService;
	@Autowired
	private DoctorService doctorService;

	@ModelAttribute("estadosAsignacion")
	public List<EstadoAsignacion> estadoAsignacion() {
		return Arrays.asList(EstadoAsignacion.ALL);
	}

	@ModelAttribute("estadosAsignacionNoArchivada")
	public List<EstadoAsignacion> estadoAsignacionNoArchivada() {
		return Arrays.asList(EstadoAsignacion.NO_ARCHIVADA);
	}

	
	@RequestMapping(value = {"/admin_od/historias/listar/", "/admin_cl/historias/listar"}, method = RequestMethod.GET)
	public String listarHistorias(Model modelo, Authentication authentication) {
		boolean hasODRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN_OD"));
		String adminType = (hasODRole) ? "/admin_od" : "/admin_cl";
		List<HistoriaClinicaGeneralBO> listHistoriaClinicaBo = historiaClinicaService.obtenerTodasHistoriasclinicas();
		modelo.addAttribute("adminType", adminType);
		modelo.addAttribute("listaHistorias", listHistoriaClinicaBo);
		modelo.addAttribute("asignacionForm", new AsignacionForm());
		System.out.println("Entre al controller");
		return "doctorDiagnosticoListarHistorias";
	}
	
	@RequestMapping(value = {"/admin_od/historias/listar/asignaciones",
			"/admin_cl/historias/listar/asignaciones"}, method = RequestMethod.GET)
	public String verAsignaciones(@RequestParam("nroHC") Integer numHC, Model modelo) {

		HistoriaClinicaBO hcBO = historiaClinicaService.obtnerHistoriaClinica(numHC);
		List<ClinicaBO> listClinicaBo = clinicaService.findAll();
		
		modelo.addAttribute("historiaClinicaDetalle", hcBO);
		modelo.addAttribute("asignacionForm", new AsignacionForm());
		modelo.addAttribute("clinicas", listClinicaBo);
		modelo.addAttribute("turnos", turnoService.obtenerTodosTurnos());
		return "doctorDiagnosticoListarHistorias :: #modalAsignaciones";
	}
	
	@RequestMapping(value={"/admin_od/historias/listar/asignaciones/alumnos",
			"/admin_cl/historias/listar/asignaciones/alumnos"}, method = RequestMethod.GET)
	public String getAlumnoPorClinica(Model model, @ModelAttribute("adminType") String adminType, @ModelAttribute("idClinica") int idClinica,
			@ModelAttribute("turno") String turno, @ModelAttribute("dia") String dia, Authentication authentication){

		boolean hasODRole = authentication.getAuthorities().stream()
		          .anyMatch(r -> r.getAuthority().equals("ROLE_ADMIN_OD"));
//		String adminType = (hasODRole) ? "/admin_od" : "/admin_cl";
		DoctorBO doctorBO = (hasODRole)
				? (DoctorBO) SecurityContextFacade.getAuthenticatedUser().getTipoUsuario():
			doctorService.obtenerDoctorPorGrupo(idClinica, turno, dia);
		String json = "";
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			json = ow.writeValueAsString(doctorBO);
			System.out.println("json: " + json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("adminType", adminType);
		model.addAttribute("doctor", json);
		model.addAttribute("operadores", alumnoService.obtenerAlumnosPorGrupo(idClinica, turno, dia));
		return "/template/fragments/fragmentosAsignacion :: resultadoBusqueda";
	}
	
	@RequestMapping(value={"/admin_od/historias/listar/asignaciones/alumnos/pacientes",
			"/admin_cl/historias/listar/asignaciones/alumnos/pacientes"}, method = RequestMethod.GET)
	public String getPacientesAlumno(Model model, @ModelAttribute("codigoOperador") String codigo){	
		model.addAttribute("asignados", asignacionService.listarAsignacionesPorCodigoAlumno(codigo));
		System.out.println("Tamaño: " + asignacionService.listarAsignacionesPorCodigoAlumno(codigo).size());
		return "/template/fragments/fragmentosAsignacion :: resultadoPacientes";
	}

	/***************************************/
	/*** ADMINISTRACION DE ASIGNACIONES ****/
	/***************************************/
	// springdateado falta redireccionar
	@RequestMapping(value = "/admin_od/historias/listar/nuevaAsignacion", method = RequestMethod.GET)
	public String registrarAsignacion(Model modelo, @ModelAttribute AsignacionForm asignacionForm, BindingResult br) {
		if(asignacionForm != null)
			asignacionService.registrarAsignacion(asignacionForm);
		return "redirect:/admin_od/historias/listar/";
	}

	@RequestMapping(value = {"/admin_od/historias/listar", "/admin_cl/historias/listar"}, params = { "modificarAsignacion" })
	public String modificarAsignacion(Model modelo, final HttpServletRequest req,
			@ModelAttribute("adminType") String adminType) {
		System.out.println("entre 2");
		final String datos = req.getParameter("modificarAsignacion");
		final int idAsignacion = Integer.parseInt(datos.split("/")[0]);

		String estado = "listaAsignacionDetalle[" + datos.split("/")[1] + "].estado";
		final String estadoActual = req.getParameter(estado);

		 asignacionService.modificarEstadoAsignacion(idAsignacion, estadoActual);

		return "redirect:"+ adminType + "/historias/listar/";

	}
}
