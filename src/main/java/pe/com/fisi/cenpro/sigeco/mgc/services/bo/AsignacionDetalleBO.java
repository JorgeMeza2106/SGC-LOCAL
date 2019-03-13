package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.util.Date;

public class AsignacionDetalleBO {

	private Integer idAsignacion;

	private String nombreOperador;
	private String apellidoPatOperador;
	private String apellidoMatOperador;

	private String nombreDoctor;
	private String apellidoPatDoctor;

	private Date fechaInicio;
	private Date fechaFin;
	private String estado;

	public AsignacionDetalleBO() {
		super();
		idAsignacion = 0;
		nombreOperador = "";
		apellidoPatOperador = "";
		apellidoMatOperador = "";
		nombreDoctor = "";
		apellidoPatDoctor = "";
		fechaInicio = null;
		fechaFin = null;
		estado = "";
	}

	public Integer getIdAsignacion() {
		return idAsignacion;
	}

	public void setIdAsignacion(Integer idAsignacion) {
		this.idAsignacion = idAsignacion;
	}

	public String getNombreOperador() {
		return nombreOperador;
	}

	public void setNombreOperador(String nombreOperador) {
		this.nombreOperador = nombreOperador;
	}

	public String getApellidoPatOperador() {
		return apellidoPatOperador;
	}

	public void setApellidoPatOperador(String apellidoPatOperador) {
		this.apellidoPatOperador = apellidoPatOperador;
	}

	public String getApellidoMatOperador() {
		return apellidoMatOperador;
	}

	public void setApellidoMatOperador(String apellidoMatOperador) {
		this.apellidoMatOperador = apellidoMatOperador;
	}

	public String getNombreDoctor() {
		return nombreDoctor;
	}

	public void setNombreDoctor(String nombreDoctor) {
		this.nombreDoctor = nombreDoctor;
	}

	public String getApellidoPatDoctor() {
		return apellidoPatDoctor;
	}

	public void setApellidoPatDoctor(String apellidoPatDoctor) {
		this.apellidoPatDoctor = apellidoPatDoctor;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
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
		builder.append("IdAsignacion  : " + getIdAsignacion()).append("\n");
		builder.append("Nombre Operardor : " + getNombreOperador()).append("\n");
		builder.append("Apellido paterno operador: " + getApellidoPatOperador()).append("\n");
		builder.append("Apellido materno operador : " + getApellidoMatOperador()).append("\n");
		builder.append("Nombre del doctor : " + getNombreDoctor()).append("\n");
		builder.append("Apellido paterno doctor : " + getApellidoPatDoctor()).append("\n");
		builder.append("Fecha de inicio : " + getFechaInicio()).append("\n");
		builder.append("Fecha fin : " + getFechaFin()).append("\n");
		builder.append("Estado : " + getEstado()).append("\n");
		return builder.toString();
	}
}
