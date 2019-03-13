package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the administrativo database table.
 * 
 */
@Entity 
@NamedQuery(name="Administrativo.findAll", query="SELECT a FROM Administrativo a")
public class Administrativo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dniAdministrativo;

	private String tipo;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="dniAdministrativo")
	private Persona persona;

	//bi-directional many-to-one association to HorarioClinica
	@OneToMany(mappedBy="administrativo")
	private List<HorarioClinica> horarioClinicas;

	public Administrativo() {
	}

	public String getDniAdministrativo() {
		return this.dniAdministrativo;
	}

	public void setDniAdministrativo(String dniAdministrativo) {
		this.dniAdministrativo = dniAdministrativo;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
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
		horarioClinica.setAdministrativo(this);

		return horarioClinica;
	}

	public HorarioClinica removeHorarioClinica(HorarioClinica horarioClinica) {
		getHorarioClinicas().remove(horarioClinica);
		horarioClinica.setAdministrativo(null);

		return horarioClinica;
	}

}