package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

public class ClinicaBO {

//	private Short idClinica;
	private int idClinica;
	private String nombre;
	
	public ClinicaBO() {
	}

//	public ClinicaBO(Short idClinica, String nombreClinica) {
//		this.idClinica = idClinica;
//		this.nombre = nombreClinica;
//	}
	
	public ClinicaBO(int idClinica, String nombreClinica) {
		this.idClinica = idClinica;
		this.nombre = nombreClinica;
	}

//	public Short getIdClinica() {
//		return idClinica;
//	}
//
//	public void setIdClinica(Short idClinica) {
//		this.idClinica = idClinica;
//	}
	public int getIdClinica(){
		return idClinica;
	}
	
	public void setIdClinica(int idClinica) {
		this.idClinica = idClinica;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\nClinicaBO \n\n");
		builder.append(" Idclinica : " + getIdClinica()).append("\n");
		builder.append(" Nombre clinica : " + getNombre()).append("\n");
		return builder.toString();
	}
}
