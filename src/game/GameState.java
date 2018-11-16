package game;
import java.util.*; 
import java.util.stream.Collectors;

import entity.*;
import level.*;
/**
 * This class implements a game state in the Plant vs Zombie games. Each game state has 
 * certain unifying characteristics. Every game state consists of a level, a number of
 * sun points, a list of entities that exist at that particular turn. Each turn can thus be
 * assigned a number corresponding to a specific game state.
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.0
 */
public class GameState {
	private Level level; // Current level in the game
	private int sunPoints; // Player's current total sun points
	private int turn; // The current turn in the game
	private List<Entity> entities; //All the entities currently on the board
	private List<GameStateListener> listeners; //All the listeners for the GameState
	
	/**
	 * Constructor for GameState.
	 * @param level The level of the current game
	 */
	public GameState(Level level) {
		this.level = level;
		sunPoints = 200;
		entities = new ArrayList<Entity>();
		listeners = new ArrayList<GameStateListener>();

	}
	
	
	/**
	 * Get the total sun points the player current has.
	 * @return Total sun points
	 */
	public int getSunPoints() {
		return sunPoints;
	}
	
	/**
	 * Decrement the players total sun points to a new value
	 * @param sunPoints The points to remove from the total
	 */
	public void decrementSunPoints(int sunPoints) {
		this.sunPoints -= sunPoints;
		for(GameStateListener listener: listeners) {
			listener.updateSunshine(new PointEvent(this));
		}
	}
	
	/**
	 * Increment the players total sun points to a new value
	 * @param sunPoints The points to add to the total
	 */
	public void incrementSunPoints(int sunPoints) {
		this.sunPoints += sunPoints;
		for(GameStateListener listener: listeners) {
			listener.updateSunshine(new PointEvent(this));
		}
	}
	
	/**
	 * Set the player's total sun points to a new value.
	 * @param sunPoints The sun points obtained from a turn.
	 */
	public void setSunPoints(int sunPoints) {
		this.sunPoints =  sunPoints;
		for(GameStateListener listener: listeners) {
			listener.updateSunshine(new PointEvent(this));
		}
	}
	
	/**
	 * Get all the current entities on the board.
	 * @return List of all entities on the board
	 */
	public List<Entity> getEntities(){
		return entities;
	}
	

	public void updateEntities(List<Entity> removableEntities){
		entities.removeAll(removableEntities);
	}
	
	/**
	 * Get the current level of the game.
	 * @return Level of the game
	 */
	public Level getLevel() {
		return level;
	}
	
	/**
	 * Get the current turn in the game.
	 * @return Current turn in the game
	 */
	public int getTurn() {
		return turn;
	}
	
	/**
	 * Increment turn by 1.
	 */
	public void incrementTurn() {
		turn++;
		for(GameStateListener listener: listeners) {
			listener.updateTurn(new PointEvent(this));
		}
	}
	
	/**
	 * Add an entity to the game.
	 * @param ent The entity to be added
	 */
	public void addEntity(Entity ent) {
		entities.add(ent);
		for(GameStateListener listener: listeners) {
			listener.drawEntity(new EntityEvent(this, ent));
		}
	}
	
	/**
	 * Remove an entity from the game. (GUI only)
	 * @param ent The entity to be removed
	 */
	public void removeEntity(Entity ent) {
		entities.remove(ent); 
		for(GameStateListener listener: listeners) {
			listener.eraseEntity(new EntityEvent(this, ent));
		}
	}
	
	/**
	 * Moves a zombie on the board
	 * @param ent The entity to be moved on the board
	 */
	public void moveZombie(Entity ent) {
		for(GameStateListener listener: listeners) {
			listener.drawEntity(new EntityEvent(this, ent));
		}
	}
	
	/**
	 * Check if any entities have collided with given position.
	 * @param p The position to check for collision
	 * @return The list of entities that collided with position given
	 */
	public List<Entity> checkCollision(Position p){
		List<Entity> entities = this.entities.stream()
				.filter(entity -> entity.getPosition().equals(p))
				.collect(Collectors.toList());
		return entities;
	}
	
	/**
	 * Get all the zombies in the current game.
	 * @return The list of zombies in current game
	 */
	public List<Zombie> getZombies(){
		List<Zombie> zombies = entities.stream()
				.filter(entity-> entity instanceof Zombie)
				.map(zombie -> (Zombie) zombie)
				.collect(Collectors.toList());
		return zombies;
	}
	
	/**
	 * Get all the plants in the current game.
	 * @return The list of plants in current game
	 */
	public List<Plant> getPlants(){
		List<Plant> plants = entities.stream()
				.filter(entity-> entity instanceof Plant)
				.map(plant -> (Plant) plant)
				.collect(Collectors.toList());
		return plants;
	}
	
	/**
	 * Console output of the current state of the board (game).
	 * @return A string that contains the board of representing the games current state
	 */
	public String toString() {
		//Create a 2D board encoded by Char and populate it with empty cells
		char[][] board = new char[Level.Y_BOUNDARY * 4][Level.X_BOUNDARY * 5];
		for(int y = 0; y < Level.Y_BOUNDARY * 4; y++) {
			for(int x = 0; x < Level.X_BOUNDARY * 5; x++) {
				if(y % 4 == 0 || y % (3 + (y/4) * 4) == 0) {
					board[y][x] = '-';
				}
				else if(x % 5 == 0 || x % (4 + (x/5) * 5) == 0){
					board[y][x] = '|';
				}
				else{
					board[y][x] = ' ';
				}
			}
		}
		
		//Populate the board with Zombies
		for(Zombie zombie: getZombies()) {
			Position p = zombie.getPosition();
			int x = p.getX(), y = p.getY();
			if(board[y * 4 + 2][x * 5 + 1] == 'Z') {
				board[y * 4 + 2][x * 5 + 3] += 1;
			} else {
				board[y * 4 + 2][x * 5 + 1] = 'Z';
				board[y * 4 + 2][x * 5 + 3] = '1';	
			}
		}
		
		//Populate the board with Plants
		for(Plant plant: getPlants()) {
			Position p = plant.getPosition();
			int x = p.getX(), y = p.getY();
			if (plant instanceof Peashooter) {
				board[y * 4 + 1][x * 5 + 1] = 'P';
			}
			else if (plant instanceof Sunflower) {
				board[y * 4 + 1][x * 5 + 1] = 'S';
			}
		}
		
		//Encode the game board as a string
		String s = "Total Sunpoints: " + sunPoints + "\n";
		for(int y = 0; y < Level.Y_BOUNDARY * 4; y++) {
			for(int x = 0; x < Level.X_BOUNDARY * 5; x++) {
				s += board[y][x];
			}
			s+= "\n";
		}
		s+= "Turn: " + this.turn;
		return s;
	}
	
	public void addListener(GameStateListener listener) {
		listeners.add(listener);
	}
}
