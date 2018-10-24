package level_package;

import java.util.*;
import java.util.stream.Collectors;
import entity_package.*;

public class Level {
	public Tile[][] board;
	public static final int X_BOUNDARY = 9;
	public static final int Y_BOUNDARY = 5;
	public List<Zombie> zombieList; // all the zombieList to be spawned for this level;
	private List<Entity> entities; // all entities on the board;
	
	public Level() {
		this.board = new Tile[Y_BOUNDARY][X_BOUNDARY];
		//initialize all the tiles on the board
		for (int row=0; row < X_BOUNDARY; row++) {
			for(int col=0; col < Y_BOUNDARY; col++) {
				board[col][row] = new Tile("grass", new Position(row,col));
			}
		}

		this.zombieList = new ArrayList();
		this.entities = new ArrayList();
	}
	
	public List<Zombie> getzombieListList(){
		return zombieList;
	}
	
	public List<Entity> getEntities(){
		return entities;
	}
	
	public List<Entity> checkTileEntity(Position p){
		List<Entity> entities = this.entities.stream()
				.filter(entity -> entity.getPosition().equals(p))
				.collect(Collectors.toList());
		return entities;
	}

	public Tile getTile(Position p) throws IndexOutOfBoundsException {
		return board[p.getY()][p.getX()];
	}
	
	/**
	 * Retrieves sunshine based of the number of sunflowers on the board
	 * @return
	 */
	public int getSunshine() {
		Collection<Entity> entity = entities;
		int sunPoints = 0;
		for(Entity x: entity) {
			if(x instanceof Sunflower) {
				sunPoints += 25;
			}
		}
		return sunPoints;
	}
	
	/**
	 * Adds the plant to the gameboard and checks conditions
	 * @param name the name of the plant taken as input
	 * @param x the x position
	 * @param y the y position
	 */
	public int addEntity(String name, int x, int y, int sunPoints) {
		int points = sunPoints;
		String condition = name.toLowerCase();
		switch(condition) {
		case "sunflower":
			if(sunPoints>50 && checkTileEntity(new Position(x, y)).isEmpty()) {
				Sunflower sun = new Sunflower(55,0,"sun", new Position(x,y),50,1,1);
				getEntities().add(sun);
				points -= 50; 
				
			}
			else {
				System.out.println("Could not add plant check the position or your sun points");
			}
			break;
		case "peashooter":
			if(sunPoints>=100 && checkTileEntity(new Position(x, y)).isEmpty()) {
				Peashooter pea = new Peashooter(55,5,"shooter", new Position(x,y),100,2,3);
				getEntities().add(pea);
				points -= 100; 
			} else {
				System.out.println("Could not add plant check the position or your sun points");
			}
			break;
		default:
			System.out.println("That plant does not exist");
		}
		return points;
	}
	
	public Entity removeEntity(int x, int y) {
		List<Entity> plant = getEntities();
		for(Entity ent : plant) {
			if(ent.getPosition().equals(new Position(x,y))) {
				return ent;
			}
		}
		return null;
	}
	
	public void removeFromBoard(Entity ent) {
		entities.remove(ent);
	}
		
//		Iterator<Entity> i = plant.iterator();
//		while(i.hasNext()) {
//			Entity e = i.next();
//			System.out.println(e.getCost());
//			if(e.getPosition().equals(new Position(x,y))) {
//				System.out.println("Works");
//				return i.e
//			}
//		}
	//}
	


	public List<Zombie> getZombies(){
		List<Zombie> zombies = entities.stream()
				.filter(entity-> entity instanceof Zombie)
				.map(zombie -> (Zombie) zombie)
				.collect(Collectors.toList());
		return zombies;
	}
	
	public List<Plant> getPlants(){
		List<Plant> plants = entities.stream()
				.filter(entity-> entity instanceof Plant)
				.map(plant -> (Plant) plant)
				.collect(Collectors.toList());
		return plants;
	}
	
	public static void main(String[] args) {
		Level one = new Level();
		//System.out.println(one);
		Zombie z1 = new Zombie(55, 66, "Bob", new Position(8, 1),5);
        Zombie z2 = new Zombie(55, 66, "Bob", new Position(8, 3),5);
        Zombie z3= new Zombie(55, 66, "Bob", new Position(8, 1),5);
        Zombie z4= new Zombie(55, 66, "Bob", new Position(8, 1),5);

        Peashooter p1 = new Peashooter(55,5,"shooter", new Position(1,3),50,3,3);
        Sunflower p2 = new Sunflower(55,5,"shooter", new Position(1,0),50,3,3);

        one.entities.add(z1);
        one.entities.add(z2);
        one.entities.add(z3);
        one.entities.add(z4);
        one.entities.add(p1);
        one.entities.add(p2);
		System.out.println(one);
		
	}
	
	public String toString() {
		//Create a 2D board encoded by Char and populate it with empty cells
		char[][] board = new char[Y_BOUNDARY * 4][X_BOUNDARY * 5];
		for(int y = 0; y < Y_BOUNDARY * 4; y++) {
			for(int x = 0; x < X_BOUNDARY * 5; x++) {
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
		String s = "";
		for(int y = 0; y < Y_BOUNDARY * 4; y++) {
			for(int x = 0; x < X_BOUNDARY * 5; x++) {
				s += board[y][x];
			}
			s+= "\n";
		}
		return s;
	}
}

