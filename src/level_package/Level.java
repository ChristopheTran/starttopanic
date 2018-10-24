package level_package;

import java.util.*;
import java.util.stream.Collectors;
import entity_package.*;

public class Level {
	public Tile[][] board;
	public List<Zombie> zombieList; // all the zombieList to be spawned for this level;
	private List<Entity> entities; // all entities on the board;
	
	public Level() {
		this.board = new Tile[5][9];
		//initialize all the tiles on the board
		for (int row=0; row < board.length; row++) {
			for(int col=0; col < board[row].length; col++) {
				board[row][col] = new Tile("grass", new Position(row,col););
			}
		}
		this.zombieList = new ArrayList();
		this.entities = new ArrayList();
	}
	
	public Collection<Zombie> getzombieListList(){
		return this.zombieList;
	}
	
	public Tile getTile(Position p) {
		for (int row=0; row < board.length; row++) {
			for(int col=0; col < board[row].length; col++) {
				if(board[row][col].getPosition().equals(p)) {
					return board[row][col];
				}
			}
		}
		return null;
	}
	
	public Collection<Entity> getEntities() {
		return this.entities;
		
	}
	
	public List<Entity> checkTileEntity(int x, int y){
		List<Entity> entities = this.entities.stream()
				.filter(entity -> entity.getPosition().equals(new Position(x, y)))
				.collect(Collectors.toList());
		return entities;
	}

	public String toString() {
		//String s ="     0     1     2     3     4     5     6     7     8\n";
		String s = "";
		for (int row=0; row < board.length; row++) {
			//s+=row;
			for(int col=0; col < board[row].length; col++) {
				s+="| ";
				Collection<Entity> entities = board[row][col].getEntities();
				if(!entities.isEmpty()) { // if there are entities on a tile
					for(Entity e: entities ) {
						if (e instanceof Zombie) {
							s+=" Z  ";
						}
						else if( e instanceof Peashooter) {
							s+= " P  ";
						}
						else if( e instanceof Sunflower) {
							s+= " S  ";
						}
					}		
				}
				else { // if no entities on a tile, print empty tile
					s += "    ";
				}
			}
			s+= " |\n";
			s+="----------------------------------------------------------\n";
		}
		return s;
		
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
		System.out.println(one.toString());
		
	}
}
