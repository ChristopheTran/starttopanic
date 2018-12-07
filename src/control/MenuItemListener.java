package control;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.JMenuItem;

import game.Game;
import level.LevelBuilder;
import view.LevelBuilderView;
import view.View;

public class MenuItemListener implements ActionListener{

	private View view;
	private Game model;
	
	/**
	 * constructor for the listener
	 * @param view the view that is being used by the game
	 * @param model is the model that is being used by the game
	 */
	public MenuItemListener(View view, Game model) {
		this.view = view;
		this.model = model;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (view.getRestartMenu() == (JMenuItem) object) {
			model.resetGame(view, model);
		} else if (view.getCheatMenu() == (JMenuItem) object) {
			String cheatCode = "";
			cheatCode = view.getCheatCode();
			model.addCheat(cheatCode);
		} else if(view.getSaveItem() == (JMenuItem) object) {
			boolean success = model.saveGame("StartToPanicSav.ser");
			view.getLoadItem().setEnabled(true);
			if(success) {
				view.notifyFileStatus("Game was saved");
			}else {
				view.notifyFileStatus("save was not saved sucessfully");
			}
		} else if(view.getLoadItem()== (JMenuItem) object) {
			boolean success = model.loadGame("StartToPanicSav.ser");
			if(success) {
				view.notifyFileStatus("loaded successfully");
			}else {
				view.notifyFileStatus("Game was not loaded sucessfully. Please check that a valid save was loaded.");
			}
		} else if(view.getBuildMenu() == (JMenuItem) object) {
			LevelBuilderView builderView = new LevelBuilderView();
			LevelBuilder builderModel = new LevelBuilder();
			builderView.addBuilderListener(new BuilderListener(builderModel,builderView));
		} else if(view.getLoadMenu() == (JMenuItem) object) {
			view.disablePlantsButtonStatus();
			boolean success = model.loadLevel(view.getFile());;
			if(success) {
				view.notifyFileStatus("Level was loaded successfully");
			}else {
				view.notifyFileStatus("Level was not loaded sucessfully. Ihe entered level is invalid!");
			}
		} else if(view.getRealTimeMenu() == (JMenuItem) object) {
			model.realTimeEnable();
		}	

		
	}

}
