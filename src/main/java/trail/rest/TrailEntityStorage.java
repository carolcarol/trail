package trail.rest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import trail.rest.beans.TrailEntity;

public class TrailEntityStorage {
	private static final AtomicLong entityCounter = new AtomicLong (0);

	public static final Map<Long, TrailEntity> entities = new HashMap<Long, TrailEntity> ();

	public static TrailEntity addTrailEntity(final TrailEntity entity) {
		long id = entityCounter.incrementAndGet ();
		entity.setId (id);
		entities.put (id, entity);
		return entity;
	}
	
//	public static TrailEntity updateTrailEntity(final Long id, final TrailEntity entity) {
//		TrailEntity origEntity = entities.get (id);
//	}
}
