package trail.rest;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.ArrayList;
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
import org.junit.Test;

import trail.rest.beans.Device;
import trail.rest.beans.TrailEntity;

public class TrailApplicationTest extends JerseyTest {

	private static final String URI_ENTITY = "entities";

	final static TrailEntity ENT1;

	final static TrailEntity ENT2;

	 final static Device DEVICE1;
	 final static Device DEVICE2;
	 
	 final static List<Device> deviceList1 = new  ArrayList<Device>();
	 final static List<Device> deviceList2 = new  ArrayList<Device>();
	 

	static {
		ENT1 = new TrailEntity ();
		ENT1.setFullName ("Eben Ebesiz");
		ENT1.setEmail ("email@email");
		DEVICE1 = new Device();
		
		DEVICE1.setMsisdn (null);
		deviceList1.add(DEVICE1);
		ENT1.setDevices(deviceList1);
		

		ENT2 = new TrailEntity ();
		ENT2.setFullName ("ibin ibisiz");
		ENT2.setEmail ("email2@email2");
		DEVICE2 = new Device();
		DEVICE2.setMsisdn ("654321");
		deviceList2.add(DEVICE2);
		//deviceList2.add(DEVICE1);
		ENT2.setDevices(deviceList2);
	}

	@Override
	protected Application configure () {
		enable (TestProperties.LOG_TRAFFIC);
		enable (TestProperties.DUMP_ENTITY);

		final TrailApplication application = new TrailApplication ();
		application.property (ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
		application.property (ServerProperties.RESOURCE_VALIDATION_IGNORE_ERRORS, false);
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
		
		final Response response = postEntity (ENT1);

		final TrailEntity entity1 = response.readEntity (TrailEntity.class);

		assertEquals (200, response.getStatus ());
		assertEquals (ENT1.getFullName (), entity1.getFullName ());
		//assertEquals (ENT1.getDevices().size(),1);
		//assertEquals (ENT1.getDevices().get(0).getMsisdn(),"123456");
		assertNotNull (entity1.getId ());
	}

	private Response postEntity (final TrailEntity entity) {
		final WebTarget target = target ().path (URI_ENTITY);
		final Response response =
			target.request (MediaType.APPLICATION_JSON_TYPE).post (
				Entity.entity (entity, MediaType.APPLICATION_JSON_TYPE));
		return response;
	}

	@Test
	public void testAddTwoEntities () throws Exception {
		final Response response = postEntity (ENT1);
		final TrailEntity entity1 = response.readEntity (TrailEntity.class);
		assertEquals (200, response.getStatus ());
		assertEquals (ENT1.getFullName (), entity1.getFullName ());
		assertNotNull (entity1.getId ());

		final Response response2 = postEntity (ENT2);
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
		List<TrailEntity> entities = response.readEntity (new GenericType<List<TrailEntity>> () {});
		assertEquals (3, entities.size ()); // 3 because of the ones added in the previous tests
	}
	
	
	@Test
	public void testGetEntitiesSingle() {
		final WebTarget target = target ().path (URI_ENTITY.concat("/1"));
		final Response response = target.request ().get ();
		
		final TrailEntity entity = response.readEntity (TrailEntity.class);
		assertEquals (200, response.getStatus ()); 
		assertEquals(ENT1.getFullName(),entity.getFullName());
	}
	
	@Test
	public void testGetEntitiesSingleNotFound() {
		final WebTarget target = target ().path (URI_ENTITY.concat("/190001"));
		final Response response = target.request ().get ();
		assertEquals (404, response.getStatus ());
	}
	
	@Test
	public void testUpdateEntity() {
		final WebTarget target = target ().path (URI_ENTITY.concat("/1"));
		TrailEntity updatedEntity = new TrailEntity();
		updatedEntity.setFullName("ibini dibinidibini");
		final Response response = target.request (MediaType.APPLICATION_JSON_TYPE).put (Entity.entity (updatedEntity, MediaType.APPLICATION_JSON_TYPE));
		
		final TrailEntity responseEntity = response.readEntity (TrailEntity.class);
		
		assertEquals (200, response.getStatus ());
		assertEquals(updatedEntity.getFullName(),responseEntity.getFullName());
	}

	@Test
	public void testDeleteEntitiesSingle() {
		final WebTarget target = target ().path (URI_ENTITY.concat("/1"));
		final Response response = target.request ().delete();
		assertEquals (204, response.getStatus ()); 
	}
	
	
	
}
