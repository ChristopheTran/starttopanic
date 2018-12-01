package entity;

import java.io.Serializable;

/**
 * This is the implementation of a Walnut in the Plants vs Zombies game.
 * Walnut are plants that tank the enemy with a lot of hp
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.0
 */
public class Walnut extends Plant implements Serializable{
	

	/**
	 * Constructor for Walnut
	 * @param health The health value of a Walnut
	 * @param attack The attack value of a Walnut (which is always 0)
	 * @param description The flavour text of the Walnut
	 * @param position The position of the Walnut on the board
	 * @param cost The cost of the Walnut
	 * @param resetTime The rounds needed to recharge 
	 * @param createSunTime Rounds needed to create points
	 */
	public Walnut(int health, int attack, String description, Position position, 
			int cost, int resetTime) {
		super(health, attack, description, position, cost, resetTime);

	}
	/**
	 * Copy constructor for Walnut
	 * @param sunflower Walnut to be copied
	 */
	public Walnut(Walnut walnut) {
		super(walnut);
	}
	/**
	 * Clones a Walnut object
	 */
	public Walnut clone() {
		return new Walnut(this);
	}	
}
