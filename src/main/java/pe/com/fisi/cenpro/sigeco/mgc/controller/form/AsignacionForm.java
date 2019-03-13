package pe.com.fisi.cenpro.sigeco.mgc.controller.form;

import java.util.Date;

public class AsignacionForm {
	private Integer nroHC;
	private String codigoAlumno;
	private String dniDoctor;
	private Date fechaInicio;
	private Date fechafin;
	private String estado;

	public Integer getNroHC() {
		return nroHC;
	}

	public void setNroHC(Integer nroHC) {
		this.nroHC = nroHC;
	}

	public String getCodigoAlumno() {
		return codigoAlumno;
	}

	public void setCodigoAlumno(String codigoAlumno) {
		this.codigoAlumno = codigoAlumno;
	}

	public String getDniDoctor() {
		return dniDoctor;
	}

	public void setDniDoctor(String dniDoctor) {
		this.dniDoctor = dniDoctor;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Asignacion FORM ").append("\n");
		builder.append("nroHC : " + getNroHC()).append("\n");
		builder.append("codigo alumno : " + getCodigoAlumno()).append("\n");
		builder.append("dni Doctor : " + getDniDoctor()).append("\n");
		builder.append("fecha inicio : " + getFechaInicio()).append("\n");
		builder.append("Fecha fin : " + getFechafin()).append("\n");
		builder.append("Estado : " + getEstado()).append("\n");

		return builder.toString();
	}
}
