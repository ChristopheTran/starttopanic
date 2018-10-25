package entity_package;
import level_package.Level;
/**
 * This is a convenience class to encode position information for entities. Positions when
 * created must be within a level's board constraints. Positions are encoded as (x, y)
 */
public class Position {
	private int x;
	private int y;
	
	/**
	 * Constructor for the Position class
	 * @param x The x coordinate of the position
	 * @param y The y coordinate of the position
	 * @throws IndexOutOfBoundsException When a position is out of bounds of a level
	 */
	public Position(int x, int y) throws IndexOutOfBoundsException{
		if (x < 0 | x >= Level.X_BOUNDARY | y < 0 | y >= Level.Y_BOUNDARY ) {
			throw new IndexOutOfBoundsException();
		}
		this.x = x;
		this.y = y;
	}


	/**
	 * Retrieves the x coordinate of a Position
	 * @return The x coordinate of a Position
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x coordinate of a Position to a value
	 * @param x The x coordinate of a Position to be set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Retrieves the y coordinate of a Position
	 * @return The y coordinate of a Position
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y coordinate of a Position to a value
	 * @param y The y coordinate of a Position to be set
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Checks if two Positions are in the same lane i.e. have the same y values
	 * @param position The position to be compared to
	 * @return True if the positions are in the same lane false otherwise
	 */
	public boolean sameLane(Position position) {
		return  position.y == y;
	}
	
	/**
	 * Checks if two Positions are equal i.e. they are overlapping
	 * @param position The position to be compared to
	 * @return True if the positions are equal, false otherwise
	 */
	public boolean equals(Position position) {
		return position.x == x && position.y == y;
	}
}
