package game_package;

import level_package.Level;

import java.util.*;

import entity_package.Entity;
import entity_package.Peashooter;
import entity_package.Plant;
import entity_package.Position;
import entity_package.Sunflower;

public class Game {
	
	Level level;
	int sunPoints;
	int numSunSpawn;
	int gamePhase;
	Collection<Entity> entity;
	
	/**
	 * Constructor
	 * @param level the level being played
	 */
	public Game(Level level) {
		this.level = level;
		this.level.updateBoard();
		entity = level.getAllEntities();
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
	 * The entities in the level
	 * @return
	 */
	public Collection<Entity> getEntities(){
		return entity;
	}
	
	/**
	 * The level that is being played
	 * @return the current level
	 */
	public Level getlevel() {
		return level;
	}
	
	/**
	 * Adds the plant to the gameboard and checks conditions
	 * @param name the name of the plant taken as input
	 * @param x the x position
	 * @param y the y position
	 */
	
	public void addEntity(String name, int x, int y) {
		String condition = name.toLowerCase();
		switch(condition) {
		case "sunflower":
			if(sunPoints>50 && level.checktileEntity(y, x)) {
				Sunflower sun = new Sunflower(55,0,"sun", new Position(y,x),50,1,1);
				getEntities().add(sun);
			}
			else {
				System.out.println("Not enough Points");
			}
			break;
		case "peashooter":
			if(sunPoints>=100 && level.checktileEntity(y, x)) {
				Peashooter pea = new Peashooter(55,5,"shooter", new Position(y,x),100,2,3);
				getEntities().add(pea);
			} else {
				System.out.println("Not enough Points");
			}
			break;
		default:
			System.out.println("That plant does not exist");
		}
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
		
		for(Entity x: entity) {
			if(x instanceof Sunflower) {
				sunPoints += 25;
			}
		}
		System.out.println("You have:" + getSunPoints() + " sun points!\n");
	}
	
	
	public void userPhase() {
		Boolean endPhase = false;
		System.out.println(level.toString());
		System.out.println("Make your move! \n");
		Scanner scanner = new Scanner(System.in);
		while(endPhase==false) {
			System.out.println("Enter the name of the plant you want to pot and the xy-coordinate that you would like to place it at.\nPlant Name: ");
			String plantName = scanner.next();
			System.out.println("x-Coordinate:");
			int x = scanner.nextInt();
			System.out.println("y-Coordinate:");
			int y = scanner.nextInt();
			if((x>0 && x<9) && (y>0 && y<5)) {
				addEntity(plantName,x,y);
			}
			else {
				System.out.println("!!!!Warning!!!! \nThose are not valid coordinates!");
			}
			getlevel().updateBoard();
			System.out.println(getlevel().toString());
			System.out.println("Would you like to end your turn? Input Yes to end or No to make another Move \nResponse:");
			//Scanner scan = new Scanner(System.in);
			
			String r = scanner.next();
			r.toLowerCase();
			System.out.println(r);
			//scan.close();
			if(r.equals("yes")) {
				System.out.println(r);
				endPhase=true;
			}
			else {
				System.out.println("Make your next Move!");
			}
		}
		scanner.close();
		System.out.println("Changing Phases...");
	}
	

	public static void main(String[] args) {
		Level one = new Level();
		Game game = new Game(one);
//		Sunflower p3 = new Sunflower(55,5,"shooter", new Position(0,0),50,3,3);
//		game.getEntities().add(p3);
//		Sunflower p4 = new Sunflower(55,5,"shooter", new Position(1,0),50,3,3);
//		game.getEntities().add(p4);
//		game.level.updateBoard();
		game.sunshinePhase();
		game.userPhase();
		//game.level.updateBoard();
		
		
		
		
	}
}

