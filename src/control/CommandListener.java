package control;
import java.awt.event.*;

import game.Game;
import view.View;
/**
 * Listener for the Command panel (Pot, Remove, End)
 * @author Christophe Tran, Christopher Wang, Rahul Anilkumar, Thomas Leung
 * @version 1
 *
 */
public class CommandListener implements ActionListener{
	private View.Command type;
	private View view;
	private Game model;
	/**
	 * Constructor for the CommandListener 
	 * @param type The command type
	 * @param view The view of the game
	 * @param model The model of the game
	 */
	public CommandListener(View.Command type, View view, Game model) {
		this.type = type;
		this.view = view;
		this.model = model;
	}
	/**
	 * The action being performed
	 */
	public void actionPerformed(ActionEvent e) {
		view.setSelectedCommand(type);
		view.updatePlantButtonStatus();
		switch(type) {
		case END:
			model.endPhase();
			break;
		case UNDO:
			model.undo();
			break;
		case REDO:
			model.redo();
			break;
		default:
			break;
		}
	}
}
