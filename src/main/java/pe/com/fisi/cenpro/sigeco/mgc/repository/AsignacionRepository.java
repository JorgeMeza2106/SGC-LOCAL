package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Asignacion;

public interface AsignacionRepository extends JpaRepository<Asignacion, Integer>{
	
	@Query(value = "select a.idAsignacion , a.estado , hc.nroHC, max(cp.nroContrato), "
			+ "pe.dniPersona, pe.apellidoPaterno, pe.apellidoMaterno, pe.nombre "
			+ "from Asignacion a "
			+ "join a.historiaClinica hc "
			+ "left join hc.contratoPres cp "
			+ "join a.historiaClinica.paciente pa "
			+ "join pa.persona pe "
			+ "where a.alumno.dniAlumno = ?1 "
			+ "and a.estado <> 'ARCHIVADA' "
			+ "group by hc.nroHC "
			+ "order by pe.apellidoPaterno ")
	public List<Object[]> obtenerAsignacionBoPorDniAlumno(String dniAlumno);
	
	
	@Query(value = "select a.idAsignacion, a.fechaInicio, a.fechaFin, a.estado, "
			+ "aluper.nombre,  aluper.apellidoPaterno, aluper.apellidoMaterno, "
			+ "doper.nombre,  doper.apellidoPaterno "
			+ "from Asignacion a "
			+ "join a.historiaClinica hc "
			+ "left join a.alumno alu "
			+ "left join alu.persona aluper "
			+ "left join a.doctor do "
			+ "left join do.persona doper "
			+ "where hc.nroHC = ?1 ")
	public List<Object[]> obtenerAsignacionesPorNroHc(int numHc);
}
