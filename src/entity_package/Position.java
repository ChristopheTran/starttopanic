package entity_package;

public class Position {

	int x;
	int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Get x position
	 * @return x position
	 */
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get y position
	 * @return y position
	 */
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public boolean equals(Position position) {
		
		if (position.getX() == x && position.getY() == y)
			return true;
		return false;
	}
}
