package trail.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

import trail.rest.resources.EntityResource;

@ApplicationPath("/")
public class TrailApplication extends ResourceConfig {

	public TrailApplication() {
		
		property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
		// Resources.
		packages(EntityResource.class.getPackage().getName());

		// Providers - JSON.
		register(MoxyJsonFeature.class);
		// register(JacksonFeature.class);
		register(JsonConfiguration.class);
	}

	/**
	 * Configuration for
	 * {@link org.eclipse.persistence.jaxb.rs.MOXyJsonProvider} - outputs
	 * formatted JSON.
	 */
	public static class JsonConfiguration implements
			ContextResolver<MoxyJsonConfig> {
		public MoxyJsonConfig getContext(final Class<?> type) {
			final MoxyJsonConfig config = new MoxyJsonConfig();
			config.setFormattedOutput(true);
			return config;
		}
	}
	
	

}
