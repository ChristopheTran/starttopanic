package control;
import java.awt.event.*;

import game.Game;
import view.View;
public class CommandListener implements ActionListener{
	private View.Command type;
	private View view;
	private Game model;
	public CommandListener(View.Command type, View view, Game model) {
		this.type = type;
		this.view = view;
		this.model = model;
	}
	public void actionPerformed(ActionEvent e) {
		view.setSelectedCommand(type);
		switch(type) {
		case END:
			model.endPhase();
			break;
		default:
			break;
		}
	}
}
