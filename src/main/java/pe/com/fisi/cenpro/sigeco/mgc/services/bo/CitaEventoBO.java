package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CitaEventoBO {
	
	private int id;
	private String title;
	private Date start;
	private Date end;
	private String descripcion;
	/*
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaAtencion;
	*/
	
	public CitaEventoBO(){
		title= "";
		descripcion = "";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	/*
	public Date getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}
	*/
	
}
