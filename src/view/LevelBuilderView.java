package view;
import java.awt.*;

/**
 * View for the Level Builder. 
 * Allows the player to select the type and  proportions of zombies to be spawned
 * 
 * @author Christophe Tran
 * @Version 2.0
 */
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
	
	/**
	 * Constructor for LevelBuilderView
	 */
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
	
	/**
	 * Create the panel for the sunpoints and waves
	 * @return JPanel 
	 */
	private JPanel createTopPanel() {
		JPanel topPanel = new JPanel();
		//create sunlabel and spinner
		sunLabel = new JLabel("Initial Sunpoints: ");
		sunpointsSpinner = new JSpinner(new SpinnerNumberModel(200,0,1000,50));
		
		waveLabel = new JLabel("Waves: ");
		waveSpinner =  new JSpinner(new SpinnerNumberModel(10,1,1000,1));
		
		topPanel.add(sunLabel);
		topPanel.add(sunpointsSpinner);
		topPanel.add(waveLabel);
		topPanel.add(waveSpinner);
		//re-adjust size of Panel to avoid empty space on contentPane
		//topPanel.setMaximumSize(new Dimension(300,2000));
		return topPanel;
	}
	
	/**
	 * Create a JPanel for the zombie selection
	 * @return JPanel
	 */
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
	
	/**
	 * Create a JPanel for the plant selection
	 * @return JPanel
	 */
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
	
	/**
	 * Get the file name for the level to be saved in
	 * @return The file name to be saved in
	 */
	public String getFile() {
		return JOptionPane.showInputDialog(frame, "File name to be saved:");
	}
	
	/**
	 * Notifies the player that they must select at least one zombie
	 */
	public void requireSelectZombie() {
		JOptionPane.showMessageDialog(null, "Please select at least one zombie type");
	}
	/**
	 * Add listeners to the zombie buttons
	 * @param listener
	 */
	public void addBuilderListener(ActionListener listener) {
		buildLevel.addActionListener(listener);
		zombieWalker.addActionListener(listener);
		zombieRunner.addActionListener(listener);
		zombieCone.addActionListener(listener);
	}
	
	/**
	 * Get the peashooter checkbox
	 * @return JCheckBox
	 */
	public JCheckBox getPeashooter() {
		return peashooter;
	}
	
	/**
	 * Get the sunflower checkbox
	 * @return JCheckBox
	 */
	public JCheckBox getSunflower() {
		return sunflower;
	}
	
	/**
	 * Get the walnut checkbox
	 * @return JCheckBox
	 */
	public JCheckBox getWalnut() {
		return walnut;
	}
	
	/**
	 * Get the freezeshooter checkbox
	 * @return JCheckBox
	 */
	public JCheckBox getFreezeshooter() {
		return freezeshooter;
	}

	/**
	 * Get the sunpoint JSpinner
	 * @return JSpinner
	 */
	public JSpinner getSunpointsSpinner() {
		return sunpointsSpinner;
	}

	/**
	 * Get the Build Level button
	 * @return JButton
	 */
	public JButton getBuildLevel() {
		return buildLevel;
	}
	
	/**
	 * Get the frame of the level builder view
	 * @return JFrame
	 */
	public JFrame getFrame() {
		return frame;
	}
	
	/**
	 * Get the zombieWalker proportion value
	 * @return JTextField
	 */
	public JTextField getZombieWalkerTotal() {
		return zombieWalkerTotal;
	}
	
	/**
	 * Set the zombie walker proportion value
	 * @param value The value to be set to
	 */
	public void setZombieWalkerTotal(String value) {
		this.zombieWalkerTotal.setText(value);;
	}
	
	/**
	 * Get the zombie runner proportion value
	 * @return JTextField
	 */
	public JTextField getZombieRunnerTotal() {
		return zombieRunnerTotal;
	}
	
	/**
	 * Set the zombie runner proportion value
	 * @param value Value to be set to
	 */
	public void setZombieRunnerTotal(String value) {
		this.zombieRunnerTotal.setText(value);;
	}
	
	/**
	 * Get the zombie cone proportion value
	 * @return JTextField
	 */
	public JTextField getZombieConeTotal() {
		return zombieConeTotal;
	}
	
	/**
	 * Set the zombie cone proportion value
	 * @param value Value to be set to
	 */
	public void setZombieConeTotal(String value) {
		this.zombieConeTotal.setText(value);;
	}
	
	/**
	 * Get the zombie walker button
	 * @return JButton
	 */
	public JButton getZombieWalker() {
		return zombieWalker;
	}
	
	/**
	 * Get the zombieRunner button
	 * @return JButton
	 */
	public JButton getZombieRunner() {
		return zombieRunner;
	}
	
	/**
	 * GEt the zombie cone button
	 * @return JButton
	 */
	public JButton getZombieCone() {
		return zombieCone;
	}
	
	/**
	 * Get the wave JSpinner
	 * @return JSpinner
	 */
	public JSpinner getWaveSpinner() {
		return waveSpinner;
	}
}
