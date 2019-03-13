package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clinica database table.
 * 
 */
@Entity
@NamedQuery(name="Clinica.findAll", query="SELECT c FROM Clinica c")
public class Clinica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idClinica;

	private String descripcion;

	private String nombre;

	//bi-directional many-to-one association to HorarioClinica
	@OneToMany(mappedBy="clinica")
	private List<HorarioClinica> horarioClinicas;

	public Clinica() {
	}

	public int getIdClinica() {
		return this.idClinica;
	}

	public void setIdClinica(int idClinica) {
		this.idClinica = idClinica;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<HorarioClinica> getHorarioClinicas() {
		return this.horarioClinicas;
	}

	public void setHorarioClinicas(List<HorarioClinica> horarioClinicas) {
		this.horarioClinicas = horarioClinicas;
	}

	public HorarioClinica addHorarioClinica(HorarioClinica horarioClinica) {
		getHorarioClinicas().add(horarioClinica);
		horarioClinica.setClinica(this);

		return horarioClinica;
	}

	public HorarioClinica removeHorarioClinica(HorarioClinica horarioClinica) {
		getHorarioClinicas().remove(horarioClinica);
		horarioClinica.setClinica(null);

		return horarioClinica;
	}

}