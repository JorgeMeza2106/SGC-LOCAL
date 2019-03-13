package pe.com.fisi.cenpro.sigeco.mgc.services.exceptions;

public class ErrorSistemaException extends RuntimeException {
	
	private static final long serialVersionUID = -6139069065822010489L;

	public ErrorSistemaException(String msg){
		super(msg);
	}
}
