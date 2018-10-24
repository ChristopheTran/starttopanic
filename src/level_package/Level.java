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
	private int waves; //Number of waves of zombies in the level
	
	public Level() {
		this.board = new Tile[Y_BOUNDARY][X_BOUNDARY];
		//initialize all the tiles on the board
		for (int row=0; row < X_BOUNDARY; row++) {
			for(int col=0; col < Y_BOUNDARY; col++) {
				board[col][row] = new Tile("grass", new Position(row,col));
			}
		}
		this.zombieList = new ArrayList<Zombie>();
		this.entities = new ArrayList<Entity>();
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
	
	public int getWaves() {
		return waves;
	}
	
	public void setWaves(int waves) {
		this.waves = waves;
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
