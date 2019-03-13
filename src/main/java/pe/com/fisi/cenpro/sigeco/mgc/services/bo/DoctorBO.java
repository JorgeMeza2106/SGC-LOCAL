package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

public class DoctorBO {
	
	private String dni; //Puede ser su DNI o su CMP
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	
	public DoctorBO(){}
	public DoctorBO(String dni, String nombre, String apellidoPaterno, String apellidoMaterno) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
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
	public void setNombre(String nombres) {
		this.nombre = nombres;
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
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(" DOCTOR BO").append("\n");
		builder.append("dni  -> "+ getDni()).append("\n");
		builder.append("nombre --> "+ getNombre()).append("\n");
		builder.append("ap Paterno  -> " + getApellidoPaterno()).append("\n");
		builder.append("AP MAterno --> " + getApellidoMaterno()).append("\n");
		
		return builder.toString();
	}
}
