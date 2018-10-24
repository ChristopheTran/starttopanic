package entity_package;

import level_package.Level;

public class Position {

	int x;
	int y;
	
	public Position(int x, int y) throws IndexOutOfBoundsException{
        if (x < 0 | x >= Level.X_BOUNDARY | y < 0 | y >= Level.Y_BOUNDARY ) {
            throw new IndexOutOfBoundsException();
        }
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
