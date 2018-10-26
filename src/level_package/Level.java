package level_package;
import java.util.*; 
import java.util.stream.Collectors;
import entity_package.*;
/**
 * This class implements levels in the Plant vs Zombie games. Each level has 
 * certain unifying characteristics. Every level is played on a fixed 9x5 board.
 * The board consists of a tile that possesses certain terrain hazards (net yet implemented)
 * Every level has a list of different Zombie types that are spawned every turn in waves.
 * A player's goal is to survive every wave. A level determines how many waves a player needs
 * to survive to win.
 */
public class Level {
	public static final int X_BOUNDARY = 9;
	public static final int Y_BOUNDARY = 5;
	public Tile[][] board;
	public List<Zombie> zombieList;
	private int waves;
	
	/**
	 * Constructor for the Level class.
	 * @param waves The number of waves that a player must endure for a Level
	 * @param zombieList The list of Zombies types that can be spawned each turn for a Level
	 */
	public Level(int waves, List<Zombie> zombieList) {
		this.board = new Tile[Y_BOUNDARY][X_BOUNDARY];
		//initialize all the tiles on the board
		for (int row=0; row < X_BOUNDARY; row++) {
			for(int col=0; col < Y_BOUNDARY; col++) {
				board[col][row] = new Tile("grass", new Position(row,col));
			}
		}
		this.waves = waves;
		this.zombieList = zombieList;
	}
	
	/**
	 * Retrieves the list of Zombies that can be spawned this Level
	 * @return The  list of Zombies that can be spawned this Level
	 */
	public List<Zombie> getZombieList(){
		return zombieList;
	}
	
	/**
	 * Retrieves the Tile at a specified position of this Level
	 * @param position The Position of the tile to be retrieved
	 * @return The tile at a specified position of this Level
	 * @throws IndexOutOfBoundsException if the position is outside the board
	 */
	public Tile getTile(Position p) throws IndexOutOfBoundsException {
		return board[p.getY()][p.getX()];
	}

	/**
	 * Retrieves the number of waves that a player must endure for a Level
	 * @return The number of waves that a player must endure for a Level
	 */
	public int getWaves() {
		return waves;
	}
}

