package level;

import java.util.ArrayList;

import entity.EntityType;

public class LevelBuilder {
	private Level level;
	private ArrayList<LevelBuilderListener> listeners;
	
	/**
	 * Constructor for the LevelBuilder Class
	 */
	public LevelBuilder() {
		level = new Level();
		listeners = new ArrayList<LevelBuilderListener>();
	}
	
	/**
	 * Adds a LevelBuilderListener to the LevelBuilder
	 * @param listener The LevelBuilderListener to be added
	 */
	 public void addListener(LevelBuilderListener listener) {
		 listeners.add(listener);
	 }
	
	/**
	 * Adds a plant type to the PlantList
	 * @param plant The type of plant to be added
	 */
	public void addPlantType(EntityType plant) {
		level.addPlantType(plant);
		for(LevelBuilderListener listener: listeners) {
			listener.plantAdded(new LevelEntityEvent(this, plant));
		}
	}
	
	/**
	 * Adds a zombie type to the ZombieList
	 * @param zombie The type of zombie to be added
	 */
	public void addZombieType(EntityType zombie) {
		level.addZombieType(zombie);
		for(LevelBuilderListener listener: listeners) {
			listener.zombieAdded(new LevelEntityEvent(this, zombie));
		}
	}
	
	/**
	 * Sets the number of waves that a player must endure for a Level
	 * @param waves The number of waves to be set
	 */
	public void setWaves(int waves) {
		level.setWaves(waves);
		for(LevelBuilderListener listener: listeners) {
			listener.updateWaves(new LevelPointEvent(this, waves, level.getInitialSunPoints()));
		}
	}
	
	/**
	 * Retrieves the number of sun points that a player begins with
	 * @param initialSunPoints The number of beginning sun points to be set
	 */
	public void setInitialSunPoints(int initialSunPoints) {
		level.setInitialSunPoints(initialSunPoints);
		for(LevelBuilderListener listener: listeners) {
			listener.updateInitialSunPoints(new LevelPointEvent(this, level.getWaves(), initialSunPoints));
		}
	}
	
	/**
	 * Saves the newly initialized level as an XML file
	 * @param fileName The name of the file to be saved
	 */
	public void saveLevel(String fileName) {
		level.exportToXMLFile(fileName);
	}
}
