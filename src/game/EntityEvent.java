package game;

import java.util.EventObject;

import entity.*;
/**
 * An event object that is generated when dealing with an entity (plant, zombie)
 */
public class EntityEvent extends EventObject{
	private EntityType entity;
	private Position position;
	
	/**
	 * Constructor for EntityEvent
	 * @param state The current game state
	 * @param entity The entity that triggered the event
	 */
	public EntityEvent(GameState state, Entity entity) {
		super(entity);
		this.entity = entity.getEntityType();
		this.position = entity.getPosition();
	}
	
	/**
	 * Get the entity
	 * @return The entity
	 */
	public EntityType getEntity() {
		return entity;
	}
	
	/**
	 * Store the entity type
	 * @param entity The entity to be set to
	 */
	public void setEntity(EntityType entity) {
		this.entity = entity;
	}
	
	/**
	 * Get the position of the entity
	 * @return Position
	 */
	public Position getPosition() {
		return position;
	}
	
	/**
	 * Set the position of the entity
	 * @param position The new position to be set to
	 */
	public void setPosition(Position position) {
		this.position = position;
	}

}