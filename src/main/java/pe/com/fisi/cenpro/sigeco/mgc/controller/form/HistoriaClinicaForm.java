package pe.com.fisi.cenpro.sigeco.mgc.controller.form;

import javax.validation.Valid;

import pe.com.fisi.cenpro.sigeco.mgc.services.bo.DatosPersonaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.HistoriaClinicaGeneralBO;


public class HistoriaClinicaForm {
	
	@Valid
	HistoriaClinicaGeneralBO historiaClinicaGeneralBO; 
	
	@Valid
	DatosPersonaBO datosPersonaBO;	
	
	AsignacionForm asignacionForm;
	
	public HistoriaClinicaForm() {
		historiaClinicaGeneralBO = new HistoriaClinicaGeneralBO();
		datosPersonaBO = new DatosPersonaBO();
		asignacionForm = new AsignacionForm();
	}

	public HistoriaClinicaGeneralBO getHistoriaClinicaGeneralBO() {
		return historiaClinicaGeneralBO;
	}

	public void setHistoriaClinicaGeneralBO(HistoriaClinicaGeneralBO historiaClinicaGeneralBO) {
		this.historiaClinicaGeneralBO = historiaClinicaGeneralBO;
	}

	public DatosPersonaBO getDatosPersonaBO() {
		return datosPersonaBO;
	}

	public void setDatosPersonaBO(DatosPersonaBO datosPersonaBO) {
		this.datosPersonaBO = datosPersonaBO;
	}

	public AsignacionForm getAsignacionForm() {
		return asignacionForm;
	}

	public void setAsignacionForm(AsignacionForm asignacionForm) {
		this.asignacionForm = asignacionForm;
	}	

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(getHistoriaClinicaGeneralBO().toString()).append("\n");
		builder.append(getDatosPersonaBO().toString()).append("\n");
		builder.append(getAsignacionForm().toString()).append("\n");
		return builder.toString();
	}
}
