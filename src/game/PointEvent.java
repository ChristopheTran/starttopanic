package game;

import java.util.EventObject;
import java.util.List;

import entity.Entity;

public class PointEvent extends EventObject{
	private int sunPoints;
	private int turn;
	public PointEvent(GameState state) {
		super(state);
		sunPoints = state.getSunPoints();
		turn = state.getTurn();
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
}