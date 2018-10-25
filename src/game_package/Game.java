package game_package;
import java.util.*;
import level_package.*;
import entity_package.*;

public class Game {
	
	private Level level;
	private int sunPoints;
	private int numSunSpawn;
	private int gamePhase;
	
	/**
	 * Constructor
	 * @param level the level being played
	 */
	public Game(Level level) {
		this.level = level;
		sunPoints = 100;
	}
	
	/**
	 * getter for the sunPoints
	 * @return int with sunpoints
	 */
	public int getSunPoints() {
		return sunPoints;
	}
	
	/**
	 * Sets the amount of sunpoints available
	 * @param val the sun points to be set to the total
	 */
	public void setSunPoints(int val) {
		sunPoints = val;
	}
	
	/**
	 * The level that is being played
	 * @return the current level
	 */
	public Level getLevel() {
		return level;
	}
	
	/**
	 * Gathers sunshine from all sunflowers and the randomly spawned sunshine to the total count
	 */
	public void sunshinePhase() {
		numSunSpawn = (int)(Math.random() * 5 + 1);
		System.out.println("Wow! " + numSunSpawn + " Sunshine has formed this turn." );
		System.out.println("Gathering Sunshine!"); 
		
		if(numSunSpawn !=0) {
			int addToTotal = 25*numSunSpawn; 
			sunPoints += addToTotal;
		}
		sunPoints+= getLevel().getSunshine();
		
		System.out.println("You have:" + sunPoints + " sun points!\n");
	}
	
	
	/**
	 * Makes the move for the specific plant to be planted and the position for it to be placed
	 * @param scanner the scanner to get input for the plant name and position
	 */
	public void makeMove(Scanner scanner) {
		System.out.println("POT a plant or REMOVE a plant?");
		String choice = scanner.next();
		choice = choice.toLowerCase();
		if(choice.equals("pot")) {
			System.out.println("Enter the name of the plant you want to pot and the xy-coordinate that you would like to place it at.\nPlant Name: ");
			String plantName = scanner.next();
			System.out.println("x-Coordinate:");
			int x = scanner.nextInt();
			System.out.println("y-Coordinate:");
			int y = scanner.nextInt();
			if((x>=0 && x<9) && (y>=0 && y<5)) {
				sunPoints= getLevel().addEntity(plantName,x,y,sunPoints);
			}
			else {
				System.out.println("!!!!Warning!!!! \nThose are not valid coordinates!");
			}
			System.out.println(getLevel().toString());
			System.out.println("You have " + sunPoints + "sunpoints leftover");
		}
		else if(choice.equals("remove")) {
			System.out.println("Enter the x and y coordinate of the plant");
			System.out.println("x-Coordinate:");
			int x = scanner.nextInt();
			System.out.println("y-Coordinate:");
			int y = scanner.nextInt();
			getLevel().removeFromBoard(getLevel().removeEntity(x, y));
			System.out.println(getLevel().toString());
			
		}
	}
	
	
	/**
	 * phase for the user to make the moves for the turn.
	 */
	public void userPhase() {
		Boolean endPhase = false;
		System.out.println(level.toString());
		System.out.println("Make your move! \n");
		Scanner scanner = new Scanner(System.in);
		makeMove(scanner);
		while(endPhase==false) {
			
			System.out.println("Would you like to end your turn? Input End to end the turn or Continue to make another Move \nResponse:");
			String r = scanner.next();
			r = r.toLowerCase();
			if(r.equals("end")) {
				System.out.println(r);
				endPhase=true;
			}
			else if(r.equals("continue")) {
				System.out.println("Make your next Move!");
				makeMove(scanner);
			}
		}
		scanner.close();
		System.out.println("Changing Phases...");
	}
	
	private void zombieAttack() {
		List<Zombie> zombies = zombieCollision();
	}
	
	public void movePhase() {
		List<Zombie> zombies = level.getZombies();
		zombies.removeAll(zombieCollision());
		for(Zombie zombie: zombies) {
			zombie.getPosition().setX(zombie.getPosition().getX() - 1);
		}
	}
	
	private List<Zombie> zombieCollision(){
		List<Zombie> zombies = new ArrayList<Zombie>();
		for(Zombie zombie:  level.getZombies()) {
			for(Plant plant: level.getPlants()) {
				if(zombie.getPosition().equals(plant.getPosition())){
					zombies.add(zombie);
				}
			}
		}
		return zombies;
	}
	
	public void endPhase() {
		

	}
	
	public static void main(String[] args) {
		Level one = new Level();
		Game game = new Game(one);
		game.sunshinePhase();
		game.userPhase();
	}
	
}

