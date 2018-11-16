package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.*;
import game.GameState;
import level.*;

class GameStateTest {
	GameState state;
	Level level;
	EntityType sunflower;
	EntityType peashooter;
	EntityType zombie;
	List<Entity> entityToRemove;
	int size;
	Plant sun;
	Zombie zomb ;

	@BeforeEach
	void setUp() throws Exception {
		level = new Level(3, new ArrayList<Zombie>());
		state = new GameState(level);
		sunflower= EntityType.SUNFLOWER;
		peashooter= EntityType.PEASHOOTER;
		zombie= EntityType.ZOMBIE;
		sun = (Plant) Entity.generateEntity(sunflower, new Position(3,3));
		state.addEntity(sun);
		zomb = (Zombie) Entity.generateEntity(zombie, new Position(3,3));	// Add zombie to position that already has a plant
		state.addEntity(zomb);
		entityToRemove = new ArrayList<Entity>();
		size = 0;
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
	}

	@Test
	void testDecrementSunPoints() {
		int val = state.getSunPoints();
		state.decrementSunPoints(100);
		assertTrue("Check that the sunpoints have decremented by 100 correctly",state.getSunPoints()==(val-100));
	}
	
	@Test
	void testIncrementSunPoints() {
		int val = state.getSunPoints();
		state.incrementSunPoints(100);
		assertTrue("Check that the sunpoints have decremented by 100 correctly",state.getSunPoints()==(val+100));
	}
	
	@Test
	void testIncrementTurn() {
		int val = state.getTurn();
		state.incrementTurn();
		assertTrue("Check that the turn has been incremented",state.getTurn()==(val+1));
	}
	
	@Test
	void testaddEntity() {
		size = state.getEntities().size();
		Plant plant = (Plant) Entity.generateEntity(sunflower, new Position(2,2));
		state.addEntity(plant);
		assertTrue("Check that an entity plant was added to the entity list", state.getEntities().size() > size);
		size = state.getEntities().size();
		Zombie zomb = (Zombie) Entity.generateEntity(zombie, new Position(3,3));
		state.addEntity(zomb);		
		assertTrue("Check that an entity zombie was added to the entity list", state.getEntities().size() > size);
	}
	
	@Test
	void testRemoveEntity() {
		assertTrue("Check that the entity list has atleast 1 entity", state.getEntities().size() > 0);
		state.removeEntity(sun);
		assertTrue("Check that the sunflower entity was removed", state.getEntities().size() == 1);
		state.removeEntity(zomb);
		assertTrue("Check that the zombie entity was removed", state.getEntities().size() == 0);
	}
	
	@Test
	void testCheckCollision() {
		Plant plant = (Plant) Entity.generateEntity(sunflower, new Position(3,3));
		state.addEntity(plant);
		assertTrue("Check that a plant collides with another plant at that position", sun.getPosition().equals(plant.getPosition()));
	}
	
	@Test
	void testgetPlants() {
		assertTrue("Check that two entities are on the same spot", state.getPlants().size() == 1);
		Plant plant = (Plant) Entity.generateEntity(peashooter, new Position(0,1));
		state.addEntity(plant);
		assertTrue("Check that two entities are on the same spot", state.getPlants().size() == 2);

	}
	
	@Test
	void testgetZombies() {
		assertTrue("Check that two entities are on the same spot", state.getZombies().size() == 1);
		Zombie zombie2 = (Zombie) Entity.generateEntity(zombie, new Position(0,1));
		state.addEntity(zombie2);
		assertTrue("Check that two entities are on the same spot", state.getZombies().size() == 2);
	}
	
	@Test
	void testIsGameOver() {
		//Check if player wins
		state.removeEntity(zomb); 	//remove all zombies from board to meet win condition
		//increment turns to complete waves for win condition
		state.incrementTurn();
		state.incrementTurn();
		state.incrementTurn();
		state.incrementTurn();
		state.incrementTurn();
		
		assertTrue("Check that when all zombies are cleared from the board game is won", state.isGameOver());
		
		// Check if zombies win
		Zombie zombie1 = (Zombie) Entity.generateEntity(zombie, new Position(0,1));
		state.addEntity(zombie1);
		assertTrue("Check that game is over when zombie reaches the left most side", state.isGameOver());
	}
	

}
