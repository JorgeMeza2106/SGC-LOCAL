package pe.com.fisi.cenpro.sigeco.mgc.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the datos_persona database table.
 * 
 */ 
@Entity
@Table(name="datos_persona")
@NamedQuery(name="DatosPersona.findAll", query="SELECT d FROM DatosPersona d")
public class DatosPersona implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dniPersona;

	private String domicilio;

	private String email;

	@Column(name="estado_civil")
	private String estadoCivil;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_nacimiento")
	private Date fechaNacimiento;

	private String ocupacion;

	private String sexo;

	@Column(name="telefono_celular")
	private String telefonoCelular;

	@Column(name="telefono_fijo")
	private String telefonoFijo;

	//bi-directional one-to-one association to Persona
	@OneToOne
	@JoinColumn(name="dniPersona")
	private Persona persona;

	//bi-directional many-to-one association to Ubigeo
	@ManyToOne
	@JoinColumn(name="lugar_procedencia")
	private Ubigeo ubigeo1;

	//bi-directional many-to-one association to Ubigeo
	@ManyToOne
	@JoinColumn(name="lugar_nacimiento")
	private Ubigeo ubigeo2;

	public DatosPersona() {
	}

	public String getDniPersona() {
		return this.dniPersona;
	}

	public void setDniPersona(String dniPersona) {
		this.dniPersona = dniPersona;
	}

	public String getDomicilio() {
		return this.domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEstadoCivil() {
		return this.estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getOcupacion() {
		return this.ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefonoCelular() {
		return this.telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getTelefonoFijo() {
		return this.telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public Ubigeo getUbigeo1() {
		return this.ubigeo1;
	}

	public void setUbigeo1(Ubigeo ubigeo1) {
		this.ubigeo1 = ubigeo1;
	}

	public Ubigeo getUbigeo2() {
		return this.ubigeo2;
	}

	public void setUbigeo2(Ubigeo ubigeo2) {
		this.ubigeo2 = ubigeo2;
	}

}