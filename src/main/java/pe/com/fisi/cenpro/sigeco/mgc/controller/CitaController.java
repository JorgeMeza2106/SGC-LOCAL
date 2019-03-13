package pe.com.fisi.cenpro.sigeco.mgc.controller;
//package pe.com.fisi.cenpro.sigeco.mgc.controller;
//
//import java.util.List;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import pe.com.fisi.cenpro.sigeco.mgc.domain.Cita;
//import pe.com.fisi.cenpro.sigeco.mgc.domain.Clinica;
//import pe.com.fisi.cenpro.sigeco.mgc.repository.AsignacionDAO;
//import pe.com.fisi.cenpro.sigeco.mgc.services.AlumnoServiceDeprecated;
//import pe.com.fisi.cenpro.sigeco.mgc.services.AsignacionServiceDeprecated;
//import pe.com.fisi.cenpro.sigeco.mgc.services.CitaServiceDeprecated;
//import pe.com.fisi.cenpro.sigeco.mgc.services.ClinicaServiceDeprecated;
//import pe.com.fisi.cenpro.sigeco.mgc.services.CursoServiceDeprecated;
//
///*Falta definir donde va el new... ¿en que capa?*/
//@Controller
//public class CitaController {
//
//	
//	@Autowired
//	@Qualifier("citaServiceImplDeprecated")
//	CitaServiceDeprecated citaService;
//	
//	@Autowired
//	@Qualifier("clinicaServiceImplDeprecated")
//	ClinicaServiceDeprecated clinicaService;
//	
//	@Autowired
//	@Qualifier("cursoServiceImplDeprecated")
//	CursoServiceDeprecated cursoService;
//	
//	@Autowired
//	@Qualifier("asignacionServiceImplDeprecated")
//	AsignacionServiceDeprecated asignacionService;
//	
//	@Autowired
//	@Qualifier("alumnoServiceImplDeprecated")
//	AlumnoServiceDeprecated alumnoService;
//	
//	@Autowired
//	@Qualifier("asignacionDAOImpl")
//	AsignacionDAO asignacionDAO;
//	/*@Autowired
//	@Qualifier("historiaClinicaServiceImpl")
//	HistoriaClinicaService hcService;
//	*/
//
//	
//	@RequestMapping("/registrarCita")
//	public String procesarCita(@RequestParam("campo_asignacion") String asignacion,
//			@RequestParam("selectorClinica") short idClinica, 
//			@RequestParam("selectorCurso") short idCurso,
//			@RequestParam("fechaEjecucion") String fechaEjecucion, 
//			@RequestParam("turno") String turno,
//			@RequestParam("tratamiento") Byte idTratamiento, 
//			RedirectAttributes redirectAttributes, Model model) {
//
//        //el controller deberia enviar solo un BO  que el dao debe decodificar...
//		Cita cita = citaService.crearCita(asignacion, idClinica, idCurso, fechaEjecucion, turno, idTratamiento);
//		
//		citaService.save(cita);
//		
//		redirectAttributes.addFlashAttribute("agregadoH", false);
//		redirectAttributes.addFlashAttribute("agregadoA", true);
//
//		return "redirect:/alumno/listarHistorias";
//	}
//	
//	
//	@RequestMapping("/alumno/prepararFormularioCitas")
//	public String listarFormularioCitas(Model model) {
//		/* Prepara el formulario de las citas */
//		model.addAttribute("cursos", cursoService.findAll());
//		List<Clinica> clinicaList = clinicaService.findAll();
//		model.addAttribute("clinicas", clinicaList);
//		
//		return "listarHCAlumno";
//	}
//
//	/*
//	@RequestMapping("/alumno/generarRecord")
//	public String generarRecord(Model model) {
//
//		//Prepara el formulario de las citas 
//		Alumno alum = (Alumno) SecurityContextFacade.getAuthenticatedUser().getTipoUsuario();
//		List<RecordBO> rm = alumnoService.obtenerRecord(alum.getCodigoAlumno());
//		
//		//Total deudas (Logica de BO, debe estar ahí) 
//		float sumad = 0, sumap = 0;
//		for (RecordBO r : rm) {
//			sumad += r.getDebe();
//			sumap += r.getPago();
//		}
//
//		model.addAttribute("record", rm);
//		model.addAttribute("total_pagos", sumap);
//		model.addAttribute("total_deudas", sumad);
//		model.addAttribute("total", red(sumad + sumap));
//
//		return "alumnoRecord";
//	}
//
//	private double red(double valor) {
//		return (double) Math.round(valor * 100) / 100;
//
//	}
//	*/
//}
