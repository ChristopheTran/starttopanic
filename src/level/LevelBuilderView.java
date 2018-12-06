package level;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
public class LevelBuilderView {
	private JFrame frame;
	private JLabel sunLabel,zombieSpawnTitle, plantSelectionTitle, zombieWalkerLabel, zombieRunnerLabel, zombieConeLabel;
	private Container contentPane;
	private JPanel topPanel, zombiePanel, plantPanel;
	private JSpinner sunpointsSpinner, zombieWalkerSpinner, zombieRunnerSpinner, zombieConeSpinner;
	private JCheckBox peashooter,sunflower, walnut, freezeshooter;
	private JButton buildLevel;
	
	public LevelBuilderView() {
		frame = new JFrame("Level Builder");
		//create contentPane
		contentPane = frame.getContentPane(); // default layout is BorderLayout
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		topPanel = createTopPanel();
		zombiePanel = createZombiePanel();
		plantPanel = createPlantPanel();
		
		//create zombie spawn title
		zombieSpawnTitle = new JLabel("Zombie Selection: ");
		zombieSpawnTitle.setForeground(Color.RED);
		zombieSpawnTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		zombieSpawnTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		//create plant spawn title
		plantSelectionTitle = new JLabel("Plant Selection: ");
		plantSelectionTitle.setForeground(Color.GREEN);
		plantSelectionTitle.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
		plantSelectionTitle.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		
		//create build level
		buildLevel = new JButton("Build Level");
		buildLevel.setAlignmentX(JButton.CENTER_ALIGNMENT);
		// add all the panels to contentPane
		contentPane.add(topPanel);
		contentPane.add(zombieSpawnTitle);
		contentPane.add(zombiePanel);
		contentPane.add(plantSelectionTitle);
		contentPane.add(plantPanel);
		contentPane.add(buildLevel);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(1000, 700);
		frame.setVisible(true);
	}
	
	private JPanel createTopPanel() {
		JPanel topPanel = new JPanel();
		//create sunlabel and spinner
		sunLabel = new JLabel("Initial Sunpoints: ");
		sunpointsSpinner = new JSpinner(new SpinnerNumberModel(200,0,1000,50));

		topPanel.add(sunLabel);
		topPanel.add(sunpointsSpinner);
		//re-adjust size of Panel to avoid empty space on contentPane
		//topPanel.setMaximumSize(new Dimension(300,2000));
		return topPanel;
	}
	
	private JPanel createZombiePanel() {
		JPanel zombiePanel = new JPanel();
		//middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));

		zombiePanel.setLayout(new FlowLayout());

		//create all JLabel images and JSpinners
		zombieWalkerLabel = new JLabel(new ImageIcon("drawable/zombie_walker.png"));
		zombieWalkerSpinner = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
		zombieRunnerLabel = new JLabel(new ImageIcon("drawable/zombie_runner.png"));
		zombieRunnerSpinner = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
		
		zombieConeLabel = new JLabel(new ImageIcon("drawable/zombie_cone.png"));
		zombieConeSpinner = new JSpinner(new SpinnerNumberModel(0,0,1000,1));
		
		//add all components to panel
		zombiePanel.add(zombieWalkerLabel);
		zombiePanel.add(zombieWalkerSpinner);
		zombiePanel.add(zombieRunnerLabel);
		zombiePanel.add(zombieRunnerSpinner);
		zombiePanel.add(zombieConeLabel);
		zombiePanel.add(zombieConeSpinner);
		return zombiePanel;
		}
	
	private JPanel createPlantPanel() {
		JPanel plantPanel = new JPanel();
		plantPanel.setLayout(new FlowLayout());	
		peashooter = new JCheckBox((new ImageIcon("drawable/peashooter_grass.png")));
		peashooter.setSelectedIcon(new ImageIcon("drawable/peashooter_grass_selected.png"));
		
		sunflower = new JCheckBox((new ImageIcon("drawable/sunflower_grass.png")));
		sunflower.setSelectedIcon((new ImageIcon("drawable/sunflower_grass_selected.png")));
		
		walnut = new JCheckBox((new ImageIcon("drawable/walnut_grass.png")));
		walnut.setSelectedIcon((new ImageIcon("drawable/walnut_grass_selected.png")));
		
		freezeshooter = new JCheckBox((new ImageIcon("drawable/freezeshooter_grass.png")));
		freezeshooter.setSelectedIcon((new ImageIcon("drawable/freezeshooter_grass_selected.png")));

		plantPanel.add(peashooter);
		plantPanel.add(sunflower);
		plantPanel.add(walnut);
		plantPanel.add(freezeshooter);
		return plantPanel;
	}
	
	public void addBuilderListener(ActionListener listener) {
		buildLevel.addActionListener(listener);
	}
	
	public JCheckBox getPeashooter() {
		return peashooter;
	}

	public JCheckBox getSunflower() {
		return sunflower;
	}


	public JCheckBox getWalnut() {
		return walnut;
	}

	public JCheckBox getFreezeshooter() {
		return freezeshooter;
	}

	public JSpinner getSunpointsSpinner() {
		return sunpointsSpinner;
	}

	public JSpinner getZombieWalkerSpinner() {
		return zombieWalkerSpinner;
	}

	public JSpinner getZombieRunnerSpinner() {
		return zombieRunnerSpinner;
	}

	public JSpinner getZombieConeSpinner() {
		return zombieConeSpinner;
	}

	public JButton getBuildLevel() {
		return buildLevel;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public static void main(String[] args) {
		LevelBuilderView v = new LevelBuilderView();
		
	}

}
