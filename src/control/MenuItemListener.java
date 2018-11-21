package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;

import entity.EntityType;
import entity.Zombie;
import game.Game;
import game.GameState;
import level.Level;
import view.View;

public class MenuItemListener implements ActionListener{

	private View view;
	private Game model;
	
	/**
	 * constructor for the listener
	 * @param type the type of entity being passed
	 * @param view the view that is being used by the game
	 */
	public MenuItemListener(View view, Game model) {
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (view.getRestartMenu() == (JMenuItem) object) {
			System.out.println("haha");
			Level one = new Level(10, new ArrayList<Zombie>());
			GameState state = new GameState(one);
			state.addListener(view);
			Control c = new Control(model, view);
			model.getGameState().replace(state);
			model.resetGame();
		} else if (view.getCheatMenu() == (JMenuItem) object) {
			System.out.println("hehe");
		}
		
	}

}
