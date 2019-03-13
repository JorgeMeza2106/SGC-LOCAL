package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Ubigeo;

public interface UbigeoRepository extends JpaRepository<Ubigeo, Integer>{

	@Query(value ="select ubi.idUbigeo, ubi.departamentoId, ubi.provinciaId, ubi.distritoId, ubi.nombre "
			+ "from Ubigeo ubi "
			+ "where (ubi.departamentoId = 15 or ubi.departamentoId = 7) and ubi.provinciaId = 1 "
			+ "and ubi.distritoId <> 0 and ubi.nombre like %?1% ")
	public List<Object[]> obtenerUbigeosPorNombre(String nombre);
	
	
	@Query(value = "select ubi.idUbigeo, ubi.departamentoId, ubi.provinciaId, ubi.distritoId, ubi.nombre " 
			+ "from Ubigeo ubi " )		
//			+ "group by ubi.departamentoId")
	public List<Object[]> obtenerDepartamentos();
	
	@Query(value = "select ubi.idUbigeo, ubi.departamentoId, ubi.provinciaId, ubi.distritoId, ubi.nombre " 
			+ "from Ubigeo ubi "  
			+ "where (ubi.departamentoId = ?1 "
			+ "and ubi.distritoId <> 0) ")  
//			+ "group by  ubi.provinciaId")
	public List<Object[]> obtenerProvinciasPorDepa(String idDepartamento);
	
	
	
	@Query(value = "select ubi.idUbigeo, ubi.departamentoId, ubi.provinciaId, ubi.distritoId, ubi.nombre " 
			+ "from Ubigeo ubi " 
			+ "where (ubi.departamentoId = ?1 "	
			+ "and ubi.distritoId <> 0 "
			+ "and ubi.provinciaId = ?2)")	
	public List<Object[]> obtenerDistritosPorDepaProvincia(String idDepartamento, String idProvincia);
	
	public List<Ubigeo> findByDepartamentoIdAndProvinciaIdAndDistritoId(String departamentoId, String provinciaId, String distritoId);
}
