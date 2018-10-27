package entity_package;
/**
 * This is the implementation of a Sunflower in the Plants vs Zombies game.
 * Sunflowers are plants that generate sun points every turn.
 */
public class Sunflower extends Plant{
	private int createSunTime; 
	public final static int SUNPOWER = 25;

	/**
	 * Constructor for Sunflower
	 * @param health The health value of a Sunflower
	 * @param attack The attack value of a Sunflower (which is always 0)
	 * @param description The flavour text of the Sunflower
	 * @param position The position of the Sunflower on the board
	 * @param cost The cost of the Sunflower
	 * @param resetTime The rounds needed to recharge 
	 * @param createSunTime Rounds needed to create points
	 */
	public Sunflower(int health, int attack, String description, Position position, 
			int cost, int resetTime, int createSunTime) {
		super(health, attack, description, position, cost, resetTime);
		this.createSunTime = createSunTime;
	}

	/**
	 * Get the value of createSunTime.
	 * @return The value of createSunTime
	 */
	public int getCreateSunTime() {
		return createSunTime;
	}

	/**
	 * Set the value of createSunTime to a value.
	 * @param createSunTime The value to be set
	 */
	public void setCreateSunTime(int createSunTime) {
		this.createSunTime = createSunTime;
	}	
}
