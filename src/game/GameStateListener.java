package game;

/**
 * An Interface for the different gameStateListeners
 * @author Christopher Wang, Christophe Tran, Rahul Anilkumar
 * @version 1.0
 */
public interface GameStateListener {
	public void updateSunshine(PointEvent e);
	public void updateTurn(PointEvent e);
	public void drawEntity(EntityEvent e);
	public void eraseEntity(EntityEvent e);
	public void gameOver(GameEvent e);
}
