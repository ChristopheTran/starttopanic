package control;
import java.awt.event.*;

import view.View;
import entity.EntityType;
/**
 * Listener class for the plant entities
 * @author Christopher Wang, Christophe Tran, Rahul Anilkumar, Thomas Leung
 * @version 1.0
 */
public class PlantsListener implements ActionListener{
	private EntityType type;
	private View view;
	/**
	 * constructor for the listener
	 * @param type the type of entity being passed
	 * @param view the view that is being used by the game
	 */
	public PlantsListener(EntityType type, View view) {
		this.type = type;
		this.view = view;
	}
	
	/**
	 * the actions to be performed 
	 */
	public void actionPerformed(ActionEvent e) {
		view.setSelectedEntity(type);
	}
}
