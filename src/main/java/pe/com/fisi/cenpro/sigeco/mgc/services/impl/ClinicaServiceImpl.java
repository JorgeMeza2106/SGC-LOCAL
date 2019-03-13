package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.dto.form.ClinicaForm;
import pe.com.fisi.cenpro.sigeco.mgc.dto.result.ClinicaResult;
import pe.com.fisi.cenpro.sigeco.mgc.repository.ClinicaRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.ClinicaService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.ClinicaBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.ClinicaTransformer;

@Service
public class ClinicaServiceImpl implements ClinicaService{
	
	private final ClinicaRepository clinicaRepository;
	
	@Autowired
	public ClinicaServiceImpl(ClinicaRepository clinicaRepository) {
		this.clinicaRepository=clinicaRepository;
	}
	
	
	@Override
	public List<ClinicaBO> findAll() {
		return ClinicaTransformer.transformToListBo(clinicaRepository.findAll());
	}


	@Override
	public List<ClinicaResult> buscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void registrarClinica(ClinicaForm clinicaForm) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void actualizarClinica(ClinicaForm clinicaForm) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void eliminarClinica(ClinicaForm clinicaForm) {
		// TODO Auto-generated method stub
		
	}

}
