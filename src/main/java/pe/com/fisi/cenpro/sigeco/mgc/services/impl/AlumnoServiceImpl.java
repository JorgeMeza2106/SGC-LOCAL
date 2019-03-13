package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Alumno;
import pe.com.fisi.cenpro.sigeco.mgc.domain.Curso;
import pe.com.fisi.cenpro.sigeco.mgc.repository.AlumnoRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.AlumnoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AlumnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.Diagnostico.DiagnosticoAlumnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.AlumnoTransformer;
import pe.com.fisi.cenpro.sigeco.mgc.utils.AppUtil;

@Service
public class AlumnoServiceImpl implements AlumnoService {

	private final AlumnoRepository alumnoRepository;

	@Autowired
	public AlumnoServiceImpl(AlumnoRepository alumnoRepository) {
		this.alumnoRepository = alumnoRepository;
	}

	@Override
	public AlumnoBO findOne(String dniAlumno) {
		Alumno alumno = alumnoRepository.findOne(dniAlumno);
		if (alumno != null) {
			return AlumnoTransformer.transformToBo(alumnoRepository.findOne(dniAlumno));
		} else {
			return null;
		}
	}

	@Override
	public AlumnoBO obtenerAlumnoConCursos(String dniAlumno) {
		Alumno alumno = alumnoRepository.findOne(dniAlumno);
		List<Object[]> list = alumnoRepository.obtenerCursosPorAlumno(dniAlumno);
		alumno.setCursos(obtenerCurso(list));
		return AlumnoTransformer.transformToBo(alumno);
	}

	@Override
	public boolean existe(String dniAlumno) {
		return alumnoRepository.exists(dniAlumno);
	}

	private List<Curso> obtenerCurso(List<Object[]> list) {
		if (list != null) {
			List<Curso> listCursos = new ArrayList<Curso>();
			for (Object[] o : list) {
				Curso curso = new Curso();
				curso.setIdCurso(AppUtil.ObjectToInteger(o[0]));
				curso.setNombre(AppUtil.ObjectToString(o[1]));
				listCursos.add(curso);
			}
			return listCursos;
		}
		return null;
	}

	@Override
	public String obtenerNombreCompletoPorCodigo(String codigo) {
		List<Object[]> list = alumnoRepository.obtenerAlumnoPorCodigo(codigo);
		if (list != null && list.size() > 0) {
			Object[] o = list.get(0);
			StringBuilder builder = new StringBuilder();
			builder.append(AppUtil.ObjectToString(o[0])).append(" ");
			builder.append(AppUtil.ObjectToString(o[1])).append(" ");
			builder.append(AppUtil.ObjectToString(o[2])).append(" ");
			return builder.toString();
		} else {
			return null;
		}
	}
	
	@Override
	public List<DiagnosticoAlumnoBO> obtenerAlumnosPorGrupo(int idClinica, String turno, String dia){
		List<Object[]> list = alumnoRepository.obtenerAlumnosPorGrupo(idClinica, turno, dia);
		if (list != null && list.size() > 0) {
			return AlumnoTransformer.transformObjectsToListBo(list);
		}
		return null;		
	}

}
