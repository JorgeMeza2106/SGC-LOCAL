package pe.com.fisi.cenpro.sigeco.mgc.services;

import java.util.List;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.AsignacionForm;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.AsignacionBO;

public interface AsignacionService {

	public List<AsignacionBO> listarAsignacionesPorDniAlumno(String dniAlumno);
	
	public List<AsignacionBO> listarAsignacionesPorCodigoAlumno(String codigo);
	
	public void registrarAsignacion(AsignacionForm asignacionForm);
	
	public void modificarEstadoAsignacion(int idAsignacion , String estado);
}
