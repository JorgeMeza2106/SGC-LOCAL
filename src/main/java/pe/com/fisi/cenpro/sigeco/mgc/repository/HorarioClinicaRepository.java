package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.fisi.cenpro.sigeco.mgc.domain.HorarioClinica;

public interface HorarioClinicaRepository extends JpaRepository<HorarioClinica,Integer>{
	
	@Query(value = "select hc.dia , cl.idClinica, cl.nombre, cu.idCurso "
			+ "from HorarioClinica hc "
			+ "join hc.clinica cl "
			+ "join hc.curso cu")
	public List<Object[]> obtenerHorarios();
	
	
	@Query(value = "select hc.idHorarioClinica, hc.dia "
			+ "from HorarioClinica hc "
			+ "join hc.clinica cl "
			//+ "join hc.curso cu "
			+ "join hc.turno tu "
			+ "where cl.idClinica = ?1 "
			//+ "and cu.idCurso = ?2 "
			+ "and hc.dia = ?2 "
			+ "and tu.descripcion = ?3")
	public List<Object[]> obtenerHorariosPorCursoClinicaDia(int idClinica,  String dia, String turno);
	
	
	@Query(value = "select hc.idHorarioClinica "
			+ "from HorarioClinica hc "
			+ "join hc.clinica cl "
			+ "join hc.turno tu "
			+ "where cl.idClinica = ?1 "
			+ "and tu.idTurno = ?2 "
			+ "and hc.dia = ?3")
	public List<Object> obtenerHorariosPorClinicaTurnoDia(int idClinica, short turno, String dia);
	

	@Query(value = "select new map( hc.idHorarioClinica as idHorarioClinica ,"
			+ " hc.dia as diaHorarioClinica ,"
			+ " cl.idClinica as idClinica ,"
			+ " cl.nombre as nombreClinica ,"
			+ " tu.descripcion as descripcionTurno ,"
			+ " tu.inicio as inicioTurno  ,"
			+ " tu.fin as finTurno "
			+ " ) "
			+ " from HorarioClinica hc "
			+ " join hc.clinica cl "
			+ " join hc.turno tu "
			+ " join hc.administrativo ad"
			+ " where hc.estado = 'VIGENTE' "
			+ " and ad.dniAdministrativo =:dniAdministrativo")
	public List<Map<String, Object>> buscarHorariosPorAdministrativo(@Param("dniAdministrativo") String dniAdministrativo);
	
}
