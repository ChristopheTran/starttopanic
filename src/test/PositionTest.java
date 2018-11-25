package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Position;

/**
 * Test case for testing position of the entities
 * @author Christophe Tran, Rahul Anilkumar, Christopher Wang, Thomas Leung
 *
 */
class PositionTest {
	Position pos1, pos2, pos3;
	
	
	/**
	 * Creates all the initial values used in the test case
	 * @throws Exception throws if an exception occurs
	 */
	@BeforeEach
	void setUp() throws Exception {
		pos1 = new Position(1,1);
		pos2 = new Position(1,1);
		pos3 = new Position(2,1);
	}
	/**
	 * Test if entities are on the same lane (y position)
	 */
	@Test
	void testSameLane() {
		assertTrue("Check same lane but different x lane is the same lane", pos1.sameLane(pos3));
		assertFalse("Check entities that are position on different lane", pos1.sameLane(new Position(2, 2)));
	}
	/**
	 * Test if entities are at the same position (x and y)
	 */
	@Test
	void testEquals() {
		assertTrue("Check that the passed position is the same as the position of the current object", pos1.equals(pos2));
		assertFalse("Check that the passed position is NOT the same as the position fo the current object", pos1.equals(pos3));
	}

}
