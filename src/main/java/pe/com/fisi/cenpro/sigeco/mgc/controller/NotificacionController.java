package pe.com.fisi.cenpro.sigeco.mgc.controller;

import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.atmosphere.config.managed.Decoder;
import org.atmosphere.config.managed.Encoder;
import org.atmosphere.config.service.Disconnect;
import org.atmosphere.config.service.Get;
import org.atmosphere.config.service.ManagedService;
import org.atmosphere.config.service.Message;
import org.atmosphere.config.service.Ready;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;

import pe.com.fisi.cenpro.sigeco.mgc.utils.Mensaje;

@ManagedService(path = "/websockets/notificacion")
@Controller
public class NotificacionController {
	private final Log log = LogFactory.getLog(getClass());

	@Ready
	public void onReady(final AtmosphereResource resource) {
		log.info("Browser " + resource.uuid() + " connected.");
		System.out.println("Listooo: " + resource.uuid());
		System.out.println("Listooo 2: " + resource.transport());
	}

	@Disconnect
	public void onDisconnect(AtmosphereResourceEvent event) {
		System.out.println("Desconectando");

		if (event.isCancelled()) {
			log.info("Browser " + event.getResource().uuid() + " unexpectedly disconnected");
		} else if (event.isClosedByClient()) {
			log.info("Browser " + event.getResource().uuid() + " closed the connection");
		}
	}

	@Get
	public void init(AtmosphereResource resource) {
		System.out.println("En init");
		resource.getResponse().setCharacterEncoding(StandardCharsets.UTF_8.name());
	}

	 @Message(encoders = JacksonEncoderDecoder.class, decoders = JacksonEncoderDecoder.class)
	 public Mensaje onMessage(Mensaje mensajeRecita){
		 System.out.println("LLEGOOOOOOOOOOOOOO");
		 return mensajeRecita;
	 }	 
	 
	public static class JacksonEncoderDecoder implements Encoder<Mensaje, String>, Decoder<String, Mensaje> {

		@Override
		public Mensaje decode(String s) {
				System.out.println("En decode");
				return new Mensaje();
		}

		@Override
		public String encode(Mensaje s) {
			try {
				System.out.println("En encode");
				return new Gson().toJson(s);
			} catch (Exception ex) {
				throw new IllegalStateException(ex);
			}
		}

	}
}
