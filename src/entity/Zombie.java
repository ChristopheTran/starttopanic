package entity;
/**
 * This is the class for Zombie entities in the Zombies vs Zombies game.
 * All Zombies have a specified move speed that they use to move each turn.
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.0
 */
public class Zombie extends Entity{
	private int moveSpeed;
	private int freezeTurn;
	
	/**
	 * Constructor for the Zombie class.
	 * @param health The health value of a Zombie
	 * @param attack The attack value of a Zombie
	 * @param flavourText The flavour text of a Zombie
	 * @param position The instantiated position of a Zombie
	 * @param moveSpped The movement speed of a Zombie
	 */
	public Zombie(int health, int attack, String description, Position position, int moveSpeed, int freezeTurn) {
		super(health, attack, description, position);
		this.moveSpeed = moveSpeed;
	}
	/**
	 * Copy constructor for the Zombie class.
	 * @param zombie Zombie to be copied
	 */
	public Zombie(Zombie zombie) {
		super(zombie);
		this.moveSpeed = zombie.moveSpeed;
	}
	
	/**
	 * Clones a new zombie object
	 */
	public Zombie clone() {
		return new Zombie(this);
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
	
	/**
	 * gets the turns left of freezing of a Zombie to a value
	 * @param moveSpeed The movement speed of a Zombie to be set
	 */
	public int getFreezeTurn() {
		return freezeTurn;
	}
	
	/**
	 * Sets the number of turns the zombie is frozen
	 * @param moveSpeed The movement speed of a Zombie to be set
	 */
	public void setFreezeTurn(int turn) {
		freezeTurn = turn;
	}
}
