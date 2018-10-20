package entity_package;

public class Sunflower extends Plant{

	boolean sunSpawned = false;
	int createSunTime;

	public Sunflower(int health, int attack, String description, Position position, 
			int cost, int resetTime, int createSunTime) {
		super(health, attack, description, position, cost, resetTime);
		this.createSunTime = createSunTime;
	}

	/**
	 * Get remaining time to create a new sun
	 * @return remaining time
	 */
	public int getCreateSunTime() {
		return createSunTime;
	}

	public void setCreateSunTime(int createSunTime) {
		this.createSunTime = createSunTime;
	}

	/**
	 * To spawn a sun
	 * @return true if createSunTime is 0, false otherwise.
	 */
	public boolean spawnSun() {
		
		if (getCreateSunTime() == 0) {
			return true;
		}
		return false;
	}
	
}
