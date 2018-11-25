package control;

import java.awt.event.*;

import entity.*;
import game.Game;
import view.View;
/**
 * Listener for the game board and sets actions to it
 * @author Christopher Wang, Christophe Tran, Rahul Anilkumar, Thomas Leung
 * @version 1.0
 */
public class GridListener implements ActionListener{
	private Position position;
	private View view;
	private Game model;
	
	/**
	 * Constructor to initialize variables
	 * @param position the current position on the board
	 * @param view the current view being used
	 * @param model the model being used
	 */
	public GridListener(Position position, View view, Game model) {
		this.position = position;
		this.view = view;
		this.model = model;
	}
	
	/**
	 * The action being performed
	 */
	public void actionPerformed(ActionEvent e) {
		switch(view.getSelectedCommand()) {
		case POT:
			model.potPlant(view.getSelectedEntity(), position);
			break;
		case REMOVE:
			model.removePlant(position);
			break;
		default:
			break;
		}
		
	}
}

