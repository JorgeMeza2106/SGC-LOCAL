package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the curso database table.
 * 
 */
@Entity
@NamedQuery(name="Curso.findAll", query="SELECT c FROM Curso c")
public class Curso implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCurso;

	@Column(name="anio_estudio")
	private byte anioEstudio;

	private String nombre;

	//bi-directional many-to-many association to Alumno
	@ManyToMany(mappedBy="cursos")
	private List<Alumno> alumnos;

	//bi-directional many-to-one association to HorarioClinica
	@OneToMany(mappedBy="curso")
	private List<HorarioClinica> horarioClinicas;

	public Curso() {
	}

	public int getIdCurso() {
		return this.idCurso;
	}

	public void setIdCurso(int idCurso) {
		this.idCurso = idCurso;
	}

	public byte getAnioEstudio() {
		return this.anioEstudio;
	}

	public void setAnioEstudio(byte anioEstudio) {
		this.anioEstudio = anioEstudio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public List<HorarioClinica> getHorarioClinicas() {
		return this.horarioClinicas;
	}

	public void setHorarioClinicas(List<HorarioClinica> horarioClinicas) {
		this.horarioClinicas = horarioClinicas;
	}

	public HorarioClinica addHorarioClinica(HorarioClinica horarioClinica) {
		getHorarioClinicas().add(horarioClinica);
		horarioClinica.setCurso(this);

		return horarioClinica;
	}

	public HorarioClinica removeHorarioClinica(HorarioClinica horarioClinica) {
		getHorarioClinicas().remove(horarioClinica);
		horarioClinica.setCurso(null);

		return horarioClinica;
	}

}