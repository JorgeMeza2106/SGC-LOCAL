package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Cita;

public interface CitaRepository extends JpaRepository<Cita, Integer>{
	
	@Query(value = "select ci.idCita, "
			+ "hc.nroHC, cp.nroContrato, "
	+ "pe1.nombre, pe1.apellidoPaterno, "
	+ "pe2.nombre, pe2.apellidoPaterno, "
	+ "cu.nombre, cu.anioEstudio, "
	//+ "ci.turno, "
	+ "al.dniAlumno, pe2.dniPersona, ci.fechaAtencion, "
	+ "cl.nombre, ci.fechaReserva, asi.idAsignacion, "
	+ "ci.estado, pe1.dniPersona "
	+ "from Cita ci "
	+ "left join ci.asignacion asi "
	+ "left join asi.historiaClinica hc "
	+ "left join hc.contratoPres cp "
	+ "left join hc.paciente pa "
	+ "left join pa.persona pe1 "
	+ "left join asi.alumno al "
	+ "left join al.persona pe2 "
	+ "left join ci.horarioClinica hoc "
	+ "left join hoc.clinica cl "
	+ "left join hoc.curso cu "
	+ "where ci.estado <> 'CANCELADA' "
	+ "order by ci.fechaAtencion desc")
	public List<Object[]> obtenerCitas();
	
	@Query(value = "select ci.idCita, "
			+ "hc.nroHC, cp.nroContrato, "
	+ "pe1.nombre, pe1.apellidoPaterno, "
	+ "pe2.nombre, pe2.apellidoPaterno, "
	+ "cu.nombre, cu.anioEstudio, "
	//+ "ci.turno, "
	+ "al.dniAlumno, pe2.dniPersona, ci.fechaAtencion, "
	+ "cl.nombre, ci.fechaReserva, asi.idAsignacion, "
	+ "ci.estado, pe1.dniPersona "
	+ "from Cita ci "
	+ "left join ci.asignacion asi "
	+ "left join asi.historiaClinica hc "
	+ "left join hc.contratoPres cp "
	+ "left join hc.paciente pa "
	+ "left join pa.persona pe1 "
	+ "left join asi.alumno al "
	+ "left join al.persona pe2 "
	+ "left join ci.horarioClinica hoc "
	+ "left join hoc.clinica cl "
	+ "left join hoc.curso cu "
	+ "where asi.idAsignacion = ?1 "
	+ "and ci.estado <> 'CANCELADA' "
	+ "order by ci.fechaAtencion desc ")
	public List<Object[]> obtenerCitasPorIdAsignacion(int idAsignacion);
	
	@Query(value = "select ci.idCita, "
			+ "hc.nroHC, cp.nroContrato, "
	+ "pe1.nombre, pe1.apellidoPaterno, "
	+ "pe2.nombre, pe2.apellidoPaterno, "
	+ "cu.nombre, cu.anioEstudio, "
	//+ "ci.turno, "
	+ "al.dniAlumno, pe2.dniPersona, ci.fechaAtencion, "
	+ "cl.nombre, ci.fechaReserva, asi.idAsignacion, "
	+ "ci.estado, pe1.dniPersona "
	+ "from Cita ci "
	+ "left join ci.asignacion asi "
	+ "left join asi.historiaClinica hc "
	+ "left join hc.contratoPres cp "
	+ "left join hc.paciente pa "
	+ "left join pa.persona pe1 "
	+ "left join asi.alumno al "
	+ "left join al.persona pe2 "
	+ "left join ci.horarioClinica hoc "
	+ "left join hoc.clinica cl "
	+ "left join hoc.curso cu "
	+ "left join hoc.turno t "
	+ "where "
	+ "('O' = ?1 or t.descripcion = ?1) "
	+ "and ( (-1) = ?2 or hc.nroHC = ?2) "
	+ "and ( (-1) = ?3 or cp.nroContrato = ?3) "
	+ "and ( 'X' = ?4 or al.codigoAlumno = ?4) "
	+ "and ( 'X' = ?5 or pa.dniPaciente = ?5) "
	+ "and ( (-1) = ?6 or cu.idCurso = ?6) "
	+ "and ( (-1) = ?7 or cu.anioEstudio = ?7) "
	+ "and ( (-1) = ?8 or cl.idClinica = ?8) "
	+ "and ( 'X' = ?9 or ci.fechaAtencion = ?9) "
	+ "and ( 'N' = ?10 or ci.fechaAtencion = DATE(ci.fechaReserva)) "
	+ "and ci.estado <> 'CANCELADA' "
	+ "order by ci.fechaAtencion desc")
	public List<Object[]> obtenerCitasPorCampos(String turno, int nroHc, int nroContrato, String codigoAlumno,String dniPaciente,int idCurso, int anioCurso,int idClinica,String fechaAtencion,String esRecita);
	
	@Query(value = "select ci.idCita, "
			+ "hc.nroHC, cp.nroContrato, "
	+ "pe1.nombre, pe1.apellidoPaterno, "
	+ "pe2.nombre, pe2.apellidoPaterno, "
	+ "cu.nombre, "
	+ "al.dniAlumno, pe2.dniPersona, ci.fechaAtencion, "
	+ "cl.nombre, ci.fechaReserva, asi.idAsignacion, "
	+ "ci.estado, pe1.dniPersona, asis.horaEntrada "
	+ "from Cita ci "
	+ "left join ci.asignacion asi "
	+ "left join ci.asistencia asis "
	+ "left join asi.historiaClinica hc "
	+ "left join hc.contratoPres cp "
	+ "left join hc.paciente pa "
	+ "left join pa.persona pe1 "
	+ "left join asi.alumno al "
	+ "left join al.persona pe2 "
	+ "left join ci.horarioClinica hoc "
	+ "left join hoc.clinica cl "
	+ "left join hoc.curso cu "
	+ "left join hoc.turno t "	
	+ "where "
	+ "('O' = ?1 or t.descripcion = ?1) "
	+ "and ( (-1) = ?2 or hc.nroHC = ?2) "
	+ "and ( (-1) = ?3 or cp.nroContrato = ?3) "
	+ "and ( 'X' = ?4 or al.codigoAlumno = ?4) "
	+ "and ( 'X' = ?5 or pa.dniPaciente = ?5) "
	+ "and ( (-1) = ?6 or cu.idCurso = ?6) "
	+ "and ( (-1) = ?7 or cl.idClinica = ?7) "
	+ "and ( 'X' = ?8 or ci.fechaAtencion = ?8) "
	+ "and ci.estado <> 'CANCELADA' "
	+ "order by ci.fechaAtencion desc")
	public List<Object[]> obtenerCitasAsistenciaPorCampos(String turno, int nroHc, int nroContrato, String codigoAlumno,String dniPaciente,int idCurso,int idClinica,String fechaAtencion);
	
	/*metodo obtnerCitas sin atributo turno	 
	 */
//	@Query(value = "select ci.idCita, "
//			+ "hc.nroHC, cp.nroContrato, "
//	+ "pe1.nombre, pe1.apellidoPaterno, "
//	+ "pe2.nombre, pe2.apellidoPaterno, "
//	+ "cu.nombre, cu.anioEstudio, "
//	//+ "ci.turno, "
//	+ "al.dniAlumno, pe2.dniPersona, ci.fechaAtencion, "
//	+ "cl.nombre, ci.fechaReserva, asi.idAsignacion, "
//	+ "ci.estado, pe1.dniPersona "
//	+ "from Cita ci "
//	+ "left join ci.asignacion asi "
//	+ "left join asi.historiaClinica hc "
//	+ "left join hc.contratoPres cp "
//	+ "left join hc.paciente pa "
//	+ "left join pa.persona pe1 "
//	+ "left join asi.alumno al "
//	+ "left join al.persona pe2 "
//	+ "left join ci.horarioClinica hoc "
//	+ "left join hoc.clinica cl "
//	+ "left join hoc.curso cu "	
//	//+ "('O' = ?1 or ci.turno = ?1) "
//	+ "where ( (-1) = ?1 or hc.nroHC = ?1) "
//	+ "and ( (-1) = ?2 or cp.nroContrato = ?2) "
//	+ "and ( 'X' = ?3 or al.codigoAlumno = ?3) "
//	+ "and ( 'X' = ?4 or pa.dniPaciente = ?4) "
//	+ "and ( (-1) = ?5 or cu.idCurso = ?5) "
//	+ "and ( (-1) = ?6 or cu.anioEstudio = ?6) "
//	+ "and ( (-1) = ?7 or cl.idClinica = ?7) "
//	+ "and ( 'X' = ?8 or ci.fechaAtencion = ?8) "
//	+ "and ( 'N' = ?9 or ci.fechaAtencion = DATE(ci.fechaReserva)) "
//	+ "and ci.estado <> 'CANCELADA' "
//	+ "order by ci.fechaAtencion desc")
//	public List<Object[]> obtenerCitasPorCampos(int nroHc, int nroContrato, String codigoAlumno,String dniPaciente,int idCurso, int anioCurso,int idClinica,String fechaAtencion,String esRecita);
	
	
	
	@Query(value = "select "
			+ "count(*) from Cita ci "
			+ "join ci.asignacion asi "
			+ "where asi.alumno.dniAlumno = ?1 "
			+ "and ci.fechaAtencion = ?2")
	public Integer getNumCitasPorDia(String dniPersona, Date date);
	
	@Query(value = "select "
			+ "count(*) from Cita ci "
			+ "join ci.asignacion asi "
			+ "where asi.idAsignacion = ?1 "
			+ "and ci.fechaAtencion = ?2")
	public Integer getNumCitasPorPaciente(int idAsignacion, Date date);
	
	@Query(value = "select "
			+ "t.descripcion, "
			+ "cl.nombre, "
			+ "hc.nroHC, "
			+ "pe1.dniPersona, pe1.nombre, pe1.apellidoPaterno, pe1.apellidoMaterno, "
			+ "dape1.fechaNacimiento, "
			+ "ubi.nombre, "
			+ "cu.nombre "
			+ "from Cita ci "
			+ "left join ci.asignacion asi "
			+ "left join asi.historiaClinica hc "
			+ "left join hc.paciente pa "
			+ "left join pa.persona pe1 "
			+ "left join pe1.datosPersona dape1 "
			+ "left join dape1.ubigeo1 ubi "
			+ "left join ci.horarioClinica hoc "
			+ "left join hoc.clinica cl "
			+ "left join hoc.curso cu "
			+ "left join hoc.turno t "
			+ "where "
			+ "ci.idCita = ?1 ")
	public List<Object[]> obtenerCitasPorId(int idCita);
	
	@Query("SELECT c FROM Cita c WHERE c.idCita = :idCita")
	public Cita findOneCustom(@Param("idCita") Integer idCita);
	
}

