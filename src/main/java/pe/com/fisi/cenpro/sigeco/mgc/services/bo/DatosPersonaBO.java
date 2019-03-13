package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

import java.util.Date;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.validation.constraints.Pattern;

import org.springframework.context.annotation.PropertySource;
import org.springframework.format.annotation.DateTimeFormat;

@PropertySource("classpath:database.properties")
public class DatosPersonaBO {

	@NotNull
	private String sexo;
	@Size(min = 1, max = 65)
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
	private String email;
	@Size(min = 0, max = 7)
	private String telefonoFijo;
	@Size(min = 0, max = 9)
	private String telefonoCelular;
	@NotNull
	@Size(min = 1, max = 95)
	private String ocupacion;
	@NotNull
	@Size(min = 1, max = 95)
	private String domicilio;
	private String distrito;
	@NotNull
	private String estadoCivil;
	@NotNull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Past
	private Date fechaNacimiento;

	private String ubigeoProcedencia;
	private String ubigeoNacimiento;

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}

	public String getOcupacion() {
		return ocupacion;
	}

	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getUbigeoProcedencia() {
		return ubigeoProcedencia;
	}

	public void setUbigeoProcedencia(String ubigeoProcedencia) {
		this.ubigeoProcedencia = ubigeoProcedencia;
	}

	public String getUbigeoNacimiento() {
		return ubigeoNacimiento;
	}

	public void setUbigeoNacimiento(String ubigeoNacimiento) {
		this.ubigeoNacimiento = ubigeoNacimiento;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DATOS PEROSNA BO\n");
		builder.append("Sexo : " + getSexo()).append("\n");
		builder.append("Email : " + getEmail()).append("\n");
		builder.append("Telefono Fijo : " + getTelefonoFijo()).append("\n");
		builder.append("Telefono Celular " + getTelefonoCelular()).append("\n");
		builder.append("Ocupacion : " + getOcupacion()).append("\n");
		builder.append("Domicilio : " + getDomicilio()).append("\n");
		builder.append("Distrito : " + getDistrito()).append("\n");
		builder.append("Estado civil : " + getEstadoCivil()).append("\n");
		builder.append("fechaNacimiento : " + getFechaNacimiento()).append("\n");
		builder.append("ubigeoProcedencia : " + getUbigeoProcedencia()).append("\n");
		builder.append("ubigeoNacimiento : " + getUbigeoNacimiento()).append("\n");
		return builder.toString();
	}
}
