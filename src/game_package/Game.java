package game_package;
import java.util.*;
import java.util.stream.Collectors;

import level_package.*;
import entity_package.*;

public class Game {
	private int gamePhase;
	private Scanner scanner;
	private GameState gameState;
	
	public Game() {
		scanner = new Scanner(System.in);
	}
	
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
	
	public String getPlantName() {
		System.out.println("Name of Plant: ");
		String plantName = scanner.next();
		plantName = plantName.toLowerCase();
		return plantName;
	}

	public void potPlant(String name, Position pos) {
		if(gameState.checkCollision(pos).isEmpty()) {
			if(name.equals("sunflower") && gameState.getSunPoints()>=50) {
				Sunflower sun = new Sunflower(55,0,"sun", pos,50,1,1);
				gameState.addEntity(sun);	
				int newSunPoints = gameState.getSunPoints()-sun.getCost();
				gameState.setSunPoints(newSunPoints);
			}
			else if(name.equals("peashooter") && gameState.getSunPoints()>=100) {
				Peashooter pea = new Peashooter(55,5,"shooter", pos,100,2,3);
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
	
	public Entity getPlantToRemove(Position pos) {
		List<Entity> plant = gameState.getEntities();
		for(Entity ent : plant) {
			if(ent.getPosition().equals(pos)) {
				return ent;
			}
		}
		return null;
	}
	
	
	public int getSunshine() {
		List <Plant> plants = gameState.getPlants();
		List<Sunflower> sunflowers = plants.stream()
			.filter(entity-> entity instanceof Sunflower)
			.map(entity -> (Sunflower) entity)
			.collect(Collectors.toList());
		return sunflowers.size() * Sunflower.SunPower;
	}

	public void sunshinePhase() {
		int numSunSpawn = (int)(Math.random() * 5 + 1);
		int valToSet = 0;
		System.out.println("Wow! " + numSunSpawn + " Sunshine has formed this turn." );
		System.out.println("Gathering Sunshine!"); 
		if(numSunSpawn !=0) {
			int addToTotal = 25*numSunSpawn; 
			 valToSet += gameState.getSunPoints()+addToTotal;
		}
		valToSet+= getSunshine();
		gameState.setSunPoints(valToSet);
		System.out.println(gameState.getSunPoints());
	}
	

	public void spawnWave() {
		Random rand = new Random();	
		for(int i=0; i<(rand.nextInt(2)+1); i++) {
			Zombie z = new Zombie(55, 66, "Bob", new Position(8, rand.nextInt(5)),2);
			gameState.addEntity(z);
		}
	}
	
	public void userPhase() {
		Boolean endPhase = false;
		System.out.println(gameState.toString());
		System.out.println("Make your move! \n");
		while(endPhase==false) {
			System.out.println("what would you like to do?\nThe available commands are: Plant | Remove | End | Help");
			String r = scanner.next();
			r = r.toLowerCase();
			if(r.equals("plant")) {
				String name = getPlantName();
				Position pos = getPosition();
				potPlant(name, pos);
				System.out.println(gameState.toString());
				System.out.println(gameState.getSunPoints());
			}
			else if(r.equals("remove")) {
				Position pos = getPosition();
				Entity entToRemove = getPlantToRemove(pos);
				gameState.removeEntity(entToRemove);
				System.out.println(gameState.toString());
				System.out.println(gameState.getSunPoints());
			}
			else if(r.equals("end")) {
				endPhase = true;
			}
			else if(r.equals("help")) {
				System.out.println("Plant - pot a plant on the board based of the position\n");
				System.out.println("Remove - remove a plant on the board based of the position that has already been planted\n");
				System.out.println("End- End your turn\n");
			}
		}
		//scanner.close();
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
	
	public static void main(String[] args) {
		Level one = new Level(30, new ArrayList<Zombie>());
		GameState state = new GameState(one);
		Game game = new Game();
		game.gameState=state;
		do {
			game.sunshinePhase();
			game.userPhase();
			game.movePhase();
		} while(game.endPhase());
		
	}
}
