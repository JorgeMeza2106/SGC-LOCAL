package pe.com.fisi.cenpro.sigeco.mgc.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.atmosphere.cache.UUIDBroadcasterCache;
import org.atmosphere.client.TrackMessageSizeInterceptor;
import org.atmosphere.cpr.AnnotationProcessor;
import org.atmosphere.cpr.ApplicationConfig;
import org.atmosphere.cpr.AtmosphereFramework;
import org.atmosphere.cpr.AtmosphereInterceptor;
import org.atmosphere.cpr.BroadcasterFactory;
import org.atmosphere.cpr.BroadcasterLifeCyclePolicy.ATMOSPHERE_RESOURCE_POLICY;
import org.atmosphere.spring.bean.AtmosphereSpringContext;
import org.atmosphere.util.VoidAnnotationProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AtmosphereConfig {

	@Bean
	public AtmosphereFramework atmosphereFramework()
			throws ServletException, InstantiationException, IllegalAccessException {
		AtmosphereFramework atmosphereFramework = new AtmosphereFramework(false, false);
		atmosphereFramework.setBroadcasterCacheClassName(UUIDBroadcasterCache.class.getName());
		return atmosphereFramework;
	}

	@Bean
	public BroadcasterFactory broadcasterFactory()
			throws ServletException, InstantiationException, IllegalAccessException {
		return atmosphereFramework().getAtmosphereConfig().getBroadcasterFactory();
	}

	@Bean
	public AtmosphereSpringContext atmosphereSpringContext() {
		AtmosphereSpringContext atmosphereSpringContext = new AtmosphereSpringContext();
		Map<String, String> map = new HashMap<>();
		map.put("org.atmosphere.cpr.broadcasterClass", org.atmosphere.cpr.DefaultBroadcaster.class.getName());
		map.put(AtmosphereInterceptor.class.getName(), TrackMessageSizeInterceptor.class.getName());
		//map.put(AnnotationProcessor.class.getName(), VoidAnnotationProcessor.class.getName());
		map.put(ApplicationConfig.HEARTBEAT_INTERVAL_IN_SECONDS, "10");
		map.put("org.atmosphere.cpr.broadcasterLifeCyclePolicy", ATMOSPHERE_RESOURCE_POLICY.IDLE_DESTROY.toString());
		map.put(ApplicationConfig.WEBSOCKET_SUPPRESS_JSR356, "true");
		atmosphereSpringContext.setConfig(map);
		System.out.println("antes de retornar atmosphereSpringContext");
		return atmosphereSpringContext;
	}
}
