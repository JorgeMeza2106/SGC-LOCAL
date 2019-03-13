package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the historia_clinica database table.
 * 
 */
@Entity
@Table(name="historia_clinica")
@NamedQuery(name="HistoriaClinica.findAll", query="SELECT h FROM HistoriaClinica h")
public class HistoriaClinica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int nroHC;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	//bi-directional many-to-one association to Asignacion
	@OneToMany(mappedBy="historiaClinica")
	private List<Asignacion> asignacions;

	//bi-directional many-to-one association to ContratoPre
	@OneToMany(mappedBy="historiaClinica")
	private List<ContratoPre> contratoPres;

	//bi-directional many-to-one association to Paciente
	@ManyToOne
	@JoinColumn(name="dniPersona")
	private Paciente paciente;

	public HistoriaClinica() {
	}

	public int getNroHC() {
		return this.nroHC;
	}

	public void setNroHC(int nroHC) {
		this.nroHC = nroHC;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Asignacion> getAsignacions() {
		return this.asignacions;
	}

	public void setAsignacions(List<Asignacion> asignacions) {
		this.asignacions = asignacions;
	}

	public Asignacion addAsignacion(Asignacion asignacion) {
		getAsignacions().add(asignacion);
		asignacion.setHistoriaClinica(this);

		return asignacion;
	}

	public Asignacion removeAsignacion(Asignacion asignacion) {
		getAsignacions().remove(asignacion);
		asignacion.setHistoriaClinica(null);

		return asignacion;
	}

	public List<ContratoPre> getContratoPres() {
		return this.contratoPres;
	}

	public void setContratoPres(List<ContratoPre> contratoPres) {
		this.contratoPres = contratoPres;
	}

	public ContratoPre addContratoPre(ContratoPre contratoPre) {
		getContratoPres().add(contratoPre);
		contratoPre.setHistoriaClinica(this);

		return contratoPre;
	}

	public ContratoPre removeContratoPre(ContratoPre contratoPre) {
		getContratoPres().remove(contratoPre);
		contratoPre.setHistoriaClinica(null);

		return contratoPre;
	}

	public Paciente getPaciente() {
		return this.paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

}