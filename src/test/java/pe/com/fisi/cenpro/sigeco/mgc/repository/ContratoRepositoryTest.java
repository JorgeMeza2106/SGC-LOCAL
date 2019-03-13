package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.fisi.cenpro.sigeco.mgc.configuration.RepositoryConfiguration;
import pe.com.fisi.cenpro.sigeco.mgc.domain.ContratoPre;
import pe.com.fisi.cenpro.sigeco.mgc.repository.ContratoPreRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfiguration.class})
public class ContratoRepositoryTest {
	
	@Autowired
	ContratoPreRepository contratoRepository;
	
	@Test
	public void test(){
		obtenerContratoPorHC(54396);
		//obtenerUltimoC();		
	}
	
	public void obtenerContratoPorHC(int hc){
		List<Object[]> lista = contratoRepository.obtenerContratoActivoPorHC(hc);
		System.out.println("NroContrato: "+ lista.get(0)[0]);
		System.out.println("Fecha: "+ lista.get(0)[2]);
		System.out.println("Estado: "+ lista.get(0)[3]);
	}
	
	public void obtenerUltimoC(){
		ContratoPre contrato = contratoRepository.obtenerUltimoContrato();
		System.out.println("Nro Contrato: "+ contrato.getNroContrato());
		System.out.println("HC: " + contrato.getHistoriaClinica().getNroHC());
	}
	
}
