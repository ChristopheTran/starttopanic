package game;

import java.util.EventObject;
/**
 * The events for the game components.
 * @author Christopher Wang, Christophe Tran, Rahul Anilkumar, Thomas Leung
 * @version 1.0
 *
 */
public class GameEvent extends EventObject{
	private boolean outcome;
	/**
	 * Constructor
	 * @param state the current gamestate
	 * @param outcome the true/false value of the games outcome
	 */
	public GameEvent(GameState state, boolean outcome) {
		super(state);
		this.outcome = outcome;
	}
	
	/**
	 * get the outcome of the game
	 * @return the outcome  of the game
	 */
	public boolean getOutcome() {
		return outcome;
	}
	/**
	 * Set the outcome to a boolean
	 * @param outcome the true/false that the outcome will be set to
	 */
	public void setOutcome(boolean outcome) {
		this.outcome = outcome;
	}
}
