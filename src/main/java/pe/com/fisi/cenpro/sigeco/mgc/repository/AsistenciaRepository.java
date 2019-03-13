package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Asistencia;


public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {

					 @Query("select a "
					+ " from Asistencia a "
					+ " inner join  a.horarioClinica hc "
					+ " inner join  a.persona pe "
					+ " where hc.idHorarioClinica = :idHorarioClinica and "
					+ " a.fecha = :fecha and "
					+ " pe.dniPersona = :dniPersona ")
	List<Asistencia> buscarLsAsistenciaPorHorarioFecha ( 
			@Param("idHorarioClinica") Short idHorarioClinica,
			@Param("fecha") Date fecha,
			@Param("dniPersona") String dniPersona);
}
