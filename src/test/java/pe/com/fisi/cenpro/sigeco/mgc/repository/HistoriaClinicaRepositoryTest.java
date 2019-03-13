package pe.com.fisi.cenpro.sigeco.mgc.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.fisi.cenpro.sigeco.mgc.configuration.RepositoryConfiguration;
import pe.com.fisi.cenpro.sigeco.mgc.repository.HistoriaClinicaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfiguration.class})
public class HistoriaClinicaRepositoryTest {

	@Autowired
	HistoriaClinicaRepository hcRepository;
	
	@Test
	public void test(){
		listaHC();
	}
	
	public void listaHC(){
		List<Object[]> lista = hcRepository.obtenerTodasHistoriasClinicas();
		System.out.println("TAMAÑO: " + lista.size());
		for(Object[] obj : lista){
			System.out.println(obj[0] + " - " + obj[6]);
		}
	}
}
