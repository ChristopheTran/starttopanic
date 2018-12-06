package control;
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import entity.EntityType;
import level.LevelBuilder;
import view.LevelBuilderView;

/**
 * Listener for the LevelBuilderView
 * @author Christophe Tran
 *
 */
public class BuilderListener implements ActionListener{
	private LevelBuilderView view;
	private LevelBuilder model;
	
	public BuilderListener(LevelBuilder m,LevelBuilderView v) {
		view = v;
		model = m;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o instanceof JButton) {
			if(o == view.getBuildLevel()){
				model.setInitialSunPoints((Integer)view.getSunpointsSpinner().getValue());
				
				if(view.getPeashooter().isSelected()) {
					model.addPlantType(EntityType.PEASHOOTER);
				}
				if(view.getSunflower().isSelected()) {
					model.addPlantType(EntityType.SUNFLOWER);
				}
				if(view.getFreezeshooter().isSelected()) {
					model.addPlantType(EntityType.FREEZESHOOTER);
				}
				if(view.getWalnut().isSelected()) {
					model.addPlantType(EntityType.WALNUT);
				}
				if((Integer) view.getZombieConeSpinner().getValue() > 0) {
					model.addZombieType(EntityType.ZOMBIE_CONE);
					//need to add number of zombies??
				}
				if((Integer) view.getZombieWalkerSpinner().getValue() > 0) {
					model.addZombieType(EntityType.ZOMBIE_WALKER);
					//need to add number of zombies??
				}
				if((Integer) view.getZombieRunnerSpinner().getValue() > 0) {
					model.addZombieType(EntityType.ZOMBIE_RUNNER);
					//need to add number of zombies??
				}
				//close level builder frame after button is clicked
				view.getFrame().dispose();;
			}
		}
				
	}

}
