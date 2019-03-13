package pe.com.fisi.cenpro.sigeco.mgc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


//Junta todas las configuraciones...
//configuracion en la nube,  configuracion de seguridad
//se importa todos el mvc, todos lo que tienen enable es preferible trabajarlo por separado
//en teoria PARA cada capa se debe crear una clase que configure, pero por el momento, en serviceConfiguration
//analizamos todo
//@Import(value = {ServiceConfiguration.class})

@Configuration
@ComponentScan(basePackages="pe.com.fisi.cenpro.sigeco.mgc")
@Import(value = {RepositoryConfiguration.class})
public class ApplicationConfiguration {

}
