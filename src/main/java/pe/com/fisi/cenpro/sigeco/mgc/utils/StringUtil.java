package pe.com.fisi.cenpro.sigeco.mgc.utils;

public class StringUtil {

	public static String separarDespues(String mensaje, String parametro){
		try { 
			return mensaje.substring(mensaje.indexOf(parametro)+1, mensaje.length()-1);
		} catch (Exception ex){
			return null;
		}
	}
}
