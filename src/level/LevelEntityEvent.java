package level;

import java.util.EventObject; 
import entity.EntityType;

public class LevelEntityEvent extends EventObject{
	private EntityType entity;
	private String walkerPercentage;
	private String runnerPercentage;
	private String conePercentage;
	/**
	 * Constructor for LevelPointEvent
	 * @param state The current game state
	 * @param entity The entity that triggered the event
	 */
	public LevelEntityEvent(LevelBuilder levelBuilder, EntityType entity) {
		super(levelBuilder);
		this.entity = entity;
		walkerPercentage = levelBuilder.getPercentage(EntityType.ZOMBIE_WALKER);
		runnerPercentage = levelBuilder.getPercentage(EntityType.ZOMBIE_RUNNER);
		conePercentage = levelBuilder.getPercentage(EntityType.ZOMBIE_CONE);
	}
	
	/**
	 * Get the entity
	 * @return The entity
	 */
	public EntityType getEntity() {
		return entity;
	}
	
	public String getWalkerPercentage() {
		return walkerPercentage;
	}
	
	public String getRunnerPercentage() {
		return runnerPercentage;
	}
	
	public String getConePercentage() {
		return conePercentage;
	}
}