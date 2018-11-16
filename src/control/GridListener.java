package control;

import java.awt.event.*;

import entity.*;
import game.EntityEvent;
import game.Game;
import view.View;

public class GridListener implements ActionListener{
	private Position position;
	private View view;
	private Game model;
	public GridListener(Position position, View view, Game model) {
		this.position = position;
		this.view = view;
		this.model = model;
	}
	public void actionPerformed(ActionEvent e) {
		switch(view.getSelectedCommand()) {
		case POT:
			model.potPlant(view.getSelectedEntity(), position);
			break;
		case REMOVE:
			model.removePlant(position);
			break;
//		case END:
//			System.out.println("END");
//			model.endPhase();
//			break;
		default:
			break;
		}
		
	}
}

