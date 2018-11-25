package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import entity.Entity;
import entity.EntityType;
import entity.Position;
import game.Game;
import game.GameState;
import level.Level;
import view.View;
import view.View.Command;

/**
 * Tests for the View class
 * @author Christophe Tran
 *
 */
public class ViewTest {
	
	private View view;
	private Game game;
	private Level one;
	private GameState state;

	/**
	 * Initialize as needed for the upcoming test cases
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		view = new View();
		ArrayList<EntityType> spawnable = new ArrayList<EntityType>();
		spawnable.add(EntityType.ZOMBIE_WALKER);
		spawnable.add(EntityType.ZOMBIE_RUNNER);
		spawnable.add(EntityType.ZOMBIE_CONE);
		one = new Level(10, spawnable);
		state = new GameState(one);
		state.addListener(view);
		game = new Game(state);
		state.addListener(view);
		
	}
	
	/**
	 * Test menu items in the view
	 */
	@Test
	public void testMenuItems() {
		assertEquals("Check that a menu item is set to Restart Game", "Restart Game", view.getStartItem().getText());
		assertEquals("Check that a menu item is set to Cheat code", "Cheat code", view.getCheatItem().getText());
		assertEquals("Check that a menu item is set to Quit", "Quit", view.getQuitItem().getText());
	}
	/**
	 * Test creating and updating the Sun-points label
	 */
	@Test
	public void testSunpointsLabel() {
		assertEquals("Check that sunpoints label was created, and is set to 200", "200", view.getSunLabel().getText());
		state.incrementSunPoints(51);
		assertEquals("Check that sunpoints label is set to 251", "251", view.getSunLabel().getText());
		state.setSunPoints(11);
		assertEquals("Check that sunpoints label is set to 11", "11", view.getSunLabel().getText());
	}
	
	/**
	 * Test creating updating the turns label
	 */
	@Test
	public void testTurnLabel() {
		assertEquals("Check that JLabel turn was created and is set 0", "0", view.getTurnsLabel().getText());
		state.setTurn(99);
		assertEquals("Check that JLabel turn is set to 99", "99", view.getTurnsLabel().getText());
	}
	
	/**
	 * Test creating the command Buttons
	 */
	@Test 
	public void testCommandButtons() {
		assertEquals("Check that first command button is <------", "<------", view.getCommandButton()[0].getText());
		assertEquals("Check that second command button is ------>", "------>", view.getCommandButton()[1].getText());
		assertEquals("Check that second command button is Pot", "Pot", view.getCommandButton()[2].getText());
		assertEquals("Check that second command button is Remove", "Remove", view.getCommandButton()[3].getText());
		assertEquals("Check that second command button is End Turn", "End Turn", view.getCommandButton()[4].getText());
	}
	
	/**
	 * Test creating the plant select buttons
	 */
	@Test
	public void testPlantButtons() {
		for(int i=0; i<View.plantsClickable;i++) {
			assertNotNull("Check that plant buttons were created", view.getCommandButton()[i]);
		}
	}
	
	/**
	 * Test creating the grid buttons
	 */
	@Test
	public void testGridButtons() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				assertNotNull("Check that the board buttons were created", view.getGridButton()[i][j]);
			}
		}
	}
	
	/**
	 * Test drawing a plant on the board
	 */
	@Test
	public void testDrawEntity() {
		state.addEntity(Entity.generateEntity(EntityType.PEASHOOTER, new Position(0,0)));
		assertEquals("Check that drawn peashooter is at position (0,0)","drawable/peashooter_grass.png", view.getGridButton()[0][0].getIcon().toString());
	}
	
	/**
	 * Test removing a plant on the board
	 */
	@Test
	public void testRemoveEntity() {
		state.addEntity(Entity.generateEntity(EntityType.PEASHOOTER, new Position(1,1)));
		state.removeEntity(Entity.generateEntity(EntityType.PEASHOOTER, new Position(1,1)));
		assertEquals("Check that drawn peashooter at position (1,1) is removed","drawable/grass.png", view.getGridButton()[1][1].getIcon().toString());

	}	
	
	/**
	 * Test enabling / disabling plant buttons
	 */
	@Test
	public void testUpdatePlantButtonStatus() {
		view.setSelectedCommand(View.Command.POT);
		view.updatePlantButtonStatus();
		for(int i=0; i<View.plantsClickable; i++) {
			assertTrue("Plant buttons should be enabled if command is Pot", view.getPlantsButton()[i].isEnabled());
		}
		
		view.setSelectedCommand(View.Command.REMOVE);
		view.updatePlantButtonStatus();
		for(int i=0; i<View.plantsClickable; i++) {
			assertFalse("Plant buttons should be disabled if command is Pot", view.getPlantsButton()[i].isEnabled());
		}
	}
	
	/**
	 * Test disabling/enabling command buttons
	 */
	@Test 
	public void testDisableCommandButtons() {
		view.disableCommandButtonStatus();
		for(int i=0;i<View.commandsClickable;i++) {
			assertFalse("Command buttons should be disabled", view.getCommandButton()[i].isEnabled());
		}
		
		view.enableCommandButtonStatus();
		for(int i=0;i<View.commandsClickable;i++) {
			assertTrue("Command buttons should be disabled", view.getCommandButton()[i].isEnabled());
		}
	}
	
}
