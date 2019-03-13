package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;


/**
 * The persistent class for the horario_clinica database table.
 * 
 */
@Entity
@Table(name="horario_clinica")
@NamedQuery(name="HorarioClinica.findAll", query="SELECT h FROM HorarioClinica h")
@Builder
@AllArgsConstructor
public class HorarioClinica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short idHorarioClinica;

	private String dia;

	private String estado;

	private byte prioridad;

	//bi-directional many-to-many association to Alumno
	@ManyToMany(mappedBy="horarioClinicas")
	private List<Alumno> alumnos;

	//bi-directional many-to-one association to Asistencia
	@OneToMany(mappedBy="horarioClinica", fetch=FetchType.LAZY)
	private List<Asistencia> asistencias;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="horarioClinica")
	private List<Cita> citas;

	//bi-directional many-to-one association to Clinica
	@ManyToOne
	@JoinColumn(name="idClinica")
	private Clinica clinica;

	//bi-directional many-to-one association to Administrativo
	@ManyToOne
	@JoinColumn(name="dniAdministrativo")
	private Administrativo administrativo;

	//bi-directional many-to-one association to Curso
	@ManyToOne
	@JoinColumn(name="idCurso")
	private Curso curso;

	//bi-directional many-to-one association to Doctor
	@ManyToOne
	@JoinColumn(name="dniDoctor")
	private Doctor doctor;

	//bi-directional many-to-one association to Turno
	@ManyToOne
	@JoinColumn(name="idTurno")
	private Turno turno;

	public HorarioClinica() {
	}

	public short getIdHorarioClinica() {
		return this.idHorarioClinica;
	}

	public void setIdHorarioClinica(short idHorarioClinica) {
		this.idHorarioClinica = idHorarioClinica;
	}

	public String getDia() {
		return this.dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public byte getPrioridad() {
		return this.prioridad;
	}

	public void setPrioridad(byte prioridad) {
		this.prioridad = prioridad;
	}

	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public List<Asistencia> getAsistencias() {
		return this.asistencias;
	}

	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}

	public Asistencia addAsistencia(Asistencia asistencia) {
		getAsistencias().add(asistencia);
		asistencia.setHorarioClinica(this);

		return asistencia;
	}

	public Asistencia removeAsistencia(Asistencia asistencia) {
		getAsistencias().remove(asistencia);
		asistencia.setHorarioClinica(null);

		return asistencia;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setHorarioClinica(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setHorarioClinica(null);

		return cita;
	}

	public Clinica getClinica() {
		return this.clinica;
	}

	public void setClinica(Clinica clinica) {
		this.clinica = clinica;
	}

	public Administrativo getAdministrativo() {
		return this.administrativo;
	}

	public void setAdministrativo(Administrativo administrativo) {
		this.administrativo = administrativo;
	}

	public Curso getCurso() {
		return this.curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Doctor getDoctor() {
		return this.doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Turno getTurno() {
		return this.turno;
	}

	public void setTurno(Turno turno) {
		this.turno = turno;
	}

}