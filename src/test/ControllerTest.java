package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import control.Control;
import control.MenuItemListener;
import entity.EntityType;
import entity.Position;
import game.Game;
import game.GameState;
import level.Level;
import view.View;
import view.View.Command;


/**
 * Test cases for the Control class.
 * These test cases are for methods executed in the action methods. 
 * Actions methods triggered from classes: GridListener, MenuItemListener, CommandListener, PlantsListener
 * @author Christophe Tran
 *
 */
public class ControllerTest {
	private Control c;
	private Game game;
	private View view;
	private Level one;
	private GameState state;
	
	/**
	 * Initialize as needed for the upcoming test cases
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		view = new View();
		one = new Level(10, 200);
		one.addZombieType(EntityType.ZOMBIE_WALKER);
		one.addZombieType(EntityType.ZOMBIE_RUNNER);
		one.addZombieType(EntityType.ZOMBIE_CONE);
		one.addPlantType(EntityType.SUNFLOWER);
		one.addPlantType(EntityType.PEASHOOTER);
		one.addPlantType(EntityType.WALNUT);
		one.addPlantType(EntityType.FREEZESHOOTER);
		state = new GameState(one);
		state.addListener(view);
		game = new Game(state);
		c = new Control(game, view);
		view.addMenuItemListener(new MenuItemListener(view, game));
	}

	/**
	 * Test setting the command in the view
	 */
	@Test
	public void testSetCommand() {
		view.setSelectedCommand(View.Command.POT);
		assertEquals("Check that command is set to POT", Command.POT, view.getSelectedCommand());
	}
	
	/**
	 * Test setting the entity type in the view
	 */
	@Test
	public void testSetEntity() {
		view.setSelectedEntity(EntityType.FREEZESHOOTER);
		assertEquals("Check that Entity is set to FRREZESHOOTER", EntityType.FREEZESHOOTER, view.getSelectedEntity());
	}
	
	
	/**
	 * Test for undo() and redo() when triggered from CommandListener class
	 */
	@Test
	public void testCommandListenerUndoRedo() {
		assertEquals("Check the number of plants originally", 0, state.getPlants().size());
		game.endPhase();
		game.potPlant(EntityType.PEASHOOTER, new Position(2,2));
		game.endPhase();
		view.getCommandButton()[0].doClick(); // pressing undo button
		view.getCommandButton()[0].doClick(); // pressing undo button
		assertEquals("Check the number of plants after undo ", 0, state.getPlants().size());
		
		view.getCommandButton()[1].doClick(); //pressing redo button
		assertEquals("Check the number of plants after redo ", 1, state.getPlants().size());
	}
	
	/**
	 * Test for endPhase() when triggered from CommandListener class
	 */
	@Test
	public void testCommandListenerEndPhase() {
		assertTrue("Check initial amount of zombies before turn 1 is 0", state.getZombies().size()==0);
		view.getCommandButton()[4].doClick(); // pressing End button
		assertTrue("Check amount of zombies after turn 0 is greater than 0", state.getZombies().size()>0);
	}
	
	/**
	 * Test for storing plant selected from PlantListener class
	 */
	@Test
	public void testPlantListener() {
		view.getCommandButton()[2].doClick(); // pressing Pot button first (to enable plant selection)
		view.getPlantsButton()[0].doClick(); // pressing Peashooter plant button
		assertEquals("Plant selected should be PEASHOOTER",EntityType.PEASHOOTER,view.getSelectedEntity());
	}
	
	/**
	 * Test for potting a plant triggered from GridListener class
	 */
	@Test 
	public void testGridListenerPotPlant() {
		view.getCommandButton()[2].doClick(); // Select pot command button first
		view.getPlantsButton()[0].doClick(); // select Peashooter plant button
		view.getGridButton()[1][1].doClick(); // Select tile (1,1) on the grid
		assertEquals("Board should have 1 plant planted", 1, state.getPlants().size());
	}
	
	/**
	 * Test for removing a plant triggered from GridListener class
	 */
	@Test
	public void testGridListenerRemovePlant() {
		//pot a plant at (1,1)
		view.getCommandButton()[2].doClick(); 
		view.getPlantsButton()[0].doClick(); 
		view.getGridButton()[1][1].doClick(); 
		assertEquals("Board should have 1 plant planted", 1, state.getPlants().size());
		//remove plant
		view.getCommandButton()[3].doClick(); // Select remove command button 
		view.getGridButton()[1][1].doClick(); 
		assertEquals("Board should have 0 plants", 0, state.getPlants().size());
	}
	
	/**
	 * Test for menu restart option, triggered from MenuItemListener class
	 */
	@Test
	public void testMenuItemListenerRestart() {
		assertTrue("There should be no zombies at turn 0", state.getZombies().size() ==0);
		game.endPhase();
		game.endPhase();
		assertTrue("There should be more than 0 zombies", state.getZombies().size() >0);
		view.getRestartMenu().doClick();
		assertTrue("Game restarted, there should be no zombies", state.getZombies().size() ==0);	
	}
}
