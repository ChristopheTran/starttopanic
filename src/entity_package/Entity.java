package entity_package;

public class Entity {
	
	int health;
	int attack;
	String flavourText;
	Position position;

	public Entity(int health, int attack, String description, Position position) {
		super();
		this.health = health;
		this.attack = attack;
		this.flavourText = description;
		this.position = position;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public String getFlavourText() {
		return flavourText;
	}

	public void setFlavourText(String description) {
		this.flavourText = description;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
