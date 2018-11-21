package game;

import java.util.EventObject;

/**
 * Event  for modeling the sunpoints in the game 
 * @author Christopher Wang, Christophe Tran, Rahul Anilkumar, Thomas Leung
 * @version 1.0
 *
 */
public class PointEvent extends EventObject{
	private int sunPoints;
	private int turn;
	/**
	 * Constructor for the class
	 * @param state the game state that this is an event for
	 */
	public PointEvent(GameState state) {
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
	 * Set a new value to sunPoints
	 * @param sunPoints the value to be set
	 */
	public void setSunPoints(int sunPoints) {
		this.sunPoints = sunPoints;
	}
	
	/**
	 * the current Turn of the game
	 * @return the current Turn
	 */
	public int getTurn() {
		return turn;
	}
	/**
	 * set the turn to a specified value
	 * @param turn the turn to be set to
	 */
	public void setTurn(int turn) {
		this.turn = turn;
	}
}