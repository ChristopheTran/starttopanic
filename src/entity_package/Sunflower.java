package entity_package;
/**
 * This is the implementation of a Sunflower in the Plants vs Zombies game.
 * Sunflowers are plants that generate sun points every turn.
 */
public class Sunflower extends Plant{
	private int createSunTime;
	public final static int SUNPOWER = 25;

	public Sunflower(int health, int attack, String description, Position position, 
			int cost, int resetTime, int createSunTime) {
		super(health, attack, description, position, cost, resetTime);
		this.createSunTime = createSunTime;
	}

	public int getCreateSunTime() {
		return createSunTime;
	}

	public void setCreateSunTime(int createSunTime) {
		this.createSunTime = createSunTime;
	}	
}
