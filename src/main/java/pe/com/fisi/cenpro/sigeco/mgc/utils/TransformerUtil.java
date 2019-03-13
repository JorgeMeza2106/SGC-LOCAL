package pe.com.fisi.cenpro.sigeco.mgc.utils;

import java.sql.Time;

import pe.com.fisi.cenpro.sigeco.mgc.services.exceptions.TransformerCastException;

public class TransformerUtil {

	public static Integer toPositiveInteger(Object object)  {
		if(object instanceof Integer){
		     Integer number = (Integer) object;
		     if(number > 0) {
		    	 return number;
		     }
		}
		return -1;
	}
	

	public static Short toPositiveShort(Object object) {
		if(object instanceof Short){
		     Short number = (Short) object;
		     if(number > 0) {
		    	 return number;
		     }
		}
		return -1;
	}
	
	public static String toString(Object object) {
		if(object instanceof String){
			return (String) object;
		}
		return "N/A";
	}
	
	public static Time toTime(Object object) {
		if(object instanceof Time){
			System.out.println("Este es el tiempo:   " + (Time) object );
			return (Time) object ;
		}
		throw new TransformerCastException("Se produjo un problema con la conversión de la Hora");
	}

	
	
	
}
