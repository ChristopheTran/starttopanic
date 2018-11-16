package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import entity.EntityType;
import entity.Position;
import game.*;
/**
 * This class contains the GUI for the game. 
 * It displays the content of the game with a user interface to allow for point and click interaction
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.1
 */

public class View extends JFrame implements GameStateListener{
	public enum Command{POT, REMOVE, END, NONE}
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JButton[][] gridButton;
	private JButton[] commandButton, plantsButton;
	private int plantsClickable = 3; // right now 3 plants can be clicked
	
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem startItem, quitItem;

	private JLabel sunLabel;
	private JLabel turnsLabel;
	private Command selectedCommand;
	private EntityType selectedEntity;
	
	public Command getSelectedCommand() {
		return selectedCommand;
	}

	public void setSelectedCommand(Command selectedCommand) {
		this.selectedCommand = selectedCommand;
	}

	public EntityType getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(EntityType selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	/**
	 * Constructor
	 */
	public View() {
		gridButton = new JButton[5][9];
		commandButton = new JButton[5];
		plantsButton = new JButton[7];
		selectedCommand = Command.NONE;
		selectedEntity = EntityType.NONE;
		createGUI();
	}
	
	
	/**
	 * Add listener to a gridButton at row and column
	 * @param int row The row of the button
	 * @param int column the column of the button
	 * @param ActionListener listener The listener to be added
	 */
	public void addGridListener(int row, int column, ActionListener listener) {
		gridButton[row][column].addActionListener(listener);
	}
	/**
	 * Add listener to a commandButton at index
	 * @param int index the index to be added
	 * @param ActionListener listener The listener to be added
	 */
	public void addCommandListener(int index, ActionListener listener) {
		commandButton[index].addActionListener(listener);
	}
	/**
	 * Add listener to a plantsButton at index
	 * @param int index the index to be added
	 * @param ActionListener listener The listener to be added
	 */
	public void addPlantsListener(int index, ActionListener listener) {
		plantsButton[index].addActionListener(listener);
	}
	/**
	 * Create a Menu Bar for the game
	 * @return a ready to use jMenuBar
	 */
	public JMenuBar createMenuBar() {

		// create menu bar
		menuBar = new JMenuBar();

		// build menu
		menu = new JMenu("PANICCC");
		menuBar.add(menu);

		// menu Item
		startItem = new JMenuItem("Start Game");
		// Setting the accelerator:
		startItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		// startItem.addActionListener(null);
		menu.add(startItem);

		quitItem = new JMenuItem("Quit");
		quitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.META_MASK));
		menu.add(quitItem);
		quitItem.addActionListener(new ActionListener() // create an anonymous inner class
		{
			public void actionPerformed(ActionEvent event) {
				System.exit(0); // quit
			}
		});
		return menuBar;
	}

	/**
	 * Create a text area at the bottom of the game GUI
	 * @return a ready to use text area
	 */
	public JTextArea createBottomTextArea() {
		// Create a scrolled text area.
		JTextArea output = new JTextArea(4, 30);
		output.setText("Phase/ Extra text for user context.");
		output.setFont(new Font("Monaco", Font.PLAIN, 16));
		output.setBorder(new EmptyBorder(10, 10, 10, 10));
		output.setEditable(false);
		return output;
	}

	/**
	 * Create a SunPointsLabel section (located on the top first row)
	 * @return sunPointsLabel
	 */
	public JLabel createSunPointsLabel() {
		ImageIcon icon = new ImageIcon("drawable/sun.png");
		sunLabel = new JLabel("200" + " points   ", icon, JLabel.CENTER);
		sunLabel.setFont(new Font("hobo std", Font.PLAIN, 16));
		//Set the position of the text, relative to the icon:
		sunLabel.setHorizontalTextPosition(JLabel.RIGHT);
		// set and create a line border with the specified color and width
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Sun Points");
		titledBorder.setTitleFont(new Font("hobo std", Font.PLAIN, 16));
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		sunLabel.setBorder(new CompoundBorder(titledBorder, new EmptyBorder(25, 20, 20, 20)));
		return sunLabel;
	}
	
	/**
	 * Create a command instruction menu (located on the top first row)
	 * @return create command menu panel
	 */
	public JPanel createCommandMenu() {
		
		JPanel commandPane = new JPanel(new GridLayout(1, 5));
		String[] command = new String[] {"<-", "->", "Pot", "Remove", "End Turn"};
		for (int i = 0; i < command.length; i++) {
			commandButton[i] = new JButton(command[i]);
			commandButton[i].setFont(new Font("hobo std", Font.PLAIN, 16));
			commandPane.add(commandButton[i]);
		}
		Border blackline = BorderFactory.createLineBorder(Color.black, 2);
		TitledBorder border = BorderFactory.createTitledBorder(blackline, "Commands");
		border.setTitleFont(new Font("hobo std", Font.PLAIN, 16));
		commandPane.setBorder(border);
		return commandPane;
	}
	
	/**
	 * Create a plant selector (located on the top second row)
	 * @return a plant selector JPanel
	 */
	private JPanel createPlantSelector() {
		
		JPanel plantsPane = new JPanel(new GridLayout(1, 5));
		ImageIcon[] plantsIcon = new ImageIcon[] {new ImageIcon("drawable/peashooterProfile.png"), 
				new ImageIcon("drawable/sunflowerProfile.png"), new ImageIcon("drawable/sunflowerProfile2.png"), new ImageIcon("drawable/placeholder.png"), new ImageIcon("drawable/placeholder.png"), new ImageIcon("drawable/placeholder.png"), new ImageIcon("drawable/placeholder.png")};
		
		for (int i =0; i < plantsIcon.length; i++) {
			plantsButton[i] = new JButton(plantsIcon[i]);
			plantsPane.add(plantsButton[i]);
			//plantsButton[i].setBorder (null);
			// disable button if not available
			if (plantsClickable < i + 1) { // i + 1 as i is start from 0 but plantsClickable starts from 1
				//plantsButton[i].setEnabled(false);
				plantsButton[i].setEnabled(true);
			}
		}
		Border blackline = BorderFactory.createLineBorder(Color.black, 2);
		TitledBorder border = BorderFactory.createTitledBorder(blackline, "Plants");
		border.setTitleFont(new Font("hobo std", Font.PLAIN, 16));
		plantsPane.setBorder(border);
		return plantsPane;
	}
	
	/**
	 * Create a label to display the number of turns (located on the top second row)
	 * @return a create turns JLabel
	 */
	public JLabel createTurnsLabel() {
		turnsLabel = new JLabel("0", JLabel.CENTER);
		turnsLabel.setFont(new Font("hobo std", Font.PLAIN, 24));
		TitledBorder titledBorder = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black, 2), "Turn(s)");
		titledBorder.setTitleFont(new Font("hobo std", Font.PLAIN, 16));
		titledBorder.setTitleJustification(TitledBorder.CENTER);
		turnsLabel.setBorder(new CompoundBorder(titledBorder, new EmptyBorder(50, 50, 50, 50)));
		return turnsLabel;
	}
	
	/**
	 * Create the content pane for the GUI (including the game grid)
	 * @return a container contain all content of the pane
	 */
	public Container createContentPane() {

		// create contentPane
		JPanel contentPane = new JPanel(new BorderLayout());
		contentPane.setOpaque(true);

		// create JPane for the status and command at the top first row
		JPanel topPane = new JPanel(new GridLayout(2, 0));
		JPanel firstTopPane = new JPanel();
		firstTopPane.setLayout(new BoxLayout(firstTopPane, BoxLayout.X_AXIS));
		firstTopPane.setBorder(new EmptyBorder(10, 20, 10, 20));
		firstTopPane.add(createSunPointsLabel());
		firstTopPane.add(createCommandMenu());
		
		// create JPane for status and command at the top second row
		JPanel secondTopPane = new JPanel();
		secondTopPane.setLayout(new BoxLayout(secondTopPane, BoxLayout.X_AXIS));
		secondTopPane.setBorder(new EmptyBorder(10, 20, 10, 20));
		secondTopPane.add(createPlantSelector());
		secondTopPane.add(createTurnsLabel());
		
		topPane.add(firstTopPane);
		topPane.add(secondTopPane);
		
		// create game grid pane (the heart of the game)
		JPanel gamePane = new JPanel(new GridLayout(5, 9));
		// BorderFactory.createEmptyBorder(5, 5, 5, 5)
		gamePane.setBorder(new EmptyBorder(10, 10, 10, 10));
		// create all buttons 
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 9; j++) {
				ImageIcon icon = new ImageIcon("drawable/grass.png");
				this.gridButton[i][j] = new JButton(icon);
				//Set up components preferred size
		        this.gridButton[i][j].setOpaque(true);
		        // gridButton[i][j].setBorderPainted(false);
				gamePane.add(gridButton[i][j]);
			}
		}

		// Add all other panes to the content pane.
		contentPane.add(topPane, BorderLayout.PAGE_START);
		contentPane.add(gamePane, BorderLayout.CENTER);
		contentPane.add(createBottomTextArea(), BorderLayout.PAGE_END);
		return contentPane;
	}

	/**
	 * Create the GUI of the game including menu bar and adding all content together.
	 */
	public void createGUI() {
		// JFrame set up window
		frame = new JFrame("Start to PANICCC!!!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		//View view = new View();
		frame.setJMenuBar(createMenuBar());
		frame.setContentPane(createContentPane());

		// Display the window
		frame.setSize(1500, 900);
		frame.setVisible(true);
		frame.setResizable(true);
	}

	@Override
	public void updateSunshine(PointEvent e) {
		sunLabel.setText(Integer.toString(e.getSunPoints()));
		
	}
	@Override
	public void updateTurn(PointEvent e) {
		turnsLabel.setText(Integer.toString(e.getTurn()));
		
	}
	@Override
	public void drawEntity(EntityEvent e) {
		int row = e.getPosition().getY();
		int col = e.getPosition().getX();
		switch(e.getEntity()) {
		case SUNFLOWER:
			gridButton[row][col].setIcon(new ImageIcon("drawable/sunflowerProfile.png"));
			break;
		case PEASHOOTER:
			gridButton[row][col].setIcon(new ImageIcon("drawable/peashooterProfile.png"));
			break;
		case ZOMBIE:
			gridButton[row][col].setIcon(new ImageIcon("drawable/zombie.jpg"));
		default:
			break;
		}
	}
	
	@Override 
	public void eraseEntity(EntityEvent e) {
		int row = e.getPosition().getY();
		int col = e.getPosition().getX();
		if(e.getEntity() != EntityType.ZOMBIE) {
			gridButton[row][col].setIcon(new ImageIcon("drawable/grass.png"));
		}
	}
}
