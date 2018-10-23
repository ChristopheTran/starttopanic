package entity_package;

public class Zombie extends Entity{

	int moveSpeed;
	
	public Zombie(int health, int attack, String description, Position position, int moveSpeed) {
		super(health, attack, description, position);
		this.moveSpeed = moveSpeed;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	
}
