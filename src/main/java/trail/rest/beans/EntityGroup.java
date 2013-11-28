package trail.rest.beans;

import java.util.ArrayList;
import java.util.List;

public class EntityGroup {
	
	private Long id;
	
	private List<TrailEntity> entities = new ArrayList<TrailEntity> ();
	
	public Long getId () {
		return id;
	}
	
	public void setId (Long id) {
		this.id = id;
	}
	
	public void setEntities (List<TrailEntity> entities) {
		this.entities = entities;
	}
	
	public List<TrailEntity> getEntities () {
		return entities;
	}

}
