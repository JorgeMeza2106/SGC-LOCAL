package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class CitaBO {

	private Integer idCita;
	private Integer numeroHC;
	private Integer numeroContrato;
	private String clinica;
	private String operador;
	private String paciente;
	private String curso;
	private String estado;
	private Byte anio;
	private char turno;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaAtencion;

	private Short idClinica;
	private Short idCurso;
	private String codigoOperador;
	private String dniPaciente;
	private boolean isRecita;
	
	public CitaBO(){
		codigoOperador = "";
		dniPaciente = "";
		idCurso = -1;
		anio = -1; 		
		idClinica= -1;
		isRecita = false;
	}

	public void setNumeroContrato(Integer numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getCodigoOperador() {
		return codigoOperador;
	}

	public void setCodigoOperador(String codigoOperador) {
		this.codigoOperador = codigoOperador;
	}

	public String getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}

	public Short getIdClinica() {
		return idClinica;
	}

	public void setIdClinica(Short idClinica) {
		this.idClinica = idClinica;
	}

	public Short getIdCurso() {
		return idCurso;
	}

	public void setIdCurso(Short idCurso) {
		this.idCurso = idCurso;
	}

	public Integer getIdCita() {
		return idCita;
	}

	public void setIdCita(Integer idCita) {
		this.idCita = idCita;
	}

	public Integer getNumeroHC() {
		return numeroHC;
	}

	public void setNumeroHC(Integer numeroHC) {
		this.numeroHC = numeroHC;
	}

	public Integer getNumeroContrato() {
		return numeroContrato;
	}

	public void setContrato(Integer numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public String getClinica() {
		return clinica;
	}

	public void setClinica(String clinica) {
		this.clinica = clinica;
	}

	public String getOperador() {
		return operador;
	}

	public void setOperador(String operador) {
		this.operador = operador;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	public Byte getAnio() {
		return anio;
	}

	public void setAnio(Byte anio) {
		this.anio = anio;
	}

	public char getTurno() {
		return turno;
	}

	public void setTurno(char turno) {
		this.turno = turno;
	}

	public Date getFechaAtencion() {
		return fechaAtencion;
	}

	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean getIsRecita() {
		return isRecita;
	}

	public void setIsRecita(boolean isRecita) {
		this.isRecita = isRecita;
	}

	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nCitaBO toString").append("\n");
		builder.append("idCita : " + getIdCita()).append("\n");
		builder.append("numero HC : "+ getNumeroHC()).append("\n");
		builder.append("numero contrato : "+ getNumeroContrato()).append("\n");
		builder.append("Nombre clinica :"+ getClinica()).append("\n");
		builder.append("Operador "+ getOperador()).append("\n");
		builder.append("paciente : "+ getPaciente()).append("\n");
		builder.append("Curso : "+ getCurso()).append("\n");
		builder.append("Estado : "+ getEstado()).append("\n");
		builder.append("Año : " + getAnio()).append("\n");
		builder.append("turno : " + getTurno()).append("\n");
		builder.append("fechar Atencion :" + getFechaAtencion() ).append("\n");
		builder.append("idClinica : "+ getIdClinica()).append("\n");
		builder.append("idCurso : " + getIdCurso()).append("\n");
		builder.append("codigo operador : "+ getCodigoOperador()).append("\n");
		builder.append("DniPaciente : "+ getDniPaciente()).append("\n");
		builder.append("isRecita : "+ getIsRecita()).append("\n");
		return builder.toString();
	}
	
}
