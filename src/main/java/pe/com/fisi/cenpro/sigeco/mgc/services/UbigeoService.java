package pe.com.fisi.cenpro.sigeco.mgc.services;

import java.util.List;

import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UbigeoBO;

public interface UbigeoService {
	
	public List<UbigeoBO> obtenerDepartamentos();
	
	public List<UbigeoBO> obtenerProvinciasPorDepa(String idDepa);
	
	public List<UbigeoBO> obtenerDistritosPorDepaProvincia(String idDepa, String idProvincia);
}
