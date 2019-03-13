package pe.com.fisi.cenpro.sigeco.mgc.services.exceptions;

public class HorarioClinicaException extends ServiceException {
	
	private static final long serialVersionUID = -6139069065822010489L;

	public HorarioClinicaException(String msg){
		super(msg);
	}
	
	@Override
	public String getMessageTitle(){
		return "Error en el Horario Clínico";
	}
	
}
