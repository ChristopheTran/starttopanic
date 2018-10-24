package game_package;

import level_package.Level;

import java.util.List;

import entity_package.Entity;
import entity_package.Sunflower;

public class Game {
	
	Level level;
	int sunPoints;
	int numSunSpawn;
	int gamePhase;
	List<Entity> entity;
		
	
	/**
	 * Gathers sunshine from all sunflowers and the randomly spawned sunshine to the total count
	 */
	public void sunshinePhase() {
		System.out.println("In Progress... Gathering Sunshine!");
		if(numSunSpawn !=0) {
			sunPoints += 25;
		}
		for(Entity x: entity) {
			if(x instanceof Sunflower) {
				sunPoints += 25;
			}
		}
	}
	
	public void userPhase() {
		
		
	}
	
	
}
