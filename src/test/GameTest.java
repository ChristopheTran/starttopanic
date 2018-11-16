package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entity.*;
import game.Game;
import game.GameState;
import level.*;
/**
 * Test class for the Game class of the model. It checks all public methods to ensure that the functionality is as intended
 * @author Rahul Anilkumar, Christophe Tran, Christopher Wang, Thomas Leung
 * @version 1.0
 *
 */
public class GameTest {
	private Game game;
	private GameState state;
	private Level one;
	private Zombie zombie,zombie2;
	private EntityType sunflower, peashooter;
	int size;

	/**
	 * Initialize common values for test cases
	 * @throws Exception throw an exception if one occurs
	 */
	@Before
	public void setUp() throws Exception {
		one = new Level(1, new ArrayList<Zombie>());
		state = new GameState(one);
		game = new Game(state);
		sunflower= EntityType.SUNFLOWER;
		peashooter = EntityType.PEASHOOTER;
		game.potPlant(sunflower, new Position(1,1));
		zombie = new Zombie(55, 5, "Zombie", new Position(1,1), 1);
		zombie2 = new Zombie(55, 5, "Zombie", new Position(3,3), 1);
		state.addEntity(zombie);
		state.addEntity(zombie2);
		size = state.getEntities().size();
		
	}

	/**
	 * Test the PotPlant method functionality
	 */
	@Test
	public void testPotPlant() {
		game.potPlant(sunflower, new Position(1,1));
		assertEquals("Check that no entities were added to the entities list", state.getEntities().size(), size);
		game.potPlant(peashooter, new Position(1,2));
		size = state.getEntities().size();
		game.potPlant(peashooter, new Position(1,3));
		assertEquals("Check that plant isnt added if player doesnt have enough points",state.getEntities().size(),size);
		game.sunshinePhase();	// Get more sun points to test remaining functionality
		int testChecker = state.getSunPoints();	// get more sunpoints for further testing
		size = state.getEntities().size();
		game.potPlant(peashooter, new Position(2,2));
		assertTrue("Check that a plant was successfully planted at the indicated position", state.getEntities().size()> size);
		assertEquals("Check that the sunpoints has been subtracted",testChecker-100,state.getSunPoints());
	}

	/**
	 * Test the removePlant method
	 */
	@Test
	public void testRemovePlant() {
		assertEquals("Check that the existing entity list has entities", state.getPlants().size(),1);	
		game.removePlant(state.getPlants().get(0).getPosition());
		assertTrue("Check that the existing plant list has no entities", state.getPlants().size()==0);
	}

	/**
	 * Tests the getSunchine method functionality
	 */
	@Test
	public void testGetSunshine() {
		assertTrue("Check that sunshine is gathered", game.getSunshine()>0);
		game.removePlant(new Position(1,1));
		assertTrue("Check that no sunshine is being spawned from plants",game.getSunshine()==0);
		
	}

	/**
	 * Tests the sunshinePhase method functionality
	 */
	@Test
	public void testSunshinePhase() {
		game.removePlant(new Position(1,1));
		game.sunshinePhase();
		assertTrue("Ensure that sunshine is formed at the beginging of the turn without a flower",200<state.getSunPoints());
	}
	
	/**
	 * Test the endPhase method functionality
	 */
	@Test
	public void testEndPhase() {
		game.endPhase();
		assertTrue("Check that game is over", state.isGameOver());		
	}

	/**
	 * test the movePhase method for functionality
	 */
	@Test
	public void testMovePhase() {
		int x = 1;
		int x2 = zombie2.getX();
		zombie.setX(2);
		game.movePhase();
		assertFalse("Check that zombie doesnt move past plant", x == zombie.getX());
		assertTrue("Check if the zombie has moved along the x axis", x2 != zombie2.getX());
		
	}

	/**
	 * Test the spawnWave method to ensure that the zombie waves are spawned
	 */
	@Test
	public void testSpawnWave() {
		game.spawnWave();
		int numZombieSpawn=0;	// keep count of num of Zombies spawned
		for(Entity entity: state.getEntities()) {
			if(entity instanceof Zombie) {
				numZombieSpawn += 1;
			}
		}
		numZombieSpawn -= 2; // account for 2 zombies already in the entity list
		assertTrue("Check if the number of zombies spawned are within the intended limit of 0-2", numZombieSpawn>=0 && numZombieSpawn<3 );
		
	}

}
