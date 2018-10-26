package game_package;
import java.util.*;
import java.util.stream.Collectors;

import level_package.*;
import entity_package.*;

public class Game {
	private int gamePhase;
	private Scanner scanner;
	private GameState gameState;
	
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
	
	public void potPlant(Scanner scanner) {
		System.out.println("Enter the name of the plant you want to pot.\nPlant Name: ");
		String plantName = scanner.next();
	}
	
	public void removePlant() {

	}

	public void makeMove(Scanner scanner) {
		System.out.println("POT a plant or REMOVE a plant?");
		String choice = scanner.next();
		choice = choice.toLowerCase();
		if(choice.equals("pot")) {

		}
		else if(choice.equals("remove")) {

			
		}
	}
	
	public int getSunshine() {
		List <Plant> plants = gameState.getPlants();
		List<Sunflower> sunflowers = plants.stream()
			.filter(entity-> entity instanceof Sunflower)
			.map(entity -> (Sunflower) entity)
			.collect(Collectors.toList());
		return sunflowers.size() * Sunflower.SUNPOWER;
	}

	public void sunshinePhase() {
		numSunSpawn = (int)(Math.random() * 5 + 1);
		System.out.println("Wow! " + numSunSpawn + " Sunshine has formed this turn." );
		System.out.println("Gathering Sunshine!"); 
		
		if(numSunSpawn !=0) {
			int addToTotal = 25*numSunSpawn; 
			sunPoints += addToTotal;
		}
		sunPoints+= getgameState().getSunshine();
		
		System.out.println("You have:" + sunPoints + " sun points!\n");
	}
	

	public void spawnWave() {
		Random rand = new Random();	
		for(int i=0; i<(rand.nextInt(2)+1); i++) {
			Zombie z = new Zombie(55, 66, "Bob", new Position(8, rand.nextInt(5)),5);
			gameState.addEntity(z);
		}
	}
	
	public void userPhase() {
		Boolean endPhase = false;
		System.out.println(gameState.toString());
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
	
	private void plantAttack() {
		List<Peashooter> peashooters = gameState.getPlants().stream()
				.filter(entity-> entity instanceof Peashooter)
				.map(p -> (Peashooter) p)
				.collect(Collectors.toList());
		for(Peashooter p : peashooters) {
			int lane = p.getPosition().getY();
			Zombie zombieToBeAttacked = gameState.getZombies().stream()
					// get all zombies on same lane as peashooter, on same tile or to the right of the peashooter
					.filter(entity -> entity.getPosition().getY()==lane && entity.getPosition().getX() >= p.getPosition().getX()) 
					.min(Comparator.comparing(Zombie::getX)) 	// get the closest zombie to the peashooter
					.orElse(null);							// if no zombies on a lane of a peashooter, return null
			if(zombieToBeAttacked != null) {
				zombieToBeAttacked.setHealth(zombieToBeAttacked.getHealth() - p.getAttack());
				if(zombieToBeAttacked.getHealth() <= 0) {
					gameState.getZombies().remove(zombieToBeAttacked);
				}
			}
		}
	}
	
	private void zombieAttack() {
		for(Zombie z : zombieCollision()) {
			for(Plant p: gameState.getPlants()) {
				if(z.getPosition().equals(p.getPosition())) {
					p.setHealth(p.getHealth() - z.getAttack());
					if(p.getHealth() < 0) {
						gameState.getPlants().remove(p);
					}
				}
			}
		}
	}
	
	public void movePhase() {
		List<Zombie> zombies = gameState.getZombies();
		zombies.removeAll(zombieCollision()); 
		for(Zombie z: zombies) {
			z.getPosition().setX(z.getPosition().getX() - z.getMoveSpeed());
		}
	}
	
	private List<Zombie> zombieCollision(){
		List<Zombie> zombies = new ArrayList<Zombie>();
		for(Zombie z:  gameState.getZombies()) {
			for(Plant p: gameState.getPlants()) {
				if(z.getPosition().equals(p.getPosition())){
					zombies.add(z);
				}
			}
		}
		return zombies;
	}
	
	/**
	 *
	 * @return True if the game continues, false otherwise
	 */
	public boolean endPhase() {
		//Determine if zombies have won
		for(Zombie z: gameState.getZombies()) {
			if(z.getPosition().getX() < 0) {
				return false;
			}
		}
		//Determine if the user has won
		if(gameState.getTurn() == gameState.getLevel().getWaves() && gameState.getZombies().isEmpty()) {
			return false;
		}
		//The game continues, spawn zombies and decrement waves
		gameState.incrementTurn();
		spawnWave();
		return true;
	}
}
