package entity;
/**
 * This is the implementation of a Freezeshooter in the Plants vs Zombies game.
 * Freezeshooters are plants that attack with a given rate of fire each turn.
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.0
 */
public class Freezeshooter extends Plant{
	private int rateOfFire;
	/**
	 * Constructor for the Freezeshooter class.
	 * @param health The health value of a Freezeshooter
	 * @param attack The attack value of a Freezeshooter
	 * @param description The flavour text of a Freezeshooter
	 * @param position The instantiated position of a Freezeshooter
	 * @param cost The cost of a Freezeshooter
	 * @param resetTime The time it takes before a Freezeshooter can be potted 
	 * @param rateOfFire The rate of fire of a Freezeshooter
	 */
	public Freezeshooter(int health, int attack, String description, Position position, 
						int cost, int resetTime, int rateOfFire) {
		super(health, attack, description, position, cost, resetTime);
		this.rateOfFire = rateOfFire;
	}
	/**
	 * Copy constructor for the Freezeshooter class.
	 * @param Freezeshooter The Freezeshooter to be copied
	 */
	public Freezeshooter(Freezeshooter freezeshooter) {
		super(freezeshooter);
		this.rateOfFire = freezeshooter.rateOfFire;
	}
	
	/**
	 * Clones a Freezeshooter object
	 */
	public Freezeshooter clone() {
		return new Freezeshooter(this);
	}
	
	/**
	 * Retrieves the rateOfFire of a Freezeshooter
	 * @return The rateOfFire of a Freezeshooter
	 */
	public int getRateOfFire() {
		return rateOfFire;
	}

	/**
	 * Sets the rateOfFire of a Freezeshooter to a value
	 * @param rateOfFire The rateOfFire of a Freezeshooter to be set
	 */
	public void setRateOfFire(int rateOfFire) {
		this.rateOfFire = rateOfFire;
	}

	
}
