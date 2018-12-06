package level;

import java.util.EventObject;

import game.GameState;

public class LevelPointEvent extends EventObject{
	private int initialSunPoints;
	private int waves;
	/**
	 * Constructor for LevelPointEvent
	 * @param state The current game state
	 * @param entity The entity that triggered the event
	 */
	public LevelPointEvent(LevelBuilder levelBuilder, int waves, int initialSunPoints) {
		super(levelBuilder);
		this.waves = waves;
		this.initialSunPoints = initialSunPoints;
	}
	
	/**
	 * Returns the initial sun points of the level
	 * @return the value of initial sun points
	 */
	public int getInitialSunPoints() {
		return initialSunPoints;
	}
	
	/**
	 * Returns the number of waves of a level
	 * @return the number of waves
	 */
	public int getTurn() {
		return waves;
	}
}
