package game_package;
import java.util.*;
import java.util.stream.Collectors;

import level_package.*;
import entity_package.*;

public class Game {
	
	Level level;
	int sunPoints;
	int numSunSpawn;
	int gamePhase;
	Collection<Entity> entity;
	
	/**
	 * Constructor
	 * @param level the level being played
	 */
	public Game(Level level) {
		this.level = level;

		sunPoints = 100;
	}
	
	/**
	 * getter for the sunPoints
	 * @return int with sunpoints
	 */
	public int getSunPoints() {
		return sunPoints;
	}
	
	/**
	 * The entities in the level
	 * @return
	 */
	public Collection<Entity> getEntities(){
		return entity;
	}
	
	/**
	 * The level that is being played
	 * @return the current level
	 */
	public Level getlevel() {
		return level;
	}
	
	/**
	 * Adds the plant to the gameboard and checks conditions
	 * @param name the name of the plant taken as input
	 * @param x the x position
	 * @param y the y position
	 */
	
	public void addEntity(String name, int x, int y) {
	}
	
	/**
	 * Gathers sunshine from all sunflowers and the randomly spawned sunshine to the total count
	 */
	public void sunshinePhase() {
	}
	
	
	public void userPhase() {
	}
	

	public static void main(String[] args) {
	}
	
	
	private void zombieAttack() {
		List<Zombie> zombies = zombieCollision();
	}
	
	public void movePhase() {
		List<Zombie> zombies = level.getZombies();
		zombies.removeAll(zombieCollision());
		for(Zombie zombie: zombies) {
			zombie.getPosition().setX(zombie.getPosition().getX() - 1);
		}
	}
	
	private List<Zombie> zombieCollision(){
		List<Zombie> zombies = new ArrayList<Zombie>();
		for(Zombie zombie:  level.getZombies()) {
			for(Plant plant: level.getPlants()) {
				if(zombie.getPosition().equals(plant.getPosition())){
					zombies.add(zombie);
				}
			}
		}
		return zombies;
	}
	
	public void endPhase() {
		
	}
}

