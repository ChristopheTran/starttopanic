package control;
import java.util.ArrayList;


import entity.*;
import game.Game;
import game.GameState;
import level.Level;
import view.View;

/**
 * This class is used to instantiate the game.
 * The controller class that keeps track of the game (Model) and the view (View)
 * @author Christophe Tran, Christopher Wang, Rahul Anilkumar, Thomas Leung
 * @Version 1.0
 *
 */
public class Control {
private Game game;
private View view;
	
	/**
	 * Constructor for the Control
	 * @param game The game (model)
	 * @param view The view of the game
	 */
	public Control(Game game, View view){
		this.game = game;
		this.view = view;
		addCommandListeners();
		addPlantListeners();
		addGridListeners();
	}
	
	/**
	 * Adds listeners the board
	 */
	private void addGridListeners() {
		for(int y = 0; y < Level.Y_BOUNDARY; y++) {
			for(int x = 0; x < Level.X_BOUNDARY; x++) {
				view.addGridListener(y, x, new GridListener(new Position(x, y), view, game));
			}
		}
	}
	
	/**
	 * Adds listeners to the command panel (pot, remove, end)
	 */
	private void addCommandListeners() {
		for(int i = 0; i < View.commandsClickable; i++) {
			view.addCommandListener(i, new CommandListener(View.Command.values()[i], view, game));
		}
	}
	
	/**
	 * Adds listeners to the plant selector panel
	 */
	private void addPlantListeners() {
		for(int i = 0; i < View.plantsClickable; i++) {
			view.addPlantsListener(i, new PlantsListener(EntityType.values()[i], view));
		}
	}
	
	public static void main(String[] args) {
		View view = new View();
		view.splashScreen();
		ArrayList<EntityType> spawnable = new ArrayList<EntityType>();
		spawnable.add(EntityType.ZOMBIE_WALKER);
		spawnable.add(EntityType.ZOMBIE_RUNNER);
		spawnable.add(EntityType.ZOMBIE_CONE);
		Level one = new Level(10, spawnable);
		GameState state = new GameState(one);
		state.addListener(view);
		Game game = new Game(state);
		Control c = new Control(game, view);
		view.addMenuItemListener(new MenuItemListener(view, game));
	}
}
