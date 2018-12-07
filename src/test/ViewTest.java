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
import view.LevelBuilderView;
import view.View;

/**
 * Tests for the View class
 * 
 * @author Christophe Tran, Thomas Leung
 *
 */
public class ViewTest {

	private View view;
	private Game game;
	private Level one;
	private GameState state;
	private LevelBuilderView builderView;

	/**
	 * Initialize as needed for the upcoming test cases
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		view = new View();
		one = new Level(10, 200);
		one.addZombieType(EntityType.ZOMBIE_WALKER);
		one.addZombieType(EntityType.ZOMBIE_RUNNER);
		one.addZombieType(EntityType.ZOMBIE_CONE);
		state = new GameState(one);
		state.addListener(view);
		game = new Game(state);
		state.addListener(view);

		builderView = new LevelBuilderView();
	}

	/**
	 * Test menu items in the view
	 */
	@Test
	public void testMenuItems() {
		assertEquals("Check that a menu item is set to Restart Game", "Restart Game", view.getRestartMenu().getText());
		assertEquals("Check that a menu item is set to Cheat code", "Cheat code", view.getCheatMenu().getText());
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
		for (int i = 0; i < View.plantsClickable; i++) {
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
		state.addEntity(Entity.generateEntity(EntityType.PEASHOOTER, new Position(0, 0)));
		assertEquals("Check that drawn peashooter is at position (0,0)", "drawable/peashooter_grass.png",
				view.getGridButton()[0][0].getIcon().toString());
	}

	/**
	 * Test removing a plant on the board
	 */
	@Test
	public void testRemoveEntity() {
		state.addEntity(Entity.generateEntity(EntityType.PEASHOOTER, new Position(1, 1)));
		state.removeEntity(Entity.generateEntity(EntityType.PEASHOOTER, new Position(1, 1)));
		assertEquals("Check that drawn peashooter at position (1,1) is removed", "drawable/grass.png",
				view.getGridButton()[1][1].getIcon().toString());

	}

	/**
	 * Test disabling/enabling command buttons
	 */
	@Test
	public void testDisableCommandButtons() {
		view.disableCommandButtonStatus();
		for (int i = 0; i < View.commandsClickable; i++) {
			assertFalse("Command buttons should be disabled", view.getCommandButton()[i].isEnabled());
		}

		view.enableCommandButtonStatus();
		for (int i = 0; i < View.commandsClickable; i++) {
			assertTrue("Command buttons should be disabled", view.getCommandButton()[i].isEnabled());
		}
	}

	/**
	 * Test loadLevel method
	 */
	@Test
	public void testLoadLevel() {
		builderView.setSunSpinner(100);
		int sunpoints = (int) builderView.getSunpointsSpinner().getValue();
		assertEquals("Check if sun point set to 100", 100, sunpoints);
	}

	/**
	 * Test level builder button
	 */
	@Test
	public void testLevelBuilderButton() {
		assertEquals("Check if it is the correct zobie", "drawable/zombie_walker.png",
				builderView.getZombieWalker().getIcon().toString());
		assertEquals("Check if it is the correct zobie", "drawable/zombie_runner.png",
				builderView.getZombieRunner().getIcon().toString());
		assertEquals("Check if it is the correct zobie", "drawable/zombie_cone.png",
				builderView.getZombieCone().getIcon().toString());
	}

	/**
	 * Check if plants are clicked when they are selected in the level builder
	 */
	@Test
	public void testPlantClick() {
		builderView.getPeashooter().setSelected(true);
		assertTrue("", builderView.getPeashooter().isSelected());
		builderView.getFreezeshooter().setSelected(true);
		assertTrue("", builderView.getFreezeshooter().isSelected());
		builderView.getWalnut().setSelected(true);
		assertTrue("", builderView.getWalnut().isSelected());
		builderView.getSunflower().setSelected(true);
		assertTrue("", builderView.getSunflower().isSelected());
	}

}
