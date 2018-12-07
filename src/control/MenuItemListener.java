package control;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
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
			model.saveGame();
			view.getLoadItem().setEnabled(true);
			view.notifyFileSaved();
		} else if(view.getLoadItem()== (JMenuItem) object) {
			model.loadGame();
		} else if(view.getBuildMenu() == (JMenuItem) object) {
			LevelBuilderView builderView = new LevelBuilderView();
			LevelBuilder builderModel = new LevelBuilder();
			builderView.addBuilderListener(new BuilderListener(builderModel,builderView));
		} else if(view.getLoadMenu() == (JMenuItem) object) {
			view.disablePlantsButtonStatus();
			model.loadLevel(view.getFile());
		} 
		
		
	}

}
