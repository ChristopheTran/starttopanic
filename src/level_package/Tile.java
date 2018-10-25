package level_package;

import java.util.*;
import entity_package.*;

public class Tile {
	private String name; // the type of terrain
	private Position position; // position of tile on the board
	
	public Tile(String name, Position position) {
		this.name = name;
		this.position = position;		
	}
	
	public String getName() {
		return this.name;
	}
	
	public Position getPosition() {
		return this.position;
	}
}
