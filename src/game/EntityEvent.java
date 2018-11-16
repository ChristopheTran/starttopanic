package game;

import java.util.EventObject;
import entity.*;
public class EntityEvent extends EventObject{
	private EntityType entity;
	private Position position;
	
	public EntityEvent(GameState state, Entity entity) {
		super(entity);
		this.entity = entity.getEntityType();
		this.position = entity.getPosition();
	}

	public EntityType getEntity() {
		return entity;
	}

	public void setEntity(EntityType entity) {
		this.entity = entity;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

}