package pe.com.fisi.cenpro.sigeco.mgc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.fisi.cenpro.sigeco.mgc.configuration.RepositoryConfiguration;
import pe.com.fisi.cenpro.sigeco.mgc.repository.HorarioClinicaRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryConfiguration.class})
public class HorarioClinicaRepositoryTest {
	
	@Autowired
	HorarioClinicaRepository horarioClinicaRepository;
	
	@Test
	public void obtenerHorariosPorAdministrativo(){
		
		System.out.println(horarioClinicaRepository.buscarHorariosPorAdministrativo("32132132"));

	}
	
}
