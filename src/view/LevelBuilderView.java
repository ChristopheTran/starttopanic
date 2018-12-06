package view;
import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
public class LevelBuilderView {
	private JFrame frame;
	private JLabel sunLabel,waveLabel,zombieSpawnTitle, plantSelectionTitle;
	private Container contentPane;
	private JButton zombieWalker, zombieRunner, zombieCone;
	private JPanel topPanel, zombiePanel, plantPanel;
	private JSpinner sunpointsSpinner,waveSpinner;
	private JTextField zombieWalkerTotal, zombieRunnerTotal, zombieConeTotal;
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
		
		waveLabel = new JLabel("Waves: ");
		waveSpinner =  new JSpinner(new SpinnerNumberModel(1,1,1000,1));
		
		topPanel.add(sunLabel);
		topPanel.add(sunpointsSpinner);
		topPanel.add(waveLabel);
		topPanel.add(waveSpinner);
		//re-adjust size of Panel to avoid empty space on contentPane
		//topPanel.setMaximumSize(new Dimension(300,2000));
		return topPanel;
	}
	
	private JPanel createZombiePanel() {
		JPanel zombiePanel = new JPanel();
		
		JPanel zombiePanel1 = new JPanel();
		zombiePanel1.setLayout(new BoxLayout(zombiePanel1, BoxLayout.Y_AXIS));
		
		JPanel zombiePanel2 = new JPanel();
		zombiePanel2.setLayout(new BoxLayout(zombiePanel2, BoxLayout.Y_AXIS));
		
		JPanel zombiePanel3 = new JPanel();
		zombiePanel3.setLayout(new BoxLayout(zombiePanel3, BoxLayout.Y_AXIS));

		zombieWalker = new JButton(new ImageIcon("drawable/zombie_walker.png"));
		zombieRunner = new JButton(new ImageIcon("drawable/zombie_runner.png"));
		zombieCone = new JButton(new ImageIcon("drawable/zombie_cone.png"));
		
		zombieWalkerTotal = new JTextField("0%");
		zombieWalkerTotal.setEditable(false);
		zombieRunnerTotal = new JTextField("0%");
		zombieRunnerTotal.setEditable(false);
		zombieConeTotal = new JTextField("0%");
		zombieConeTotal.setEditable(false);
		
		//add all components to panel
		zombiePanel1.add(zombieWalker);
		zombiePanel1.add(zombieWalkerTotal);
		
		zombiePanel2.add(zombieRunner);
		zombiePanel2.add(zombieRunnerTotal);
		
		zombiePanel3.add(zombieCone);
		zombiePanel3.add(zombieConeTotal);
		
		zombiePanel.add(zombiePanel1);
		zombiePanel.add(zombiePanel2);
		zombiePanel.add(zombiePanel3);
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
	
	public String getFile() {
		return JOptionPane.showInputDialog(frame, "File name to be saved:");
	}
	public void addBuilderListener(ActionListener listener) {
		buildLevel.addActionListener(listener);
		zombieWalker.addActionListener(listener);
		zombieRunner.addActionListener(listener);
		zombieCone.addActionListener(listener);
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


	public JButton getBuildLevel() {
		return buildLevel;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public static void main(String[] args) {
		LevelBuilderView v = new LevelBuilderView();	
	}
	
	public JTextField getZombieWalkerTotal() {
		return zombieWalkerTotal;
	}

	public void setZombieWalkerTotal(String value) {
		this.zombieWalkerTotal.setText(value);;
	}

	public JTextField getZombieRunnerTotal() {
		return zombieRunnerTotal;
	}

	public void setZombieRunnerTotal(String value) {
		this.zombieRunnerTotal.setText(value);;
	}

	public JTextField getZombieConeTotal() {
		return zombieConeTotal;
	}

	public void setZombieConeTotal(String value) {
		this.zombieConeTotal.setText(value);;
	}

	public JButton getZombieWalker() {
		return zombieWalker;
	}

	public JButton getZombieRunner() {
		return zombieRunner;
	}

	public JButton getZombieCone() {
		return zombieCone;
	}

	public JSpinner getWaveSpinner() {
		return waveSpinner;
	}
}
