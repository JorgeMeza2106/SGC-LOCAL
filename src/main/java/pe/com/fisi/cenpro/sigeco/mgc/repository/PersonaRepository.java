package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pe.com.fisi.cenpro.sigeco.mgc.domain.Persona;

public interface PersonaRepository extends JpaRepository<Persona, String>{
	
	@Query("select new map(p.apellidoMaterno as apellidoMaterno, "
			+ "p.apellidoPaterno as apellidoPaterno, "
			+ "p.nombre as nombre) "
			+ "from Persona p where p.dniPersona = :dni")
	public Map<String, Object> getNombreCompletoPorDni(@Param("dni") String dniPersona);

}
