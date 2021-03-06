package entity;

import java.io.Serializable;

/**
 * This is the superclass for all game entities that exist within the Plants vs Zombies game.
 * All entities share a common set of attributes such as health, attack, flavourText and
 * position. Entities are not meant to be instantiated, with either plants or zombies being
 * the relevant classes. 
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.0
 */
public class Entity implements Cloneable, Serializable{
	private int health;
	private int attack;
	private String description;
	private Position position;

	
	/**
	 * Empty Constructor to deserialize entity
	 */
	public Entity() {
	}
	
	/**
	 * Constructor for the Entity class.
	 * @param health The health value of an entity
	 * @param attack The attack value of an entity (can be 0)
	 * @param flavourText The flavour text of an entity
	 * @param position The instantiated position of an entity
	 */
	public Entity(int health, int attack, String description, Position position) {
		this.health = health;
		this.attack = attack;
		this.description = description;
		this.position = position;
	}
	
	/**
	 * Copy constructor for the Entity class.
	 * @param entity The entity to be copied
	 */
	public Entity(Entity entity) {
		this.health = entity.health;
		this.attack = entity.attack;
		this.description = new String(entity.description);
		this.position = new Position(entity.position);
	}
	/**
	 * Clones a new entity object
	 */
	public Entity clone() {
		return new Entity(this);
	}
	/**
	 * Retrieves the health of an entity
	 * @return The health of an entity
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the health of an entity to a value
	 * @param health The health of an entity to be set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * Retrieves the attack of an entity
	 * @return The attack of an entity
	 */
	public int getAttack() {
		return attack;
	}

	/**
	 * Sets the attack of an entity to a value
	 * @param attack The attack of an entity to be set
	 */
	public void setAttack(int attack) {
		this.attack = attack;
	}

	/**
	 * Retrieves the flavour text of an entity
	 * @return The flavour text of an entity
	 */
	public String getFlavourText() {
		return description;
	}

	/**
	 * Sets the flavour text of an entity to a value
	 * @param flavourText The flavour text of an entity to be set
	 */
	public void setFlavourText(String flavourText) {
		this.description = flavourText;
	}

	/**
	 * Retrieves the position of an entity
	 * @return The position of an entity
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Sets the position of an entity to a value
	 * @param position The position of an entity to be set
	 */
	public void setPosition(Position position) {
		this.position = position;
	}
	
	/**
	 * Retrieves the x coordinate of an Entity
	 * @return The x coordinate of an Entity
	 */
	public int getX() {
		return position.getX();
	}

	/**
	 * Sets the x coordinate of an Entity to a value
	 * @param x The x coordinate of an Entity to be set
	 */
	public void setX(int x) {
		position.setX(x);
	}

	/**
	 * Retrieves the y coordinate of an Entity
	 * @return The y coordinate of an Entity
	 */
	public int getY() {
		return position.getY();
	}

	/**
	 * Sets the y coordinate of an Entity to a value
	 * @param y The y coordinate of an Entity to be set
	 */
	public void setY(int y) {
		position.setY(y);
	}
	
	/**
	 * Checks if two Entities are in the same lane i.e. have the same y values
	 * @param Entity to be compared to
	 * @return True if the entities are in the same lane false otherwise
	 */
	public boolean sameLane(Entity entity) {
		return  position.getY() == entity.getY();
	}
	
	/**
	 * Checks if two Entities are in the same position
	 * @param Entity to be compared to
	 * @return True if the entities are in the same position
	 */
	public boolean collides(Entity entity) {
		return position.equals(entity.getPosition());
	}
	
	/**
	 * Returns the entity type of an entity
	 * @return EntityType of the entity
	 */
	public EntityType getEntityType() {
		if(this instanceof Peashooter) {
			return EntityType.PEASHOOTER;
		} else if(this instanceof Freezeshooter) {
			return EntityType.FREEZESHOOTER;
		} else if(this instanceof Sunflower) {
			return EntityType.SUNFLOWER;
		} else if(this instanceof Walnut) {
			return EntityType.WALNUT;
		} else if(this instanceof Zombie) {
			if(this.description.equals("walker")) {
				return EntityType.ZOMBIE_WALKER;
			}
			if(this.description.equals("runner")) {
				return EntityType.ZOMBIE_RUNNER;			
						}
			if(this.description.equals("conehead")) {
				return EntityType.ZOMBIE_CONE;
			}
		} return EntityType.NONE;
	}
	
	/**
	 * Factory method to generate entities
	 * @param type Type of entity to generate
 	 * @param position Position of entity to create
	 */
	public static Entity generateEntity(EntityType type, Position position) {
		switch(type) {
		case PEASHOOTER:
			return new Peashooter(25,50,"shooter", position, 100, 2, 3);
		case FREEZESHOOTER:
			return new Freezeshooter(25,50,"freezer", position, 100, 2, 1);
		case SUNFLOWER:
			return new Sunflower(55,0,"sun", position, 50, 1, 1);
		case WALNUT:
			return new Walnut(100,0,"tank", position, 50, 1);
		case ZOMBIE_WALKER:
			return new Zombie(100, 16, "walker", position,1,0);
		case ZOMBIE_RUNNER:
			return new Zombie(75, 16, "runner", position,2,0);
		case ZOMBIE_CONE:
			return new Zombie(150, 16, "conehead", position,1,0);
		default:
			return null;
		}
	}
}
