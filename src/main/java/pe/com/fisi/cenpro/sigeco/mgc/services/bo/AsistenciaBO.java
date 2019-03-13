package pe.com.fisi.cenpro.sigeco.mgc.services.bo;


public class AsistenciaBO {
	
	
	private Integer idCitaAsistencia;
	private Integer idClinicaAsistencia;	
	private String dniPersonaAsistencia;	
	
	public Integer getIdCitaAsistencia() {
		return idCitaAsistencia;
	}
	public void setIdCitaAsistencia(Integer idCita) {
		this.idCitaAsistencia = idCita;
	}		
	public Integer getIdClinicaAsistencia() {
		return idClinicaAsistencia;
	}
	public void setIdClinicaAsistencia(Integer idClinica) {
		this.idClinicaAsistencia = idClinica;
	}
	public String getDniPersonaAsistencia() {
		return dniPersonaAsistencia;
	}
	public void setDniPersonaAsistencia(String dniPersona) {
		this.dniPersonaAsistencia = dniPersona;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nAsistenciaBO toString").append("\n");
		builder.append("idCita : " + getIdCitaAsistencia()).append("\n");
		builder.append("idClinica : " + getIdClinicaAsistencia()).append("\n");
		builder.append("dniPersona : " + getDniPersonaAsistencia()).append("\n");
		return builder.toString(); 
	}
}
