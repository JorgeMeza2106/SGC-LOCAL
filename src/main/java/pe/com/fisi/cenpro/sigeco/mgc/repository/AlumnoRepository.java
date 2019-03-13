package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, String> {

	public Alumno findOne(String dniPersona);
	
	@Query(value = "select cu.idCurso, cu.nombre "
			+ "from Alumno a "
			+ "left join a.cursos cu "
			+ "where a.dniAlumno = ?1")
	public List<Object[]> obtenerCursosPorAlumno(String dniAlumno);
	
	@Query(value = "select pe.nombre, pe.apellidoPaterno, pe.apellidoMaterno "
			+ "from Alumno a "
			+ "left join a.persona pe "
			+ "where a.codigoAlumno = ?1")
	public List<Object[]> obtenerAlumnoPorCodigo(String codigo);
	
	public Alumno findByCodigoAlumno(String codigoAlumno);
	
	@Query(value = "select a.codigoAlumno , pe.apellidoPaterno, pe.apellidoMaterno, pe.nombre, "
			+ "dp.telefonoCelular, dp.email "
			+ "from Alumno a "			
			+ "left join a.persona pe "
			+ "left join pe.datosPersona dp "
			+ "left join a.horarioClinicas hoc "
			+ "left join hoc.clinica cli "
			+ "left join hoc.turno t "
			+ "where cli.idClinica = ?1 and t.descripcion = ?2 "
			+ "and hoc.dia = ?3 ")
	public List<Object[]> obtenerAlumnosPorGrupo(int idClinica, String turno, String dia);
}
