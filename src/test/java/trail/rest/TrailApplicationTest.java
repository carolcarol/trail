package trail.rest;

import java.net.URI;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.test.external.ExternalTestContainerFactory;
import static org.junit.Assert.*;
import org.junit.Test;

import trail.rest.beans.TrailEntity;

public class TrailApplicationTest extends JerseyTest {

	private static final String URI_ENTITY = "entities";

	final static TrailEntity ENT1;

	final static TrailEntity ENT2;

	static {
		
		
		ENT1 = new TrailEntity ();
		ENT1.setFullName ("Eben Ebesiz");
		ENT1.setMsisdn ("123456");
		ENT1.setEmail ("email@email");

		ENT2 = new TrailEntity ();
		ENT2.setFullName ("ibin ibisiz");
		ENT2.setMsisdn ("654321");
		ENT2.setEmail ("email2@email2");
	}

	@Override
	protected Application configure () {
		enable (TestProperties.LOG_TRAFFIC);
		enable (TestProperties.DUMP_ENTITY);

		final TrailApplication application = new TrailApplication ();
		application.property (ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		return application;
	}

	@Override
	protected void configureClient (final ClientConfig config) {
		super.configureClient (config);

		config.register (MoxyJsonFeature.class);
	}

	@Override
	protected URI getBaseUri () {
		final UriBuilder baseUriBuilder = UriBuilder.fromUri (super.getBaseUri ()).path ("trail");
		final boolean externalFactoryInUse =
			getTestContainerFactory () instanceof ExternalTestContainerFactory;
		return externalFactoryInUse ? baseUriBuilder.path ("api").build () : baseUriBuilder
			.build ();
	}

	@Test
	public void testAddSingleEntity () throws Exception {
		
		final Response response = addEntity (ENT1);

		final TrailEntity entity1 = response.readEntity (TrailEntity.class);

		assertEquals (200, response.getStatus ());
		assertEquals (ENT1.getFullName (), entity1.getFullName ());
		assertNotNull (entity1.getId ());
	}

	private Response addEntity (final TrailEntity entity) {
		final WebTarget target = target ().path (URI_ENTITY);
		final Response response =
			target.request (MediaType.APPLICATION_JSON_TYPE).post (
				Entity.entity (entity, MediaType.APPLICATION_JSON_TYPE));
		return response;
	}

	@Test
	public void testAddTwoEntities () throws Exception {
		final Response response = addEntity (ENT1);
		final TrailEntity entity1 = response.readEntity (TrailEntity.class);
		assertEquals (200, response.getStatus ());
		assertEquals (ENT1.getFullName (), entity1.getFullName ());
		assertNotNull (entity1.getId ());

		final Response response2 = addEntity (ENT2);
		final TrailEntity entity2 = response2.readEntity (TrailEntity.class);
		assertEquals (200, response2.getStatus ());
		assertEquals (ENT2.getFullName (), entity2.getFullName ());
		assertNotNull (entity2.getId ());
	}

	@Test
	public void testGetEntities () {
		final WebTarget target = target ().path (URI_ENTITY);
		final Response response = target.request ().get ();
		assertEquals (200, response.getStatus ());
		List<TrailEntity> entities = response.readEntity (new GenericType<List<TrailEntity>> () {
		});
		assertEquals (3, entities.size ());
	}
}
