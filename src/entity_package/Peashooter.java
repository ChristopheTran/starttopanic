package entity_package;

public class Peashooter extends Plant{

	int rateOfFire;
	
	public Peashooter(int health, int attack, String description, Position position, 
			int cost, int resetTime, int rateOfFire) {
		super(health, attack, description, position, cost, resetTime);
		this.rateOfFire = rateOfFire;
	}

	public int getRateOfFire() {
		return rateOfFire;
	}

	public void setRateOfFire(int rateOfFire) {
		this.rateOfFire = rateOfFire;
	}

	
}
