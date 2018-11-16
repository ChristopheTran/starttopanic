package control;
import java.awt.event.*;

import view.View;
import entity.EntityType;
public class PlantsListener implements ActionListener{
	private EntityType type;
	private View view;
	public PlantsListener(EntityType type, View view) {
		this.type = type;
		this.view = view;
	}
	public void actionPerformed(ActionEvent e) {
		view.setSelectedEntity(type);
	}
}
