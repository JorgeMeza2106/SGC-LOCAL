package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.repository.CursoRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.CursoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.CursoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.CursoTransformer;

@Service
public class CursoServiceImpl implements CursoService{

	private final CursoRepository cursoRepository;
	
	@Autowired
	public CursoServiceImpl(CursoRepository cursoRepository) {
		this.cursoRepository=cursoRepository;
	}

	@Override
	public List<CursoBO> findAll() {
		return CursoTransformer.transformToListBo(cursoRepository.findAll());
	}
	
}
