package entity_package;
/**
 * This is the class for Zombie entities in the Zombies vs Zombies game.
 * All Zombies have a specified move speed that they use to move each turn
 */
public class Zombie extends Entity{
	private int moveSpeed;
	
	/**
	 * Constructor for the Zombie class.
	 * @param health The health value of a Zombie
	 * @param attack The attack value of a Zombie
	 * @param flavourText The flavour text of a Zombie
	 * @param position The instantiated position of a Zombie
	 * @param moveSpped The movement speed of a Zombie
	 */
	public Zombie(int health, int attack, String description, Position position, int moveSpeed) {
		super(health, attack, description, position);
		this.moveSpeed = moveSpeed;
	}

	/**
	 * Retrieves movement speed of a Zombie
	 * @return The movement speed of a Zombie
	 */
	public int getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * Sets the movement speed of a Zombie to a value
	 * @param moveSpeed The movement speed of a Zombie to be set
	 */
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
}
