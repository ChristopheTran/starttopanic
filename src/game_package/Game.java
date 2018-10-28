package game_package;
import java.util.*;
import java.util.stream.Collectors;

import level_package.*;
import entity_package.*;
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
 * 
 */
public class Game {
	private Scanner scanner;
	private GameState gameState;
	
	/**
	 * Constructor for Game class.
	 */
	public Game() {
		scanner = new Scanner(System.in);
	}

	/**
	 * Gets input from the player through the console and validates the position based of the boards dimensions.
	 * @return the position that is defined by the user input
	 */
	public Position getPosition() {
		System.out.println("Please enter a position.");
		System.out.println("x-Coordinate:");
		int x = scanner.nextInt();
		System.out.println("y-Coordinate:");
		int y = scanner.nextInt();
		try {
			Position position = new Position(x, y);
			return position;
		}catch(IndexOutOfBoundsException e) {
			System.out.println("Warning those are not valid coordinates!");
			return getPosition();
		}
	}
	
	/**
	 * Gets the name of the plant that the player would like to plant.
	 * @return A string containing the name of the plant
	 */
	public String getPlantName() {
		System.out.println("Name of Plant: ");
		String plantName = scanner.next();
		plantName = plantName.toLowerCase();
		return plantName;
	}

	/**
	 * takes the name and position of the plant that is being planted and sets that plant at the indicated position on the board
	 * @param name The name of the plant being planted
	 * @param pos The position that the plant will be planted
	 */
	public void potPlant(String name, Position pos) {
		if(gameState.checkCollision(pos).isEmpty()) {
			if(name.equals("sunflower") && gameState.getSunPoints()>=50) {
				Sunflower sun = new Sunflower(55,0,"sun", pos,50,1,1);
				gameState.addEntity(sun);	
				int newSunPoints = gameState.getSunPoints()-sun.getCost();
				gameState.setSunPoints(newSunPoints);
			}
			else if(name.equals("peashooter") && gameState.getSunPoints()>=100) {
				Peashooter pea = new Peashooter(25,25,"shooter", pos,100,2,3);
				gameState.addEntity(pea);
				int newSunPoints = gameState.getSunPoints()-pea.getCost();
				gameState.setSunPoints(newSunPoints);
			}
			else {
				System.out.println("That is not a valid plant or you dont have enough credit");
			}
		}
		else {
			System.out.println("That spot is already occupied.");
		}
	}
	
	/**
	 * The position of the plant being removed
	 * @param pos the position of the plant
	 * @return the entity at that location specified by the passed position
	 */
	public Entity getPlantToRemove(Position pos) {
		List<Entity> plant = gameState.getEntities();
		for(Entity ent : plant) {
			if(ent.getPosition().equals(pos)) {
				return ent;
			}
		}
		return null;
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
		int valToSet = 0;
		if(numSunSpawn !=0) {
			int addToTotal = 25*numSunSpawn; 
			System.out.println(addToTotal + " sunpoints was formed this turn");
			 valToSet += gameState.getSunPoints()+addToTotal;
		}
		int sunshine=getSunshine();
		System.out.println(sunshine + " sunpoints was collected from sunflowers");
		valToSet+= sunshine;
		gameState.setSunPoints(valToSet);
	}
	
	/**
	 * The phase of the game where the player inputs his moves to plant and remove plants
	 */
	public void userPhase() {
		Boolean endOfPhase = false;
		System.out.println(gameState.toString());
		System.out.println("Make your move! \n");
		//Loop until player decides to finish his turn, allowing them to plant and remove plants as needed
		while(endOfPhase==false) {
			System.out.println("What would you like to do?\nThe available commands are: Plant | Remove | End | Help");
			String r = scanner.next();
			r = r.toLowerCase();
			if(r.equals("plant")) {
				System.out.println("sunflower: 50 points | peashooter: 100 points ");
				String name = getPlantName();
				Position pos = getPosition();
				potPlant(name, pos);
				System.out.println(gameState.toString());
			}
			else if(r.equals("remove")) {
				Position pos = getPosition();
				Entity entToRemove = getPlantToRemove(pos);
				gameState.removeEntity(entToRemove);
				System.out.println(gameState.toString());
			}
			else if(r.equals("end")) {
				endOfPhase = true;
			}
			else if(r.equals("help")) {
				System.out.println("\nPlant - pot a plant on the board based of the position\n");
				System.out.println("Remove - remove a plant on the board based of the position that has already been planted\n");
				System.out.println("End- End your turn\n");
			}
		}
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
					// get all zombies on same lane as peashooter, on same tile or to the right of the peashooter
					.filter(z -> p.sameLane(z) && z.getX() >= p.getX()) 
					.min(Comparator.comparing(Zombie::getX)) 	// get the closest zombie to the peashooter
					.orElse(null);							// if no zombies on a lane of a peashooter, return null
			if(zombieToBeAttacked != null) {
				zombieToBeAttacked.setHealth(zombieToBeAttacked.getHealth() - p.getAttack());
				if(zombieToBeAttacked.getHealth() <= 0) {
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
			z.setX(z.getX() - move);
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
	public boolean endPhase() {
		//Determine if zombies have won
		for(Zombie z: gameState.getZombies()) {
			if(z.getPosition().getX() < 0) {
				printGameOver();
				return false;
			}
		}
		//Determine if the user has won
		if(gameState.getTurn() >= gameState.getLevel().getWaves() && gameState.getZombies().isEmpty()) {
			printWinGame();
			return false;
		}
		//The game continues, spawn zombies and decrement waves
		gameState.incrementTurn();
		if(gameState.getTurn() <= gameState.getLevel().getWaves()) {
			spawnWave();
			System.out.println("Zombies have spawned!");
		}
		return true;
	}

	/**
	 * Randomly spawn 0 to 2 zombies randomly across the map.
	 */
	public void spawnWave() {
		Random rand = new Random();	
		for(int i=0; i<(rand.nextInt(2)+1); i++) {
			Zombie z = new Zombie(100, 16, "Bob", new Position(8, rand.nextInt(5)),1);
			gameState.addEntity(z);
		}
	}
	
	/**
	 * This is flavor text to provide context on the game and its setting
	 */
	public void printOpening() {
		System.out.println("****************************************************");
		System.out.println("*                   START TO PANIC                 *");
		System.out.println("****************************************************");
		System.out.println("* The world has been taken over by Zombies!        *");
		System.out.println("* Their only weekness are mutant plants!           *");
		System.out.println("* Pot your plants to keep your house safe.         *");
		System.out.println("* You have sunflowers and peashooters to fight the *");
		System.out.println("* invasion! Use Sunpoints to pot plants.           *");
		System.out.println("* Kill all the zombies to win!  				   *");
		System.out.println("****************************************************\n");
	}
	
	/**
	 * This is flavor text to output game over
	 */
	public void printGameOver() {
		System.out.println("**********************************************");
		System.out.println("*                 You Lose                   *");
		System.out.println("**********************************************");
	}
	
	/**
	 * This is flavor text to output when the user wins the game.
	 */
	public void printWinGame() {
		System.out.println("**********************************************");
		System.out.println("*                   YOU WIN!                 *");
		System.out.println("**********************************************");
	}
	
	public static void main(String[] args) {
		Level one = new Level(10, new ArrayList<Zombie>());
		GameState state = new GameState(one);
		Game game = new Game();
		game.printOpening();
		game.gameState=state;
		do {
			game.sunshinePhase();
			game.userPhase();
			game.movePhase();
			game.attackPhase();
		} while(game.endPhase());		
	}
}
