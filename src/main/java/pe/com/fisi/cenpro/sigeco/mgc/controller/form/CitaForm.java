package pe.com.fisi.cenpro.sigeco.mgc.controller.form;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class CitaForm implements Serializable {
	
	private static final long serialVersionUID = 4365084513168264342L;

	@NotNull @Min(1)
	private Integer idAsignacion;
	
	//Verificacion
	@NotNull @Min(1)
	private Integer idClinica;
	 
	@NotNull @Min(1)
	private Integer idCurso;
	
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaAtencion;
	
	@NotNull 
	private char turno;
	

	private Integer idHorarioClinica;
	
	private String dniAlumno;
	
	private boolean isAdicional ;
	
	private String estado;

	
	public CitaForm(){
		
	}
	
	public CitaForm(Integer idAsignacion, Integer idClinica, Integer idCurso, Date fechaAtencion,
			Integer idHorarioClinica, char turno) {
		System.out.println("en constructor0" + fechaAtencion);
		this.idAsignacion = idAsignacion;
		this.idClinica = idClinica;
		this.idCurso = idCurso;
		this.fechaAtencion = fechaAtencion;
		this.idHorarioClinica = idHorarioClinica;
		this.turno = turno;
	}
	
	
	public Integer getIdClinica() {
		return idClinica;
	}
	public void setIdClinica(Integer idClinica) {
		this.idClinica = idClinica;
	}
	public Integer getIdCurso() {
		return idCurso;
	}
	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}
	public Integer getIdAsignacion() {
		return idAsignacion;
	}
	public void setIdAsignacion(Integer idAsignacion) {
		this.idAsignacion = idAsignacion;
	}
	public Integer getIdHorarioClinica() {
		return idHorarioClinica;
	}
	public void setIdHorarioClinica(Integer idHorarioClinica) {
		this.idHorarioClinica = idHorarioClinica;
	}
	public Date getFechaAtencion() {
		System.out.println("en getfe" + fechaAtencion);
		return fechaAtencion;
	}
	public void setFechaAtencion(Date fechaAtencion) {
		System.out.println("en settfe" + fechaAtencion);
		this.fechaAtencion = fechaAtencion;
	}
	public char getTurno() {
		return turno;
	}
	public void setTurno(char turno) {
		this.turno = turno;
	}
	
	public boolean isAdicional() {
		return isAdicional;
	}

	public void setAdicional(boolean isAdicional) {
		this.isAdicional = isAdicional;
	}
	public String getDniAlumno() {
		return dniAlumno;
	}

	public void setDniAlumno(String dniAlumno) {
		this.dniAlumno = dniAlumno;
	}

	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("\n Citaform ToString \n");
		builder.append(" idAsignacion -> "+ getIdAsignacion()).append("\n");
		builder.append("idClinica -> " + getIdClinica()).append("\n");
		builder.append("idcurso --> " + getIdCurso()).append("\n");
		builder.append("fehca atencion --> "+ getFechaAtencion()).append("\n");
		builder.append("turno --> " + getTurno()).append("\n");
		builder.append("idhorarioClinica --> "+ getIdHorarioClinica()).append("\n");
		builder.append("dniAlumno --> "+getDniAlumno()).append("\n");
		return builder.toString();
	}
}
