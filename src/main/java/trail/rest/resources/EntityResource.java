package trail.rest.resources;

import java.util.Collection;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import trail.rest.TrailEntityStorage;
import trail.rest.beans.TrailEntity;

@Path("entities")
@Produces("application/json")
public class EntityResource {

	@POST
	@Consumes("application/json")
	public TrailEntity addTrailEntity (@NotNull @Valid final TrailEntity entity) {
		TrailEntity addTrailEntity = TrailEntityStorage.addTrailEntity (entity);
//		   URI uri = UriBuilder.fromResource (EntityResource.class).build (addTrailEntity);
//		return Response.created (uri).build ();
		return addTrailEntity;
	}
	
	@GET
	public Collection<TrailEntity> getEntities(){
		return TrailEntityStorage.entities.values ();
	}
	
	@GET
	@Path("{id}")
	public TrailEntity getEntity (@PathParam (value = "id") final Long id) {
		 return TrailEntityStorage.entities.get(id);
	}
	
	@PUT
	@Path("{id}")
	@Consumes("application/json")
	public TrailEntity updateEntity(@PathParam (value = "id") final Long id, final TrailEntity entity){
		TrailEntity updatedTrailEntity = TrailEntityStorage.updateTrailEntity (id, entity);
		return updatedTrailEntity;
	}
	
	@DELETE
	@Path("{id}")
	public void deleteEntity(@PathParam(value = "id") Long id) {
		TrailEntityStorage.entities.remove(id);
	}
	
}
