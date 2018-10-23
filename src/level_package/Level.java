package level_package;

import java.util.*;
import entity_package.*;

public class Level {
	public Tile[][] board;
	public Collection<Zombie> zombies; // all the zombies to be spawned for this level;
	private Collection<Entity> allEntities; // all entities on the board;
	
	public Level() {
		this.board = new Tile[5][9];
		//initialize all the tiles on the board
		for (int row=0; row < board.length; row++) {
			for(int col=0; col < board[row].length; col++) {
				Position p = new Position(row,col);
				board[row][col] = new Tile("grass", p);
			}
		}
		this.zombies = new ArrayList();
		this.allEntities = new ArrayList();
		//for testing purposes
		Zombie z1 = new Zombie(55, 66, "Bob", new Position(1,8),5);
		Zombie z2 = new Zombie(55, 66, "Bob", new Position(3,8),5);
		Zombie z3= new Zombie(55, 66, "Bob", new Position(1,8),5);
		Zombie z4= new Zombie(55, 66, "Bob", new Position(1,8),5);

		Peashooter p1 = new Peashooter(55,5,"shooter", new Position(1,3),50,3,3);
		Sunflower p2 = new Sunflower(55,5,"shooter", new Position(1,0),50,3,3);
		
		this.allEntities.add(z1);
		this.allEntities.add(z2);
		this.allEntities.add(p1);
		this.allEntities.add(p2);
		//this.allEntities.add(z3);
		//this.allEntities.add(z4);

	}
	
	public Collection<Zombie> getZombies(){
		return this.zombies;
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
	
	public Collection<Entity> getAllEntities() {
		return this.allEntities;
		
	}
	
	/**
	 * Update the board with all the entities to the corresponding tiles
	 */
	public void updateBoard() {
		for (int row=0; row < board.length; row++) {
			for(int col=0; col < board[row].length; col++) {
				for(Entity e: this.allEntities) {
					if(board[row][col].getPosition().equals(e.getPosition())) {
						board[row][col].addEntity(e);
					}
				}
			}
		}
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
	public static void main(String[] args) {
		Level one = new Level();
		one.updateBoard(); // place all entities onto the board 
		System.out.println(one);
	}
}
