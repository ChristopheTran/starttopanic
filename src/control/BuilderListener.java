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
	private int zombieTotal;
	
	/**
	 * Constructor for BuilderListener
	 * @param m Model for the level builder view
	 * @param v View for the Level builder
	 */
	public BuilderListener(LevelBuilder m, LevelBuilderView v) {
		view = v;
		model = m;
		zombieTotal = 0;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o instanceof JButton) {
			if(o == view.getBuildLevel()){
				model.setInitialSunPoints((Integer)view.getSunpointsSpinner().getValue());
				model.setWaves((Integer) view.getWaveSpinner().getValue());
				
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
				//Save the level and close level builder frame after button is clicked if player chose atleast 1 zombie
				if(zombieTotal >0) {
					String file = view.getFile();
					model.saveLevel(file);
					view.getFrame().dispose();
				}
				else {
					view.requireSelectZombie();
				}
			}
			if(o == view.getZombieRunner()) {
				model.addZombieType(EntityType.ZOMBIE_RUNNER);
				view.setZombieRunnerTotal(model.getPercentage(EntityType.ZOMBIE_RUNNER));
				view.setZombieWalkerTotal(model.getPercentage(EntityType.ZOMBIE_WALKER));
				view.setZombieConeTotal(model.getPercentage(EntityType.ZOMBIE_CONE));
				zombieTotal++;
			}
			if(o == view.getZombieWalker()) {
				model.addZombieType(EntityType.ZOMBIE_WALKER);
				view.setZombieRunnerTotal(model.getPercentage(EntityType.ZOMBIE_RUNNER));
				view.setZombieWalkerTotal(model.getPercentage(EntityType.ZOMBIE_WALKER));
				view.setZombieConeTotal(model.getPercentage(EntityType.ZOMBIE_CONE));
				zombieTotal++;
			}
			if(o == view.getZombieCone()) {
				model.addZombieType(EntityType.ZOMBIE_CONE);
				view.setZombieRunnerTotal(model.getPercentage(EntityType.ZOMBIE_RUNNER));
				view.setZombieWalkerTotal(model.getPercentage(EntityType.ZOMBIE_WALKER));
				view.setZombieConeTotal(model.getPercentage(EntityType.ZOMBIE_CONE));
				zombieTotal++;
			}		
		}
	}
	
}

