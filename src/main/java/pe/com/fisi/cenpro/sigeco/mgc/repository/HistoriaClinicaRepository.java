package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.fisi.cenpro.sigeco.mgc.domain.HistoriaClinica;

public interface HistoriaClinicaRepository extends JpaRepository<HistoriaClinica, Integer> {

	@Query(value = "select hc.nroHC, hc.fecha, pa.dniPaciente, pe.nombre, pe.apellidoPaterno, pe.apellidoMaterno, cp.nroContrato, cp.fecha "
			+ "from HistoriaClinica hc "
			+ "left join hc.contratoPres cp on (cp.estado = 'ACTIVO' OR cp.estado = 'ULTIMO_GENERADO') "
			+ "left join hc.paciente pa "
			+ "left join pa.persona pe "
//			+ "group by hc.nroHC "
			+ "order by hc.nroHC ")
	public List<Object[]> obtenerTodasHistoriasClinicas();

	
	@Query(value = "select hc.nroHC, hc.fecha, "
			+ "cp.nroContrato, cp.fecha, "
			+ "pa.dniPaciente, pe.nombre, pe.apellidoPaterno, pe.apellidoMaterno, "
			+ "da.sexo, da.email, da.telefonoFijo, da.telefonoCelular, da.ocupacion, da.domicilio, da.estadoCivil, da.fechaNacimiento, "
			+ "da.ubigeo2, da.ubigeo1 "
			+ "from HistoriaClinica hc "
			+ "left join hc.contratoPres cp "
			+ "left join hc.paciente pa "
			+ "left join pa.persona pe "
			+ "left join pe.datosPersona da "
			+ "left join da.ubigeo2 "
			+ "left join da.ubigeo1 "
			+ "where hc.nroHC = ?1 "
//			+ "group by hc.nroHC "
			+ "order by hc.nroHC ")
	public List<Object[]> obtenerHistoriaClinica(int numHc);
}
