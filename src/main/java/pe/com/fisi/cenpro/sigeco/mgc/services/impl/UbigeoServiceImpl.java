package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.repository.UbigeoRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.UbigeoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.UbigeoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.UbigeoTransformer;

@Service
public class UbigeoServiceImpl implements UbigeoService{

	private final UbigeoRepository ubigeoRepository;
		
	@Autowired
	public UbigeoServiceImpl(UbigeoRepository ubigeoRepository) {
		super();
		this.ubigeoRepository = ubigeoRepository;
	}

	@Override
	public List<UbigeoBO> obtenerDepartamentos() {
		
		return UbigeoTransformer.transformObjectToListBo(ubigeoRepository.obtenerDepartamentos());
	}

	@Override
	public List<UbigeoBO> obtenerProvinciasPorDepa(String idDepa) {
		
		return UbigeoTransformer.transformObjectToListBo(ubigeoRepository.obtenerProvinciasPorDepa(idDepa));
	}

	@Override
	public List<UbigeoBO> obtenerDistritosPorDepaProvincia(String idDepa, String idProvincia) {
		
		return UbigeoTransformer.transformObjectToListBo(ubigeoRepository.obtenerDistritosPorDepaProvincia(idDepa, idProvincia));
	}

}
