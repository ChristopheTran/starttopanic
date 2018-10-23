package level_package;

import java.util.*;
import entity_package.*;

public class Tile {
	private String name; // the type of terrain
	private Collection<Entity> entities; // entities currently on the tile
	private Position position; // position of tile on the board
	
	public Tile(String name, Position position) {
		this.name = name;
		this.entities = new ArrayList<Entity>();
		this.position = position;		
	}
	
	public String getName() {
		return this.name;
	}
	
	public Collection<Entity> getEntities() {
		return this.entities;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public void addEntity(Entity entity) {
		this.entities.add(entity);
	}
	
	public void removeEntity(Entity entity) {
		this.entities.remove(entity);
	}
}
