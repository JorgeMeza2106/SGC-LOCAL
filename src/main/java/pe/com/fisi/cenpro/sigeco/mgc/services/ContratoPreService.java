package pe.com.fisi.cenpro.sigeco.mgc.services;

import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ContratoBO;

public interface ContratoPreService {
	
	public int generarContrato (int nroHC);
	
	public boolean eliminarContrato (int nroHC);
	
	public boolean deshabilitarContrato (int nroHC);
	
	public ContratoBO obtenerUltimo ();
	
	public ContratoBO obtenerUltimo (int nroHC);
}
