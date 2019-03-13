package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CitaParametrosBO {
	private String pClinica;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date pFechaAtencion;
	private String pTurno;
	public String getClinica() {
		return pClinica;
	}
	public void setPClinica(String clinica) {
		this.pClinica = clinica;
	}
	public Date getPFechaAtencion() {
		return pFechaAtencion;
	}
	public void setPFechaAtencion(Date fechaAtencion) {
		this.pFechaAtencion = fechaAtencion;
	}
	public String getPTurno() {
		return pTurno;
	}
	public void setPTurno(String turno) {
		this.pTurno = turno;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nCitaParametroBO toString").append("\n");
		builder.append("Nombre clinica :" + getClinica()).append("\n");
		builder.append("turno : " + getPTurno()).append("\n");
		builder.append("fechar Atencion :" + getPFechaAtencion()).append("\n");
		return builder.toString();
	}
}
