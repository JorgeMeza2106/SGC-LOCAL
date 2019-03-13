package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import pe.com.fisi.cenpro.sigeco.mgc.domain.ContratoPre;

public interface ContratoPreRepository extends JpaRepository<ContratoPre, Integer>{
	
	public ContratoPre findOne(int nroContrato);
	
	@Query(value = "select c.nroContrato, hc.nroHC, c.fecha, c.estado "
			+ "from ContratoPre c "
			+ "left join c.historiaClinica hc "
			+ "where hc.nroHC = ?1 and (c.estado = 'ACTIVO' or c.estado = 'ULTIMO_GENERADO')")
	public List<Object[]> obtenerContratoActivoPorHC (int nroHC);
	
	@Query(value = "select c from ContratoPre c "			
			+ "where c.nroContrato = "
			+ "(select MAX(cc.nroContrato) from ContratoPre cc)")
	public ContratoPre obtenerUltimoContrato ();
}
