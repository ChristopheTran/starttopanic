package game;

public interface GameStateListener {
	public void updateSunshine(GameStateEvent e);
	public void updateTurn(GameStateEvent e);
	public void redraw(GameStateEvent e);
}
