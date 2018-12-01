package level;
import java.io.Serializable;

import entity.*;
/**
 * This is the class of for different tiles of a Plants vs Tiles game. As of right now
 * different terrain types/hazards have not been implemented. A tile's position and name
 * are unintended to be mutable and thus do not have setters.
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.0
 */
public class Tile implements Serializable{
	private String name;
	private Position position;
	
	/**
	 * Constructor for the Tile class.
	 * @param name The name of a Tile
	 * @param position The position of a Tile
	 */
	public Tile(String name, Position position) {
		this.name = name;
		this.position = position;		
	}
	
	/**
	 * Retrieves the name of a Tile
	 * @return The name of a Tile
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Retrieves position of a Tile
	 * @return The position of a Tile
	 */
	public Position getPosition() {
		return this.position;
	}
}
