package game;

public interface GameStateListener {
	public void updateSunshine(PointEvent e);
	public void updateTurn(PointEvent e);
	public void drawEntity(EntityEvent e);
	public void eraseEntity(EntityEvent e);
	public void gameOver(GameEvent e);
}
