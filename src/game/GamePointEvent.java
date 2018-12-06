package game;

import java.util.EventObject;

/**
 * Event  for modeling the sunpoints in the game 
 * @author Christopher Wang, Christophe Tran, Rahul Anilkumar, Thomas Leung
 * @version 1.0
 *
 */
public class GamePointEvent extends EventObject{
	private int sunPoints;
	private int turn;
	/**
	 * Constructor for the class
	 * @param state the game state that this is an event for
	 */
	public GamePointEvent(GameState state) {
		super(state);
		sunPoints = state.getSunPoints();
		turn = state.getTurn();
	}
	/**
	 * returns the current sunPoints
	 * @return the value of sunPoints
	 */
	public int getSunPoints() {
		return sunPoints;
	}
	
	/**
	 * the current Turn of the game
	 * @return the current Turn
	 */
	public int getTurn() {
		return turn;
	}
}