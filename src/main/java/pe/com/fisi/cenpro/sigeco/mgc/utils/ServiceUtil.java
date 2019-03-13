package pe.com.fisi.cenpro.sigeco.mgc.utils;

import java.util.Map;

import pe.com.fisi.cenpro.sigeco.mgc.services.tipos.TipoMovimiento;

public class ServiceUtil {

	public static String concatenarNombreCompleto(Map<String, Object> personaBuscada) {
		// Obtención de campos
		String nombres = (String) personaBuscada.get("nombre");
		String apellidoPaterno = (String) personaBuscada.get("apellidoPaterno");
		String apellidoMaterno = (String) personaBuscada.get("apellidoMaterno");
		// Formateo de nombres
		String nomProcesado = (nombres == null ? "" : nombres).toUpperCase();
		String apePatProcesado = (apellidoPaterno == null ? "" : apellidoPaterno).toUpperCase();
		String apeMatProcesado = (apellidoMaterno == null ? "" : apellidoMaterno).toUpperCase();
		String nombreCompleto = apePatProcesado + " " + apeMatProcesado + ", " + nomProcesado;
		return nombreCompleto;
	}

	public static TipoMovimiento convertirTipoMovimiento(String movimiento) {
		if (movimiento.equals("entrada")) {
			return TipoMovimiento.ENTRADA;
		} else if (movimiento.equalsIgnoreCase("salida")) {
			return TipoMovimiento.SALIDA;
		}
		return null;
	}
	
	
}
