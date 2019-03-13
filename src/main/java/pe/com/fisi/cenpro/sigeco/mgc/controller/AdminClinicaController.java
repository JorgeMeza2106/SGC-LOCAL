package pe.com.fisi.cenpro.sigeco.mgc.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import pe.com.fisi.cenpro.sigeco.mgc.services.AsistenciaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.CitaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.ClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.CursoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsistenciaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaAsistenciaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaParametrosBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CursoBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Constantes;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensaje;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensajes;

@Controller
//@SessionAttributes("consultaCitas")
public class AdminClinicaController {
	
	@Autowired
	private AsistenciaService asistenciaService;
	@Autowired
	private CitaService citaService;
	@Autowired
	private ClinicaService clinicaService;
	@Autowired
	private CursoService cursoService;

	@RequestMapping(value = "/admin_cl/pacientes/listar", method = RequestMethod.GET)
	public String listarHistorias(Model model, HttpSession session) {
		CitaBO citaBo = new CitaBO();
		citaBo.setFechaAtencion(new Date());

		List<CitaAsistenciaBO> listCitaBo = citaService.listarCitasAsistenciaPorBo(citaBo);
		List<ClinicaBO> listClinicaBo = clinicaService.findAll();
		List<CursoBO> listCursoBo = cursoService.findAll();
				
		model.addAttribute("citasAsistencia", listCitaBo);
		model.addAttribute("clinicas", listClinicaBo);
		model.addAttribute("cursos", listCursoBo);
		model.addAttribute("consultaCitas", new CitaBO());		
		session.setAttribute("turno", "MAÑANA - TARDE");


		return "adminClinicaAsistenciaPacientes";
	}
	
	@RequestMapping(value = "/admin_cl/{tipo}/listar", method = RequestMethod.GET)
	public String goAsistenciaAlumnos(@PathVariable String tipo, Model model, HttpSession session) {
		switch (tipo) {
			case "alumno":
				break;
			case "docente":
				break;
			case "administrativo":
				break;
		}

		return "asistencia/registro-movimiento-operador";
	} 

	@RequestMapping(value = "/admin_cl/pacientes/buscar", method = RequestMethod.GET)
	public String filtrarCitasPorCriterios(Model model, @ModelAttribute("consultaCitas") CitaBO consultaCitas,
			BindingResult bindingResult, HttpSession session) {
		System.out.println("cosultaCitas: " + consultaCitas.toString());
		List<CitaAsistenciaBO> listaCitasAsistenciaFiltradas = citaService.listarCitasAsistenciaPorBo(consultaCitas);
		model.addAttribute("citasAsistencia", listaCitasAsistenciaFiltradas);
		model.addAttribute("idClinica",consultaCitas.getIdClinica());
		return "/template/fragments/fragmentosPaciente :: resultadoBusquedaPacientes";
	}
	
	@RequestMapping(value = "/admin_cl/pacientes/registrarAsistencia", method = RequestMethod.GET)
	public String guardarAsistenciaPaciente(Model model, @ModelAttribute("consultaCitas") CitaBO consultaCitas,
			@ModelAttribute AsistenciaBO asistencia, BindingResult br){	
		Mensaje mensaje;
		String m = asistenciaService.registrarAsistencia(asistencia);					
		
		System.out.println("Mensaje: " + m);				
		if (m.equals(Mensajes.M_ASISTENCIA_VALIDA))
			mensaje = new Mensaje(Constantes.OK, m);
		else 
			mensaje = new Mensaje(Constantes.ERROR, m);
		
		model.addAttribute("mensaje", mensaje);
		List<CitaAsistenciaBO> listaCitasAsistenciaFiltradas = citaService.listarCitasAsistenciaPorBo(consultaCitas);
		model.addAttribute("citasAsistencia", listaCitasAsistenciaFiltradas);
		model.addAttribute("idClinica",consultaCitas.getIdClinica());
		return "/template/fragments/fragmentosPaciente :: resultadoBusquedaPacientes";
	}
	
	@RequestMapping(value = "/admin_cl/pacientes/exportar", method = RequestMethod.POST)
	public ModelAndView generarExcel(@ModelAttribute("consultaCitas") CitaBO consultaCitas,
			@ModelAttribute CitaParametrosBO parametros, ModelMap modelMap, HttpSession session,
			ModelAndView modelAndView, BindingResult br) {
		System.out.println(consultaCitas);
		System.out.println(parametros);
		List<CitaAsistenciaBO> citasAsistencia = citaService.listarCitasAsistenciaPorBo(consultaCitas);
		if (citasAsistencia.size()==0){
			CitaAsistenciaBO vacio = new CitaAsistenciaBO();
			vacio.setDniPaciente("-");
			citasAsistencia.add(vacio);
			System.out.println("es vacio");
		}
		modelMap.put("datasource", new JRBeanCollectionDataSource(citasAsistencia));
		modelMap.put("fecha_excel", parametros.getPFechaAtencion());
		modelMap.put("turno_excel", (parametros.getPTurno() != null)
				? (!parametros.getPTurno().equals("Seleccione turno")) ? parametros.getPTurno() : "-" : "-");
		modelMap.put("clinica", (parametros.getClinica() != null)
				? (!parametros.getClinica().equals("Seleccione clinica")) ? parametros.getClinica() : "-" : "-");
		modelMap.put("format", "xlsx");
		modelAndView = new ModelAndView("rpt_Asistencia", modelMap);
		return modelAndView;
	}
}
