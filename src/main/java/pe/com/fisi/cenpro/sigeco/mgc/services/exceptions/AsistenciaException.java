package pe.com.fisi.cenpro.sigeco.mgc.services.exceptions;

public class AsistenciaException extends ServiceException {

	private static final long serialVersionUID = -6139069065822010489L;

	public AsistenciaException(String msg){
		super(msg);
	}
	
	@Override
	public String getMessageTitle() {
		return "Error en la validación de la Asistencia";
	}
	
}
