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

public class GameTest {
	private Game game;
	private GameState state;
	private Level one;
	private Zombie zombie,zombie2;

	@Before
	public void setUp() throws Exception {
		one = new Level(3, new ArrayList<Zombie>());
		state = new GameState(one);
		game = new Game(state);
		game.potPlant("sunflower", new Position(1,1));
		zombie = new Zombie(55, 5, "Zombie", new Position(1,1), 1);
		zombie2 = new Zombie(55, 5, "Zombie", new Position(3,3), 1);
		state.addEntity(zombie);
		state.addEntity(zombie2);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPotPlant() {
		assertFalse("Check that false is returned if the indicated position is occupied", game.potPlant("sunflower", new Position(1,1)));
		assertFalse("Check that false is returned if player doesnt have enough points",game.potPlant("peashooter", new Position(1,2)));
		game.sunshinePhase();	// Get more sun points to test remaining functionality
		assertFalse("Check that the name of the plant is valid", game.potPlant("shooter", new Position(1,2)));
		int testChecker = state.getSunPoints();
		game.potPlant("peashooter", new Position(2,2));
		assertTrue("Check that a plant was successfully planted at the indicated position", game.getPlantAtPosition(new Position(2,2)) != null);
		assertEquals("Check that the sunpoints has been subtracted",testChecker-100,state.getSunPoints());
	}

	@Test
	public void testGetPlantAtPosition() {
		assertEquals("Check to make sure that an empty position returns a null value",null,game.getPlantAtPosition(new Position(1,0)));
		assertTrue("Check to make sure that a position with a plant does not return null", game.getPlantAtPosition(new Position(1,1)) != null);
		assertTrue("Check to make sure that a plant at a valid position returns an object of class Entity", game.getPlantAtPosition(new Position(1,1)) instanceof Entity);
		assertTrue("Check to make sure that a plant at a valid position returns an object of class Plant", game.getPlantAtPosition(new Position(1,1)) instanceof Plant);
		assertFalse("Check to make sure that the returned object is not of type Zombie", game.getPlantAtPosition(zombie.getPosition()) instanceof Zombie);
	}

	@Test
	public void testGetSunshine() {
		assertTrue("Check that sunshine is gathered", game.getSunshine()>0);
		state.removeEntity(game.getPlantAtPosition(new Position(1,1)));
		assertTrue("Check that no sunshine is being spawned from plants",game.getSunshine()==0);
		
	}

	@Test
	public void testSunshinePhase() {
		state.removeEntity(game.getPlantAtPosition(new Position(1,1)));
		game.sunshinePhase();
		assertTrue("Ensure that sunshine is formed at the beginging of the turn without a flower",50<state.getSunPoints());
	}

	@Test
	public void testMovePhase() {
		int x = zombie.getX();
		int x2 = zombie2.getX();
		game.movePhase();
		assertFalse("Check that zombie doesnt move past plant", x != zombie.getX());
		assertTrue("Check if the zombie has moved along the x axis", x2 != zombie2.getX());
		
	}

	@Test
	public void testEndPhase() {
		//Check if player wins
		state.removeEntity(zombie); 	//remove all zombies from board to meet win condition
		state.removeEntity(zombie2); 	//remove all zombies from board to meet win condition
		//increment turns to complete waves for win condition
		state.incrementTurn();
		state.incrementTurn();
		state.incrementTurn();
		state.incrementTurn();
		state.incrementTurn();
		
		assertFalse("Check that when all zombies are cleared from the board game is won", game.endPhase());
		
		// Check if zombies win
		Zombie zombie1 = new Zombie(55, 5, "Zombie", new Position(0,1), 5);
		zombie1.setX(-1); 	// manually move zombie on the x axis left past last column
		state.addEntity(zombie1);
		assertFalse("Check that game is over", game.endPhase());
	
	}

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
