package game;

import java.util.EventObject;
import java.util.List;

import entity.Entity;

public class GameStateEvent extends EventObject{
	private int sunPoints;
	private int turn;
	private List<Entity> entities;
	public GameStateEvent(GameState state) {
		super(state);
		sunPoints = state.getSunPoints();
		turn = state.getTurn();
		entities = state.getEntities();
	}
	public int getSunPoints() {
		return sunPoints;
	}
	public void setSunPoints(int sunPoints) {
		this.sunPoints = sunPoints;
	}
	public int getTurn() {
		return turn;
	}
	public void setTurn(int turn) {
		this.turn = turn;
	}
	public List<Entity> getEntities() {
		return entities;
	}
	public void setEntities(List<Entity> entities) {
		this.entities = entities;
	}
}