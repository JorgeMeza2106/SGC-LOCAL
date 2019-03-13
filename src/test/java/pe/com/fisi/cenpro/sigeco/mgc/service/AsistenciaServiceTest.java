package pe.com.fisi.cenpro.sigeco.mgc.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pe.com.fisi.cenpro.sigeco.mgc.configuration.RepositoryConfiguration;
import pe.com.fisi.cenpro.sigeco.mgc.configuration.ServiceConfiguration;
import pe.com.fisi.cenpro.sigeco.mgc.services.AsistenciaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfiguration.class, RepositoryConfiguration.class})
public class AsistenciaServiceTest {

	@Autowired
	AsistenciaService asistenciaService;
	
	@Test
	public void getNombreCompletoPersonal(){
		String dniPersonal = asistenciaService.buscarNombreCompletoPersonal("72028768");
		System.out.println("dni" + dniPersonal);
		assertNotNull("El dni es nulo." , dniPersonal);
	}
}
