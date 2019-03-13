package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the doctor database table.
 * 
 */
@Entity
@NamedQuery(name="Doctor.findAll", query="SELECT d FROM Doctor d")
public class Doctor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dniDoctor;

	private String codigo;

	//bi-directional many-to-one association to Asignacion
	@OneToMany(mappedBy="doctor")
	private List<Asignacion> asignacions;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="dniDoctor")
	private Persona persona;

	//bi-directional many-to-one association to HorarioClinica
	@OneToMany(mappedBy="doctor")
	private List<HorarioClinica> horarioClinicas;

	public Doctor() {
	}

	public String getDniDoctor() {
		return this.dniDoctor;
	}

	public void setDniDoctor(String dniDoctor) {
		this.dniDoctor = dniDoctor;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<Asignacion> getAsignacions() {
		return this.asignacions;
	}

	public void setAsignacions(List<Asignacion> asignacions) {
		this.asignacions = asignacions;
	}

	public Asignacion addAsignacion(Asignacion asignacion) {
		getAsignacions().add(asignacion);
		asignacion.setDoctor(this);

		return asignacion;
	}

	public Asignacion removeAsignacion(Asignacion asignacion) {
		getAsignacions().remove(asignacion);
		asignacion.setDoctor(null);

		return asignacion;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<HorarioClinica> getHorarioClinicas() {
		return this.horarioClinicas;
	}

	public void setHorarioClinicas(List<HorarioClinica> horarioClinicas) {
		this.horarioClinicas = horarioClinicas;
	}

	public HorarioClinica addHorarioClinica(HorarioClinica horarioClinica) {
		getHorarioClinicas().add(horarioClinica);
		horarioClinica.setDoctor(this);

		return horarioClinica;
	}

	public HorarioClinica removeHorarioClinica(HorarioClinica horarioClinica) {
		getHorarioClinicas().remove(horarioClinica);
		horarioClinica.setDoctor(null);

		return horarioClinica;
	}

}