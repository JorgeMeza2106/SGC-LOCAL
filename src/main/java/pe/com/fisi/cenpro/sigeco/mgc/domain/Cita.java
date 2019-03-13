package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the cita database table.
 * 
 */
@Entity
@NamedQuery(name="Cita.findAll", query="SELECT c FROM Cita c")
public class Cita implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCita;

	private Integer codigoPapeleta;

	private String estado;

	@Temporal(TemporalType.DATE)
	private Date fechaAtencion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaReserva;

	//bi-directional many-to-one association to Asignacion
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idAsignacion")
	private Asignacion asignacion;

	//bi-directional many-to-one association to Asistencia
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idAsistencia")
	private Asistencia asistencia;

	//bi-directional many-to-one association to HorarioClinica
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idHorarioClinica")
	private HorarioClinica horarioClinica;

	//bi-directional many-to-many association to Tratamiento
	@ManyToMany
	@JoinTable(
		name="cita_tratamiento"
		, joinColumns={
			@JoinColumn(name="idCita")
			}
		, inverseJoinColumns={
			@JoinColumn(name="idTratamiento")
			}
		)
	private List<Tratamiento> tratamientos;

	public Cita() {
	}

	public int getIdCita() {
		return this.idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}

	public int getCodigoPapeleta() {
		return this.codigoPapeleta;
	}

	public void setCodigoPapeleta(int codigoPapeleta) {
		this.codigoPapeleta = codigoPapeleta;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaAtencion() {
		return this.fechaAtencion;
	}

	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public Date getFechaReserva() {
		return this.fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public Asignacion getAsignacion() {
		return this.asignacion;
	}

	public void setAsignacion(Asignacion asignacion) {
		this.asignacion = asignacion;
	}

	public Asistencia getAsistencia() {
		return this.asistencia;
	}

	public void setAsistencia(Asistencia asistencia) {
		this.asistencia = asistencia;
	}

	public HorarioClinica getHorarioClinica() {
		return this.horarioClinica;
	}

	public void setHorarioClinica(HorarioClinica horarioClinica) {
		this.horarioClinica = horarioClinica;
	}

	public List<Tratamiento> getTratamientos() {
		return this.tratamientos;
	}

	public void setTratamientos(List<Tratamiento> tratamientos) {
		this.tratamientos = tratamientos;
	}

}