package game;
import java.util.*;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import entity.*;
import level.*;
/**
 * This class contains the core logic of the game and instantiates the game. 
 * The class controls the user input, entity placements, wave spawns, and attack mechanics.
 * 
 * The game is in "puzzle" form, where each turn consists of a: 
 * Sunshine phase, User phase, Movement phase, Attack phase, and End phase
 * 
 * Each turn, zombies are spawned periodically.
 * The game is won if the player manages to survive 10 waves and kill all the zombies on the board.
 * The player loses if the zombies reach the end of the board.
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.0
 */
public class Game {
	private Scanner scanner;		// Scanner to get user input
	private GameState gameState;	// the state of the current game 
	
	public GameState getGameState() {
		return gameState;
	}


	/**
	 * Constructor for Game class. Initializes global variables
	 */
	public Game(GameState gameState) {
		scanner = new Scanner(System.in);
		this.gameState = gameState; 
	}


	/**
	 * takes the name and position of the plant that is being planted and sets that plant at the indicated position on the board
	 * @param name The name of the plant being planted
	 * @param pos The position that the plant will be planted
	 */
	public void potPlant(EntityType type, Position position) {
		if(gameState.checkCollision(position).isEmpty()) {
			Plant plant = (Plant) Entity.generateEntity(type, position);
			if(plant != null && plant.getCost() <= gameState.getSunPoints()) {
				gameState.addEntity(plant);
				gameState.decrementSunPoints(plant.getCost());
			}
		}
	}
	
	/**
	 * Remove a plant at a position (from the board and GUI)
	 * @param pos the position of the plant
	 */
	public void removePlant(Position pos) {
		List<Entity> plant = gameState.getEntities();
		List<Entity> plantsToBeRemoved = new ArrayList<Entity>();
		for(Entity ent : plant) {
			if(ent.getPosition().equals(pos)) {
				gameState.removeEntity(ent);
				plantsToBeRemoved.add(ent);
				gameState.incrementSunPoints(((Plant) ent).getCost());
			}
		}
		gameState.updateEntities(plantsToBeRemoved);
		

	}
	
	/**
	 * Retrieves the total amount of sunshine produced by sunflowers each turn 
	 * @return the total amount of sunshine produced
	 */
	public int getSunshine() {
		List <Plant> plants = gameState.getPlants();
		List<Sunflower> sunflowers = plants.stream()
			.filter(entity-> entity instanceof Sunflower)
			.map(entity -> (Sunflower) entity)
			.collect(Collectors.toList());
		return sunflowers.size() * Sunflower.SUNPOWER;
	}

	/**
	 * The phase of the game where the sunshine is gathered for the player
	 */
	public void sunshinePhase() {
		int numSunSpawn = (int)(Math.random() * 5 + 1);
		int generatedSunPoints = (numSunSpawn != 0) ? 25*numSunSpawn: 0;
		generatedSunPoints += getSunshine();
		gameState.incrementSunPoints(generatedSunPoints);
	}
	
	
	/**
	 * Makes all plants on the board attack the closest zombie if a zombie is present in that row.
	 */
	private void plantAttack() {
		List<Peashooter> peashooters = gameState.getPlants().stream()
				.filter(entity-> entity instanceof Peashooter)
				.map(p -> (Peashooter) p)
				.collect(Collectors.toList());
		for(Peashooter p : peashooters) {
			Zombie zombieToBeAttacked = gameState.getZombies().stream()
					.filter(z -> p.sameLane(z) && z.getX() >= p.getX()) 
					.min(Comparator.comparing(Zombie::getX)) 	// get the closest zombie to the peashooter
					.orElse(null);							// if no zombies on a lane of a peashooter, return null
			if(zombieToBeAttacked != null) {
				zombieToBeAttacked.setHealth(zombieToBeAttacked.getHealth() - p.getAttack());
				//System.out.println("pew pew pew");
				if(zombieToBeAttacked.getHealth() <= 0) {
					gameState.removeEntity(zombieToBeAttacked); // remove entity from GUI
					gameState.getEntities().remove(zombieToBeAttacked);
				}
			}
		}
	}
	
	/**
	 * Makes all the zombies on the board attack the closest plant if possible.
	 */
	private void zombieAttack() {
		for(Zombie z : zombieCollision()) {
			for(Plant p: gameState.getPlants()) {
				if(z.getPosition().equals(p.getPosition())) {
					p.setHealth(p.getHealth() - z.getAttack());
					if(p.getHealth() <= 0) {
						gameState.removeEntity(p); // remove entity from GUI
						gameState.getEntities().remove(p);
					}
				}
			}
		}
	}
	
	/**
	 * Makes all the entities on the board attack if possible.
	 */
	private void attackPhase() {
		zombieAttack();
		plantAttack();
	}
	
	/**
	 * Moves all the zombies on the board.
	 */
	public void movePhase() {
		for(Zombie z:  gameState.getZombies()) {
			//Get the rightmost plant to the left of the zombie
			Plant p = gameState.getPlants().stream()
					.filter(plant-> plant.sameLane(z) && plant.getX() <= z.getX())
					.max(Comparator.comparing(Plant::getX))
					.orElse(null);
			int move = z.getMoveSpeed();
			if(p != null) {
				move = (z.getX() - p.getX() < z.getMoveSpeed()) ? z.getX() - p.getX(): move;
			}
			gameState.removeEntity(z); // removing zombie from GUI 
			z.setX(z.getX() - move);
			gameState.moveZombie(z); // re-drawing zombie on GUI after movement
		}
	}
	
	/**
	 * Detects if any zombies on the board have collided with a plant.
	 * @return The list of zombies that have collided with plants on the board.
	 */
	private List<Zombie> zombieCollision(){
		List<Zombie> zombies = new ArrayList<Zombie>();
		for(Zombie z:  gameState.getZombies()) {
			for(Plant p: gameState.getPlants()) {
				if(z.collides(p)){
					zombies.add(z);
				}
			}
		}
		return zombies;
	}
	
	/**
	 * Determine if game should continue, and spawn new wave if it does.
	 * @return True if the game continues, false otherwise
	 */
	public void endPhase() {
		//Determine if zombies have won
		for(Zombie z: gameState.getZombies()) {
			if(z.getPosition().getX() < 0) {
				System.out.println("Game Over");
			}
		}
		//Determine if the user has won
		if(gameState.getTurn() >= gameState.getLevel().getWaves() && gameState.getZombies().isEmpty()) {
			System.out.println("You won");
		}
		//The game continues, spawn zombies and decrement waves
		gameState.incrementTurn();
		if(gameState.getTurn() <= gameState.getLevel().getWaves()) {
			sunshinePhase();
			spawnWave();
			attackPhase();
			movePhase();
		}
		else {
			sunshinePhase();
			attackPhase();
			movePhase();
		}
	}

	public void spawnWave() {
		Random rand = new Random();	
		for(int i=0; i<(rand.nextInt(2)+1); i++) {
			Zombie z = new Zombie(100, 16, "Bob", new Position(8, rand.nextInt(5)),1);
			gameState.addEntity(z);
		}
	}

	public static void main(String[] args) {
		Level one = new Level(10, new ArrayList<Zombie>());
		GameState state = new GameState(one);
		Game game = new Game(state);
	}
}
