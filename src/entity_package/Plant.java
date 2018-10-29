package entity_package;
/**
 * This is the general superclass of all Plant entities in the Plants vs Zombies game.
 * All plants share the characteristics of a sun point cost and a resetTime before they can 
 * be potted again.
 * 
 * @author Rahul Anilkumar, Christopher Wang, Christophe Tran, Thomas Leung
 * @version 1.0
 */
public abstract class Plant extends Entity{
	private int cost;
	private int resetTime;
	
	/**
	 * Constructor for the Plant class.
	 * @param health The health value of a Plant
	 * @param attack The attack value of a Plant
	 * @param flavourText The flavour text of a Plant
	 * @param position The instantiated position of a Plant
	 * @param cost The cost of a Plant
	 * @param resetTime The time it takes before a Plant can be potted 
	 */
	public Plant(int health, int attack, String description, Position position, int cost, int resetTime) {
		super(health, attack, description, position);
		this.cost = cost;
		this.resetTime = resetTime;
	}

	/**
	 * Retrieves the cost of a Plant
	 * @return The cost of a Plant
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * Sets the cost of a Plant to a value
	 * @param cost The cost of a Plant to be set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Retrieves the resetTime of a Plant
	 * @return The resetTime of a Plant
	 */
	public int getResetTime() {
		return resetTime;
	}

	/**
	 * Sets the resetTime of a Plant to a value
	 * @param resetTime The resetTime of a Plant to be set
	 */
	public void setResetTime(int time) {
		resetTime = time;
	}
	
}
