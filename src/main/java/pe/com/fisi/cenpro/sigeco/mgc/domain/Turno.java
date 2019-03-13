package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the turno database table.
 * 
 */
@Entity
@NamedQuery(name="Turno.findAll", query="SELECT t FROM Turno t")
public class Turno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private short idTurno;

	private String descripcion;

	private byte estado;

	private Time fin;

	private Time inicio;

	//bi-directional many-to-one association to HorarioClinica
	@OneToMany(mappedBy="turno")
	private List<HorarioClinica> horarioClinicas;

	public Turno() {
	}

	public short getIdTurno() {
		return this.idTurno;
	}

	public void setIdTurno(short idTurno) {
		this.idTurno = idTurno;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public byte getEstado() {
		return this.estado;
	}

	public void setEstado(byte estado) {
		this.estado = estado;
	}

	public Time getFin() {
		return this.fin;
	}

	public void setFin(Time fin) {
		this.fin = fin;
	}

	public Time getInicio() {
		return this.inicio;
	}

	public void setInicio(Time inicio) {
		this.inicio = inicio;
	}

	public List<HorarioClinica> getHorarioClinicas() {
		return this.horarioClinicas;
	}

	public void setHorarioClinicas(List<HorarioClinica> horarioClinicas) {
		this.horarioClinicas = horarioClinicas;
	}

	public HorarioClinica addHorarioClinica(HorarioClinica horarioClinica) {
		getHorarioClinicas().add(horarioClinica);
		horarioClinica.setTurno(this);

		return horarioClinica;
	}

	public HorarioClinica removeHorarioClinica(HorarioClinica horarioClinica) {
		getHorarioClinicas().remove(horarioClinica);
		horarioClinica.setTurno(null);

		return horarioClinica;
	}

}