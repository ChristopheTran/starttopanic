package game;

/**
 * An Interface for the different gameStateListeners
 * @author Christopher Wang, Christophe Tran, Rahul Anilkumar
 * @version 1.0
 */
public interface GameStateListener {
	public void updateSunshine(GamePointEvent e);
	public void updateTurn(GamePointEvent e);
	public void drawEntity(GameEntityEvent e);
	public void eraseEntity(GameEntityEvent e);
	public void gameOver(GameEvent e);
}
