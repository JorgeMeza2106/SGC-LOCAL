package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the contrato_pre database table.
 * 
 */
@Entity
@Table(name="contrato_pre")
@NamedQuery(name="ContratoPre.findAll", query="SELECT c FROM ContratoPre c")
public class ContratoPre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int nroContrato;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to HistoriaClinica
	@ManyToOne
	@JoinColumn(name="nroHC")
	private HistoriaClinica historiaClinica;
	
	private String estado;

	public ContratoPre() {
	}

	public int getNroContrato() {
		return this.nroContrato;
	}

	public void setNroContrato(int nroContrato) {
		this.nroContrato = nroContrato;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public HistoriaClinica getHistoriaClinica() {
		return this.historiaClinica;
	}

	public void setHistoriaClinica(HistoriaClinica historiaClinica) {
		this.historiaClinica = historiaClinica;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

}