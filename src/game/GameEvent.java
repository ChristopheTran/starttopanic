package game;

import java.util.EventObject;

import entity.Entity;

public class GameEvent extends EventObject{
	private boolean outcome;
	public GameEvent(Game game, boolean outcome) {
		super(game);
		this.outcome = outcome;
	}
}
