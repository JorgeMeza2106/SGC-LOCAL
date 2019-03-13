package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the alumno database table.
 * 
 */
@Entity
@NamedQuery(name="Alumno.findAll", query="SELECT a FROM Alumno a")
public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dniAlumno;

	private String codigoAlumno;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="dniAlumno")
	private Persona persona;

	//bi-directional many-to-many association to Curso
	@ManyToMany
	@JoinTable(
		name="alumno_curso"
		, joinColumns={
			@JoinColumn(name="dniAlumno")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idCurso")
			}
		)
	private List<Curso> cursos;

	//bi-directional many-to-many association to HorarioClinica
	@ManyToMany
	@JoinTable(
		name="alumno_horario_clinica"
		, joinColumns={
			@JoinColumn(name="dniAlumno")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idHorarioClinica")
			}
		)
	private List<HorarioClinica> horarioClinicas;

	//bi-directional many-to-one association to Asignacion
	@OneToMany(mappedBy="alumno")
	private List<Asignacion> asignacions;

	public Alumno() {
	}

	public String getDniAlumno() {
		return this.dniAlumno;
	}

	public void setDniAlumno(String dniAlumno) {
		this.dniAlumno = dniAlumno;
	}

	public String getCodigoAlumno() {
		return this.codigoAlumno;
	}

	public void setCodigoAlumno(String codigoAlumno) {
		this.codigoAlumno = codigoAlumno;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Curso> getCursos() {
		return this.cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public List<HorarioClinica> getHorarioClinicas() {
		return this.horarioClinicas;
	}

	public void setHorarioClinicas(List<HorarioClinica> horarioClinicas) {
		this.horarioClinicas = horarioClinicas;
	}

	public List<Asignacion> getAsignacions() {
		return this.asignacions;
	}

	public void setAsignacions(List<Asignacion> asignacions) {
		this.asignacions = asignacions;
	}

	public Asignacion addAsignacion(Asignacion asignacion) {
		getAsignacions().add(asignacion);
		asignacion.setAlumno(this);

		return asignacion;
	}

	public Asignacion removeAsignacion(Asignacion asignacion) {
		getAsignacions().remove(asignacion);
		asignacion.setAlumno(null);

		return asignacion;
	}

}