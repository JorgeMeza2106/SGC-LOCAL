package pe.com.fisi.cenpro.sigeco.mgc.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.atmosphere.cpr.BroadcasterFactory;
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

import net.sf.jasperreports.engine.JRException;
import pe.com.fisi.cenpro.sigeco.mgc.controller.form.CitaForm;
import pe.com.fisi.cenpro.sigeco.mgc.services.AlumnoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.AsignacionService;
import pe.com.fisi.cenpro.sigeco.mgc.services.CitaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.ClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.CursoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.HorarioClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AlumnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CursoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.PapeletaBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Constantes;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensaje;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes;

@Controller
@SessionAttributes("admision")
public class AdmisionController {

	@Autowired
	private CitaService citaService;
	@Autowired
	private AlumnoService alumnoService;
	@Autowired
	private AsignacionService asignacionService;
	@Autowired
	private HorarioClinicaService horarioClinicaService;
	@Autowired
	private ClinicaService clinicaService;
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private BroadcasterFactory broadcasterFactory;

	private PapeletaBO papeletaBo;

	/*----------CITAS-----------*/

	@RequestMapping("/admin_ag/citas/listar")
	public String Adminlistar(Model model, HttpSession session) {
		CitaBO citaBo = new CitaBO();
		citaBo.setFechaAtencion(new Date());
		citaBo.setIsRecita(false);

		List<CitaBO> listCitaBo = citaService.listarCitasPorBo(citaBo);
		List<ClinicaBO> listClinicaBo = clinicaService.findAll();
		List<CursoBO> listCursoBo = cursoService.findAll();

		model.addAttribute("citas", listCitaBo);
		model.addAttribute("clinicas", listClinicaBo);
		model.addAttribute("cursos", listCursoBo);
		model.addAttribute("consultaCitas", new CitaBO());

		session.setAttribute("turno", "MAÑANA - TARDE");

		return "adminAdmisionCitas";
	}

	// con spring data
	@RequestMapping(value = "/admin_ag/citas/buscar", method = RequestMethod.GET)
	public String adminFiltrarCitasPorCriterios(Model model, @ModelAttribute("consultaCitas") CitaBO consultaCitas,
			BindingResult bindingResult, HttpSession session) {
		List<CitaBO> listaCitasFiltradasPorCriterio = citaService.listarCitasPorBo(consultaCitas);
		model.addAttribute("citas", listaCitasFiltradasPorCriterio);
		model.addAttribute("tipoAdmin", "diagnostico");
		return "/template/fragments/fragmentosCita :: resultadoBusquedaCitas";
	}

	@RequestMapping("/admin_ag/citas/papeletaPorPaciente")
	public String generarPapeleta(@RequestParam("idCita") String chainIdCita, Model model) {
		System.out.println("antes del error");
		try {
			int idCita = Integer.parseInt(chainIdCita);
			papeletaBo = citaService.obtenerPapeletaBoPorIdCita(idCita);
			papeletaBo.setNumeroPapeleta(chainIdCita);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "adminAdmisionCitas :: #idDivPapeleta";
	}

	@RequestMapping(value = "/admin_ag/citas/papeleta", method = RequestMethod.GET)
	public void verPapeleta(HttpServletResponse response, HttpSession sesion)
			throws FileNotFoundException, JRException {
		try {
			byte[] reporteEnBytes = AppUtil.generarReporteEnByteArray(papeletaBo);
			adjuntarAResponse(response, reporteEnBytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void adjuntarAResponse(HttpServletResponse response, byte[] objetoEnBytes) {
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition", "inline;filename=papeleta.pdf");
		try {
			response.getOutputStream().write(objetoEnBytes);
			response.getOutputStream().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/admin_ag/citas/registrarAdicional", method = RequestMethod.GET)
	public String registrarCita(Model modelo) {
		modelo.addAttribute("resultadoVacio", true);
		return "adminAdmisionCitaAdicional";
	}

	@RequestMapping(value = "/admin_ag/citas/buscarAsignacionesDeAlumno", method = RequestMethod.GET)
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
		return "adminAdmisionCitaAdicional";
	}

	@RequestMapping(value = "/admin_ag/citas/registrar")
	public @ResponseBody Mensaje registrarCita(Model modelo, @Valid CitaForm citaForm, BindingResult br) {
		if (!br.hasErrors()) {

			String mensaje = citaService.registrarCita(citaForm, citaForm.getDniAlumno(), false);
			modelo.addAttribute("mensaje", mensaje);
			if (mensaje.equals(Mensajes.M_CITA_CORRECTA) || mensaje
					.equals(Mensajes.M_CITA_ADICIONAL_CORRECTA + " \n" + Mensajes.M_ADVERTENCIA_CITAS_NO_VALIDADA)) {								
				broadcasterFactory.lookup("/websockets/notificacion", true).broadcast(new Mensaje(Constantes.OK, "Recita registrada"));
				System.out.println("AQUI 1!!!");
				return new Mensaje(Constantes.OK, mensaje);
			} else {				
				System.out.println("AQUI 2!!!");
				return new Mensaje(Constantes.ERROR, mensaje);
			}
		}
		return new Mensaje(Constantes.ERROR, "hay errores");
	}
	/*--------fin citas------------*/	
}
