package level;

import java.util.EventObject;

import entity.EntityType;

public class LevelEntityEvent extends EventObject{
	private EntityType entity;
	/**
	 * Constructor for LevelPointEvent
	 * @param state The current game state
	 * @param entity The entity that triggered the event
	 */
	public LevelEntityEvent(LevelBuilder levelBuilder, EntityType entity) {
		super(levelBuilder);
		this.entity = entity;
	}
	
	/**
	 * Get the entity
	 * @return The entity
	 */
	public EntityType getEntity() {
		return entity;
	}
}