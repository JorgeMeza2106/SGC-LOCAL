package pe.com.fisi.cenpro.sigeco.mgc.services;

import java.util.List;

import pe.com.fisi.cenpro.sigeco.mgc.dto.form.ClinicaForm;
import pe.com.fisi.cenpro.sigeco.mgc.dto.result.ClinicaResult;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ClinicaBO;

public interface ClinicaService {

	public List<ClinicaBO> findAll();
	
	public List<ClinicaResult> buscarTodos();
	public void registrarClinica(ClinicaForm clinicaForm);
	public void actualizarClinica(ClinicaForm clinicaForm);
	public void eliminarClinica(ClinicaForm clinicaForm);
}
