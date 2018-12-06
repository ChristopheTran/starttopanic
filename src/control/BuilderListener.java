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
	private int plantTotal, zombieTotal;
	
	
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
				model.setWaves((Integer) view.getWaveSpinner().getValue());
				
				if(view.getPeashooter().isSelected()) {
					model.addPlantType(EntityType.PEASHOOTER);
					plantTotal++;
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
				if(! view.getZombieWalkerTotal().getText().equals("0%")){
					model.addZombieType(EntityType.ZOMBIE_WALKER);
				}
				if(! view.getZombieRunnerTotal().getText().equals("0%")){
					model.addZombieType(EntityType.ZOMBIE_RUNNER);
				}
				if(! view.getZombieConeTotal().getText().equals("0%")){
					model.addZombieType(EntityType.ZOMBIE_CONE);
				}

				//Save the level and close level builder frame after button is clicked				
				String file = view.getFile();
				model.saveLevel(file);
				view.getFrame().dispose();;
			}
			if(o == view.getZombieRunner()) {
				view.setZombieRunnerTotal("50%");
			}
			if(o == view.getZombieWalker()) {
				view.setZombieWalkerTotal("50%");
			}
			if(o == view.getZombieCone()) {
				view.setZombieConeTotal("50%");
			}
		}
		
				
	}

}
