package pe.com.fisi.cenpro.sigeco.mgc.services.exceptions;

public class ServiceException extends RuntimeException {


	private static final long serialVersionUID = -3049247067184097239L;

	public ServiceException(String msg) {
		super(msg);
	}
	
	public String getMessageTitle(){
		return "Error de validación de reglas del sistema.";
	}

}
