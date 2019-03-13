package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, String>{

	@Query(value = "select do.dniDoctor, pe.nombre, pe.apellidoPaterno, pe.apellidoMaterno "
			+ "from Doctor do "
			+ "left join do.persona pe ")
	public List<Object[]> obtenerTodosDoctores();
	
	@Query(value = "select do.dniDoctor, pe.nombre, pe.apellidoPaterno, pe.apellidoMaterno "
			+ "from Doctor do "
			+ "left join do.persona pe "
			+ "where pe.apellidoPaterno = ?1")
	public List<Object[]> obtenerTodosDoctoresPorPaterno(String apPaterno);
	
	@Query(value = "select d.dniDoctor, pe.nombre, pe.apellidoPaterno, pe.apellidoMaterno "
			+ "from Doctor d "			
			+ "left join d.persona pe "
			+ "left join d.horarioClinicas hoc "
			+ "left join hoc.clinica cli "
			+ "left join hoc.turno t "
			+ "where cli.idClinica = ?1 and t.descripcion = ?2 "
			+ "and hoc.dia = ?3 ")
	public List<Object[]> obtenerDoctorPorGrupo(int idClinica, String turno, String dia);
	
}
