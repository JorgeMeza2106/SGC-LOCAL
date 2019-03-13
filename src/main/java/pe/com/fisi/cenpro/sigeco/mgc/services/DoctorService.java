package pe.com.fisi.cenpro.sigeco.mgc.services;

import java.util.List;

import pe.com.fisi.cenpro.sigeco.mgc.services.bo.DoctorBO;

public interface DoctorService {

	public List<DoctorBO> obtenerTodosDoctores();
	
	public List<DoctorBO> obtenerTodosDoctoresPorPaterno(String apPaterno);
	
	public DoctorBO obtenerDoctorPorGrupo(int idClinica, String turno, String dia);
}
