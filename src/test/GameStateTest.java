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
/**
 * Test class for the GameState class
 * @author Rahul Anilkumar, Christophe Tran, Christopher Wang, Thomas Leung
 *
 */
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

	/**
	 * Initialize common values for test cases
	 * @throws Exception throw an exception if one occurs
	 */
	@BeforeEach
	void setUp() throws Exception {
		ArrayList<EntityType> spawnable = new ArrayList<EntityType>();
		spawnable.add(EntityType.ZOMBIE_WALKER);
//		spawnable.add(EntityType.ZOMBIE_RUNNER);
//		spawnable.add(EntityType.ZOMBIE_CONE);
		Level one = new Level(3, spawnable);
//		level = new Level(3, new ArrayList<Zombie>());
		state = new GameState(level);
		sunflower= EntityType.SUNFLOWER;
		peashooter= EntityType.PEASHOOTER;
		zombie= EntityType.ZOMBIE_WALKER;
		sun = (Plant) Entity.generateEntity(sunflower, new Position(3,3));
		state.addEntity(sun);
		zomb = (Zombie) Entity.generateEntity(zombie, new Position(3,3));	// Add zombie to position that already has a plant
		state.addEntity(zomb);
		entityToRemove = new ArrayList<Entity>();
		size = 0;
		
	}

	/**
	 * Tests the decrementSunPoints functionality to check that the sunpoints have decreased
	 */
	@Test
	void testDecrementSunPoints() {
		int val = state.getSunPoints();
		state.decrementSunPoints(100);
		assertTrue("Check that the sunpoints have decremented by 100 correctly",state.getSunPoints()==(val-100));
	}
	
	/**
	 * Tests the incrementSunPoints functionality to check that the sunpoints have increased
	 */
	@Test
	void testIncrementSunPoints() {
		int val = state.getSunPoints();
		state.incrementSunPoints(100);
		assertTrue("Check that the sunpoints have decremented by 100 correctly",state.getSunPoints()==(val+100));
	}
	
	/**
	 * Tests the incrementTurn method to check that the turn number has increased by 1
	 */
	@Test
	void testIncrementTurn() {
		int val = state.getTurn();
		state.incrementTurn();
		assertTrue("Check that the turn has been incremented",state.getTurn()==(val+1));
	}
	
	/**
	 * Check that entities can be added by the addEntity method
	 */
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
	
	/**
	 * Check that the entities can be removed by the removeEntity method
	 */
	@Test
	void testRemoveEntity() {
		assertTrue("Check that the entity list has atleast 1 entity", state.getEntities().size() > 0);
		state.removeEntity(sun);
		assertTrue("Check that the sunflower entity was removed", state.getEntities().size() == 1);
		state.removeEntity(zomb);
		assertTrue("Check that the zombie entity was removed", state.getEntities().size() == 0);
	}
	
	/**
	 * Check that a plant can not be planted on a place that already has a plant
	 */
	@Test
	void testCheckCollision() {
		Plant plant = (Plant) Entity.generateEntity(sunflower, new Position(3,3));
		state.addEntity(plant);
		assertTrue("Check that a plant collides with another plant at that position", sun.getPosition().equals(plant.getPosition()));
	}
	
	/**
	 * check that a list of plants is returned by the getPlants method
	 */
	@Test
	void testgetPlants() {
		assertTrue("Check that two entities are on the same spot", state.getPlants().size() == 1);
		Plant plant = (Plant) Entity.generateEntity(peashooter, new Position(0,1));
		state.addEntity(plant);
		assertTrue("Check that two entities are on the same spot", state.getPlants().size() == 2);

	}
	
	/**
	 * check that the list of zombies is returned by the getZombies method
	 */
	@Test
	void testgetZombies() {
		assertTrue("Check that two entities are on the same spot", state.getZombies().size() == 1);
		Zombie zombie2 = (Zombie) Entity.generateEntity(zombie, new Position(0,1));
		state.addEntity(zombie2);
		assertTrue("Check that two entities are on the same spot", state.getZombies().size() == 2);
	}
	
	/**
	 * Test the game over method to check that the game ends when zombie reaches left most side or when player beats all zombies
	 */
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
