package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.exolab.castor.mapping.FieldHandler;
import org.exolab.castor.mapping.ValidityException;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;


/**
 * The persistent class for the asistencia database table.
 * 
 */
@Entity
@DynamicUpdate(value=true)
@NamedQuery(name="Asistencia.findAll", query="SELECT a FROM Asistencia a")
@Builder
@AllArgsConstructor
public class Asistencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idAsistencia;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Time horaEntrada;

	private Time horaSalida;

	//bi-directional many-to-one association to HorarioClinica
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="idHorarioClinica")
	private HorarioClinica horarioClinica;

	//bi-directional many-to-one association to Persona
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="dniPersona")
	private Persona persona;

	//bi-directional many-to-one association to Cita
	@OneToMany(mappedBy="asistencia")
	private List<Cita> citas;

	public Asistencia() {
	}

	public int getIdAsistencia() {
		return this.idAsistencia;
	}

	public void setIdAsistencia(int idAsistencia) {
		this.idAsistencia = idAsistencia;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHoraEntrada() {
		return this.horaEntrada;
	}

	public void setHoraEntrada(Time horaEntrada) {
		this.horaEntrada = horaEntrada;
	}

	public Time getHoraSalida() {
		return this.horaSalida;
	}

	public void setHoraSalida(Time horaSalida) {
		this.horaSalida = horaSalida;
	}

	public HorarioClinica getHorarioClinica() {
		return this.horarioClinica;
	}

	public void setHorarioClinica(HorarioClinica horarioClinica) {
		this.horarioClinica = horarioClinica;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public List<Cita> getCitas() {
		return this.citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public Cita addCita(Cita cita) {
		getCitas().add(cita);
		cita.setAsistencia(this);

		return cita;
	}

	public Cita removeCita(Cita cita) {
		getCitas().remove(cita);
		cita.setAsistencia(null);

		return cita;
	}

	@Override
	public String toString() {
		return "Asistencia [idAsistencia=" + idAsistencia + ", fecha=" + fecha + ", horaEntrada=" + horaEntrada
				+ ", horaSalida=" + horaSalida + ", horarioClinica=" + horarioClinica + ", persona=" + persona
				+ ", citas=" + citas + "]";
	}

}