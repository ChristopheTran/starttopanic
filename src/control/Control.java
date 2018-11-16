package control;
import java.util.ArrayList;

import entity.*;
import game.Game;
import game.GameState;
import level.Level;
import view.View;
public class Control {
private Game game;
private View view;
	public Control(Game game, View view){
		this.game = game;
		this.view = view;
		addCommandListeners();
		addPlantListeners();
		addGridListeners();
	}
	
	private void addGridListeners() {
		for(int y = 0; y < Level.Y_BOUNDARY; y++) {
			for(int x = 0; x < Level.X_BOUNDARY; x++) {
				view.addGridListener(y, x, new GridListener(new Position(x, y), view, game));
			}
		}
	}
	
	private void addCommandListeners() {
		for(int i = 2; i < 5; i++) {
			view.addCommandListener(i, new CommandListener(View.Command.values()[i-2], view, game));
		}
	}
	
	private void addPlantListeners() {
		for(int i = 0; i < 2; i++) {
			view.addPlantsListener(i, new PlantsListener(EntityType.values()[i], view));
		}
	}
	
	public static void main(String[] args) {
		Level one = new Level(10, new ArrayList<Zombie>());
		GameState state = new GameState(one);
		View view = new View();
		state.addListener(view);
		Game game = new Game(state);
		Control c = new Control(game, view);
	}
}
