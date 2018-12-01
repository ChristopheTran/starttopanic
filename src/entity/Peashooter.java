package entity;

import java.io.Serializable;

/**
 * This is the implementation of a Peashooter in the Plants vs Zombies game.
 * Peashooters are plants that attack with a given rate of fire each turn.
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.0
 */
public class Peashooter extends Plant implements Serializable{
	private int rateOfFire;
	/**
	 * Constructor for the Peashooter class.
	 * @param health The health value of a Peashooter
	 * @param attack The attack value of a Peashooter
	 * @param description The flavour text of a Peashooter
	 * @param position The instantiated position of a Peashooter
	 * @param cost The cost of a Peashooter
	 * @param resetTime The time it takes before a Peashooter can be potted 
	 * @param rateOfFire The rate of fire of a Peashooter
	 */
	public Peashooter(int health, int attack, String description, Position position, 
						int cost, int resetTime, int rateOfFire) {
		super(health, attack, description, position, cost, resetTime);
		this.rateOfFire = rateOfFire;
	}
	/**
	 * Copy constructor for the Peashooter class.
	 * @param peashooter The peashooter to be copied
	 */
	public Peashooter(Peashooter peashooter) {
		super(peashooter);
		this.rateOfFire = peashooter.rateOfFire;
	}
	
	/**
	 * Clones a peashooter object
	 */
	public Peashooter clone() {
		return new Peashooter(this);
	}
	
	/**
	 * Retrieves the rateOfFire of a Peashooter
	 * @return The rateOfFire of a Peashooter
	 */
	public int getRateOfFire() {
		return rateOfFire;
	}

	/**
	 * Sets the rateOfFire of a Peashooter to a value
	 * @param rateOfFire The rateOfFire of a Peashooter to be set
	 */
	public void setRateOfFire(int rateOfFire) {
		this.rateOfFire = rateOfFire;
	}

	
}
