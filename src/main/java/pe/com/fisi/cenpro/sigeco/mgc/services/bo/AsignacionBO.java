package pe.com.fisi.cenpro.sigeco.mgc.services.bo;

public class AsignacionBO {
	
	//select  asig.idAsignacion, hc.nroHC, cp.nroContrato, per.nombre, per.apellido_paterno, per.apellido_materno

	/*Mostrar Alumno*/
	private Integer idAsignacion;
	
	private HistoriaClinicaGeneralBO datosHistoria;
	
	private String estado;

	public AsignacionBO(){
		
	}
	
	public AsignacionBO(Integer idAsignacion, Integer nroHc, Integer nroContrato, String dniPersona, String apellidoPaterno, String apellidoMaterno, String nombre, String estado) {
		super();
		this.idAsignacion = idAsignacion;
		this.datosHistoria = new HistoriaClinicaGeneralBO(nroHc, nroContrato, dniPersona, apellidoPaterno, apellidoMaterno, nombre);
		this.estado = estado;
	}

	public Integer getIdAsignacion() {
		return idAsignacion;
	}

	public void setIdAsignacion(Integer idAsignacion) {
		this.idAsignacion = idAsignacion;
	}

	public HistoriaClinicaGeneralBO getDatosHistoria() {
		return datosHistoria;
	}

	public void setDatosHistoria(HistoriaClinicaGeneralBO datosHistoria) {
		this.datosHistoria = datosHistoria;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("\n\nAsignacionBO\n");
		builder.append("IdAssginacion : " + getIdAsignacion()).append("\n");
		builder.append("Estado : " + getEstado()).append("\n");
		
		if(getDatosHistoria()!=null){
			builder.append(getDatosHistoria().toString());
		}
		return builder.toString();
	}
	
	
}
