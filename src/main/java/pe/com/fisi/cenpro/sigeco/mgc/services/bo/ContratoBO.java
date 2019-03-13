package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ContratoBO {
	private Integer nroContrato;
	private Integer nroHC;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fecha;
	private String estado;
	
	
	public Integer getNroContrato() {
		return nroContrato;
	}
	public void setNroContrato(Integer nroContrato) {
		this.nroContrato = nroContrato;
	}
	public Integer getNroHC() {
		return nroHC;
	}
	public void setNroHC(Integer nroHC) {
		this.nroHC = nroHC;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	} 
	
	
}
