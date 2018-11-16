package game;

import java.util.EventObject;

import entity.Entity;

public class GameEvent extends EventObject{
	private boolean outcome;
	public GameEvent(GameState state, boolean outcome) {
		super(state);
		this.outcome = outcome;
	}
	public boolean getOutcome() {
		return outcome;
	}
	public void setOutcome(boolean outcome) {
		this.outcome = outcome;
	}
}
