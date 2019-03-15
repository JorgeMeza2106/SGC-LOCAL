package pe.com.fisi.cenpro.sigeco.mgc.services;

import java.util.List;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.CitaForm;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaAsistenciaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CitaEventoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.PapeletaBO;

public interface CitaService {

	public List<CitaBO> listarCitasPorIdAsignacion(String idAsig);
	
	public List<CitaBO> listarCitasPorBo(CitaBO citaBo);
	
	public List<CitaEventoBO> listarCitasEventosPorBo(CitaBO citaBo);
	
	public List<CitaAsistenciaBO> listarCitasAsistenciaPorBo(CitaBO citaBo);
	
	public String registrarCita(CitaForm citaForm, String codigoAlumno);
	
	public String registrarCita(CitaForm citaForm, String codigoAlumno, boolean isValidada);
	
	public void cancelarCita(int idcita);
	
	public PapeletaBO obtenerPapeletaBoPorIdCita(int idCita);
}
