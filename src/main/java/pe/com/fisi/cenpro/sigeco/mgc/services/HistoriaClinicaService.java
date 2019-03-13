package pe.com.fisi.cenpro.sigeco.mgc.services;

import java.util.List;

import pe.com.fisi.cenpro.sigeco.mgc.controller.form.HistoriaClinicaForm;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;
import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensaje;

public interface HistoriaClinicaService {

	public List<HistoriaClinicaGeneralBO> obtenerTodasHistoriasclinicas();
	
	public List<String> registrarHistoriaClinica(HistoriaClinicaForm historiaForm);
	
	public void modificarHistoriaClinica(HistoriaClinicaForm historiaForm);
	
	public HistoriaClinicaBO obtnerHistoriaClinica(int numHc);
	
	public boolean validarDatos(List<HistoriaClinicaForm> listaForms, List<Mensaje> mensajes);
	
	public List<HistoriaClinicaForm> agregarHistoriasImportadas(List<HistoriaClinicaForm> listaForms, Mensaje mensaje);
}
