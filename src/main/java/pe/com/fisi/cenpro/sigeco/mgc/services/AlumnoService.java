package pe.com.fisi.cenpro.sigeco.mgc.services;

import java.util.List;

import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AlumnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.Diagnostico.DiagnosticoAlumnoBO;

public interface AlumnoService {

	public AlumnoBO findOne(String dniAlumno);
	
	public AlumnoBO obtenerAlumnoConCursos(String dniAlumno);
	
	public boolean existe(String dniAlumno);
	
	public String obtenerNombreCompletoPorCodigo(String codigo);
	
	public List<DiagnosticoAlumnoBO> obtenerAlumnosPorGrupo(int idClinica, String turno, String dia);
}
