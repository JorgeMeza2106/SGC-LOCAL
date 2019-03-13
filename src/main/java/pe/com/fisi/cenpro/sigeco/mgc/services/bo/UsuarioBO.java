package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

public class UsuarioBO {

	private String dni;
	private String login; // nombre de usuario
	private String clave;
	private String estado;
	private String rol;

	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;

	public UsuarioBO(String dni, String login, String clave, String estado, String nombre, String apellidoPaterno,
			String apellidoMaterno, String rol) {
		super();
		this.dni = dni;
		this.login = login;
		this.clave = clave;
		this.estado = estado;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.rol = rol;
	}

	public UsuarioBO() {

	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("DNI : " + getDni()).append("\n");
		builder.append("Login : " + getLogin()).append("\n");
		builder.append("Clave : " + getClave()).append("\n");
		builder.append("Estado : " + getEstado()).append("\n");
		builder.append("Rol : " + getRol()).append("\n");
		builder.append("Nombre : " + getNombre()).append("\n");
		builder.append("Apellido Paterno : " + getApellidoPaterno()).append("\n");
		builder.append("Apellido Materno : " + getApellidoMaterno()).append("\n");

		return builder.toString();
	}
}
