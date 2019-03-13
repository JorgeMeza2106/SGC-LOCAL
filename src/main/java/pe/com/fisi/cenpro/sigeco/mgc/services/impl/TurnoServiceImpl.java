package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.repository.TurnoRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.TurnoService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.TurnoBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.TurnoTransformer;

@Service
public class TurnoServiceImpl implements TurnoService{

	private final TurnoRepository turnoRepository;
	
	@Autowired
	public TurnoServiceImpl(TurnoRepository turnoRepository) {
		this.turnoRepository = turnoRepository;
	}
	
	@Override
	public List<TurnoBO> obtenerTodosTurnos() {		
		return TurnoTransformer.transformToListBo(turnoRepository.findAll());
	}

}
