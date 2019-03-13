package pe.com.fisi.cenpro.sigeco.mgc.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.fisi.cenpro.sigeco.mgc.repository.DoctorRepository;
import pe.com.fisi.cenpro.sigeco.mgc.services.DoctorService;
import pe.com.fisi.cenpro.sigeco.mgc.services.bo.DoctorBO;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.AlumnoTransformer;
import pe.com.fisi.cenpro.sigeco.mgc.services.transformer.DoctorTransformer;

@Service
public class DoctorServiceImpl implements DoctorService{
	
	private final DoctorRepository doctorRepository;
	
	@Autowired
	public DoctorServiceImpl(DoctorRepository doctorRepository) {
		this.doctorRepository=doctorRepository;
	}

	@Override
	public List<DoctorBO> obtenerTodosDoctores() {
		List<Object[]> list = doctorRepository.obtenerTodosDoctores();
		return DoctorTransformer.transformListObjectToListBo(list);
	}

	@Override
	public List<DoctorBO> obtenerTodosDoctoresPorPaterno(String apPaterno) {
		List<Object[]> list = doctorRepository.obtenerTodosDoctoresPorPaterno(apPaterno);
		return DoctorTransformer.transformListObjectToListBo(list);
	}
	
	@Override
	public DoctorBO obtenerDoctorPorGrupo(int idClinica, String turno, String dia){
		List<Object[]> list = doctorRepository.obtenerDoctorPorGrupo(idClinica, turno, dia);
		if (list != null && list.size() > 0) {
			return DoctorTransformer.transformListObjectToListBo(list).get(0);
		}
		return null;	
	}

}
