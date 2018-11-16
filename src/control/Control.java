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
	public Control(){
		Level one = new Level(10, new ArrayList<Zombie>());
		GameState state = new GameState(one);
		game = new Game(state);
		view = new View();
		addGridListeners();
		addCommandListeners();
		addPlantListeners();
	}
	
	private void addGridListeners() {
		for(int y = 0; y < Level.Y_BOUNDARY; y++) {
			for(int x = 0; x < Level.X_BOUNDARY; x++) {
				view.addGridListener(y, x, new GridListener(new Position(x, y), view, game));
			}
		}
	}
	
	private void addCommandListeners() {
		for(int i = 2; i < 4; i++) {
			view.addCommandListener(i, new CommandListener(View.Command.values()[i-2], view));
		}
	}
	
	private void addPlantListeners() {
		for(int i = 0; i < 2; i++) {
			view.addCommandListener(i, new PlantsListener(EntityType.values()[i], view));
		}
	}
}
