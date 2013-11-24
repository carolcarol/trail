package trail.rest;

import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import trail.rest.beans.TrailEntity;

@Path("entities")
@Produces("application/json")
public class EntityResource {

	@POST
	@Consumes("application/json")
	public TrailEntity addTrailEntity (@NotNull @Valid final TrailEntity entity) {
		return TrailEntityStorage.addTrailEntity (entity);
	}
	
	@GET
	public Collection<TrailEntity> getEntities(){
		return TrailEntityStorage.entities.values ();
	}
	
}
