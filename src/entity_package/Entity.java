package entity_package;
/**
 * This is the superclass for all game entities that exist within the Plants vs Zombies game.
 * All entities share a common set of attributes such as health, attack, flavourText and
 * position. Entities are not meant to be instantiated, with either plants or zombies being
 * the relevant classes. 
 */
public abstract class Entity {
	private int health;
	private int attack;
	private String flavourText;
	private Position position;

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
		this.flavourText = description;
		this.position = position;
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
		return flavourText;
	}

	/**
	 * Sets the flavour text of an entity to a value
	 * @param flavourText The flavour text of an entity to be set
	 */
	public void setFlavourText(String flavourText) {
		this.flavourText = flavourText;
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
}
