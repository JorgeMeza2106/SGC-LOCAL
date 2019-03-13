package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsignacionForm;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Alumno;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Asignacion;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Doctor;
import pe.com.fisi.cenpro.sigeco.mgc.domain.HistoriaClinica;
import pe.com.fisi.cenpro.sigeco.mgc.repository.AlumnoRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.AsignacionRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.DoctorRepository;
import pe.com.fisi.cenpro.sigeco.mgc.repository.HistoriaClinicaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.AsignacionService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsignacionBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.EstadoAsignacion;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.AsignacionTransformer;

@Service
public class AsignacionServiceImpl implements AsignacionService {

	private final AsignacionRepository asignacionRepository;
	private final DoctorRepository doctorRepository;
	private final AlumnoRepository alumnoRepository;
	private final HistoriaClinicaRepository historiaClinicaRepository;

	@Autowired
	public AsignacionServiceImpl(AsignacionRepository asignacionRepository,DoctorRepository doctorRepository,AlumnoRepository alumnoRepository,HistoriaClinicaRepository historiaClinicaRepository) {
		this.asignacionRepository = asignacionRepository;
		this.doctorRepository=doctorRepository;
		this.alumnoRepository=alumnoRepository;
		this.historiaClinicaRepository=historiaClinicaRepository;
	}

	@Override
	public List<AsignacionBO> listarAsignacionesPorDniAlumno(String dniAlumno) {
		return AsignacionTransformer.transformObjectsToListBo(asignacionRepository.obtenerAsignacionBoPorDniAlumno(dniAlumno));
	}
	
	@Override
	public List<AsignacionBO> listarAsignacionesPorCodigoAlumno(String codigo) {
		Alumno alumno = alumnoRepository.findByCodigoAlumno(codigo);
		if (alumno != null) {
			String dniAlumno = alumno.getDniAlumno();
			return listarAsignacionesPorDniAlumno(dniAlumno);
		}
		return null;
	}

	@Override
	public void registrarAsignacion(AsignacionForm asignacionForm) {
		
		Alumno alumno = alumnoRepository.findByCodigoAlumno(asignacionForm.getCodigoAlumno());
		Doctor doctor = doctorRepository.findOne(asignacionForm.getDniDoctor());
		HistoriaClinica historiaClinica = historiaClinicaRepository.findOne(asignacionForm.getNroHC());
				
		Asignacion asignacion = new Asignacion();
		
		asignacion.setAlumno(alumno);
		asignacion.setDoctor(doctor);
		asignacion.setHistoriaClinica(historiaClinica);
		asignacion.setEstado(asignacionForm.getEstado());
		asignacion.setFechaInicio(asignacionForm.getFechaInicio());
		
		asignacionRepository.saveAndFlush(asignacion);
	}

	@Override
	public void modificarEstadoAsignacion(int idAsignacion, String estado) {
		
		Asignacion asignacion = asignacionRepository.findOne(idAsignacion);
		
		if(asignacion!=null){
			asignacion.setEstado(estado);
			if (estado.equals(EstadoAsignacion.ARCHIVADA.name())) {
				asignacion.setFechaFin(new Date());
			}
			asignacionRepository.saveAndFlush(asignacion);
		}
		
	}

}
