package trail.rest;

import javax.ws.rs.ext.ContextResolver;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class TrailApplication extends ResourceConfig{
	
	public TrailApplication () {
		  // Resources.
        packages(EntityResource.class.getPackage().getName());

        // Providers - JSON.
        register(MoxyJsonFeature.class);
        register(JacksonFeature.class);
        //register(JsonConfiguration.class);
	}

	 /**
     * Configuration for {@link org.eclipse.persistence.jaxb.rs.MOXyJsonProvider} - outputs formatted JSON.
     */
    public static class JsonConfiguration implements ContextResolver<MoxyJsonConfig> {
        public MoxyJsonConfig getContext(final Class<?> type) {
            final MoxyJsonConfig config = new MoxyJsonConfig();
            config.setFormattedOutput(true);
            return config;
        }
    }
}
