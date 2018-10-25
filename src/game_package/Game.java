package game_package;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Random;

import level_package.*;
import entity_package.*;

public class Game {
	
	private Level level;
	private int sunPoints;
	private int numSunSpawn;
	private int gamePhase;
	
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
	 * The level that is being played
	 * @return the current level
	 */
	public Level getlevel() {
		return level;
	}
	
	/**
	 * Adds the p to the gameboard and checks conditions
	 * @param name the name of the p taken as input
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
	
	private void plantAttack() {
		List<Peashooter> peashooters = level.getPlants().stream()
				.filter(entity-> entity instanceof Peashooter)
				.map(p -> (Peashooter) p)
				.collect(Collectors.toList());
		for(Peashooter p : peashooters) {
			int lane = p.getPosition().getY();
			Zombie zombieToBeAttacked = level.getZombies().stream()
					// get all zombies on same lane as peashooter, on same tile or to the right of the peashooter
					.filter(entity -> entity.getPosition().getY()==lane && entity.getPosition().getX() >= p.getPosition().getX()) 
					.min(Comparator.comparing(Zombie::getX)) 	// get the closest zombie to the peashooter
					.orElse(null);							// if no zombies on a lane of a peashooter, return null
			if(zombieToBeAttacked != null) {
				zombieToBeAttacked.setHealth(zombieToBeAttacked.getHealth() - p.getAttack());
				if(zombieToBeAttacked.getHealth() <= 0) {
					level.getZombies().remove(zombieToBeAttacked);
				}
			}
		}
		
	}
	
	private void zombieAttack() {
		for(Zombie z : zombieCollision()) {
			for(Plant p: level.getPlants()) {
				if(z.getPosition().equals(p.getPosition())) {
					p.setHealth(p.getHealth() - z.getAttack());
					if(p.getHealth() < 0) {
						level.getPlants().remove(p);
					}
				}
			}
		}
	}
	
	public void movePhase() {
		List<Zombie> zombies = level.getZombies();
		zombies.removeAll(zombieCollision()); 
		for(Zombie z: zombies) {
			z.getPosition().setX(z.getPosition().getX() - z.getMoveSpeed());
		}
	}
	
	private List<Zombie> zombieCollision(){
		List<Zombie> zombies = new ArrayList<Zombie>();
		for(Zombie z:  level.getZombies()) {
			for(Plant p: level.getPlants()) {
				if(z.getPosition().equals(p.getPosition())){
					zombies.add(z);
				}
			}
		}
		return zombies;
	}
	
	/**
	 *
	 * @return True if the game continues, false otherwise
	 */
	public boolean endPhase() {
		//Determine if zombies have won
		for(Zombie z: level.getZombies()) {
			if(z.getPosition().getX() < 0) {
				return false;
			}
		}
		//Determine if the user has won
		if(level.getWaves() == 0) {
			return false;
		}
		
		//The game continues, spawn zombies and decrement waves
		level.setWaves(level.getWaves() - 1);
		level.spawnWave();
		return true;
	}
}

