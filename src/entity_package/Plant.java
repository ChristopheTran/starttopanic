package entity_package;

public class Plant extends Entity{

	int cost;
	int resetTime;
	
	public Plant(int health, int attack, String description, Position position, int cost, int resetTime) {
		super(health, attack, description, position);
		this.cost = cost;
		this.resetTime = resetTime;
	}

	/**
	 * Get the cost of the plant
	 * @return cost
	 */
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	/**
	 * Get the time needed to buy the plants again
	 * @return
	 */
	public int getResetTime() {
		return resetTime;
	}

	public void setResetTime(int time) {
		resetTime = time;
	}
	
}
