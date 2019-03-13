package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class HistoriaClinicaGeneralBO {

	@NotNull
	@Digits(integer = 5, fraction = 0)
	@Min(50000)
	private Integer nroHC;

	@Digits(integer = 5, fraction = 0)
	@Min(0)
	@Max(3000)
	private Integer nroContrato;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaContrato;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date fechaHC;

	@Pattern(regexp = "[0-9]+")
	@NotNull
	@Size(min = 8, max = 8)
	private String dniPaciente;

	@NotNull
	@Size(min = 1, max = 75)
	private String nombrePaciente;

	@NotNull
	@Size(min = 1, max = 75)
	private String apellidoPatPaciente;

	@NotNull
	@Size(min = 1, max = 75)
	private String apellidoMatPaciente;

	public HistoriaClinicaGeneralBO() {

	}

	public HistoriaClinicaGeneralBO(Integer nroHC, Date fechaHC, Integer nroContrato, Date fechaContrato,
			String dniPaciente, String nombrePaciente, String apellidoPatPaciente, String apellidoMatPaciente) {
		super();
		this.nroHC = nroHC;
		this.nroContrato = nroContrato;
		this.dniPaciente = dniPaciente;
		this.nombrePaciente = nombrePaciente;
		this.apellidoPatPaciente = apellidoPatPaciente;
		this.apellidoMatPaciente = apellidoMatPaciente;
	}

	public HistoriaClinicaGeneralBO(Integer nroHC, Integer nroContrato, String dniPaciente, String apellidoPatPaciente,
			String apellidoMatPaciente, String nombrePaciente) {
		super();
		this.nroHC = nroHC;
		this.nroContrato = nroContrato;
		this.dniPaciente = dniPaciente;
		this.nombrePaciente = nombrePaciente;
		this.apellidoPatPaciente = apellidoPatPaciente;
		this.apellidoMatPaciente = apellidoMatPaciente;
	}

	public String getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}

	public Integer getNroHC() {
		return nroHC;
	}

	public void setNroHC(Integer nroHC) {
		this.nroHC = nroHC;
	}

	public Date getFechaHC() {
		return fechaHC;
	}

	public void setFechaHC(Date fechaHC) {
		this.fechaHC = fechaHC;
	}

	public Integer getNroContrato() {
		return nroContrato;
	}

	public void setNroContrato(Integer nroContrato) {
		this.nroContrato = nroContrato;
	}

	public Date getFechaContrato() {
		return fechaContrato;
	}

	public void setFechaContrato(Date fechaContrato) {
		this.fechaContrato = fechaContrato;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getApellidoPatPaciente() {
		return apellidoPatPaciente;
	}

	public void setApellidoPatPaciente(String apellidoPatPaciente) {
		this.apellidoPatPaciente = apellidoPatPaciente;
	}

	public String getApellidoMatPaciente() {
		return apellidoMatPaciente;
	}

	public void setApellidoMatPaciente(String apellidoMatPaciente) {
		this.apellidoMatPaciente = apellidoMatPaciente;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nHistoria Clinica General BO\n");
		builder.append("Nro HC : " + getNroHC()).append("\n");
		builder.append("Fecha HC : "+getFechaHC()).append("\n");
		builder.append("Fecha Contrato : "+getFechaContrato()).append("\n");
		builder.append("Nro Contrato : " + getNroContrato()).append("\n");
		builder.append("Dni Paciente : " + getDniPaciente()).append("\n");
		builder.append("Nombre del paciente : "+ getNombrePaciente()).append("\n");
		builder.append("Apellido paterno Paciente : " + getApellidoPatPaciente()).append("\n");
		builder.append("Apellido materno paciente : " + getApellidoMatPaciente()).append("\n");

		return builder.toString();
	}
}
