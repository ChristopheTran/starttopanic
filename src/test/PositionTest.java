package test;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import entity.Position;

class PositionTest {
	Position pos1, pos2, pos3;
	

	@BeforeEach
	void setUp() throws Exception {
		pos1 = new Position(1,1);
		pos2 = new Position(1,1);
		pos3 = new Position(2,1);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testSameLane() {
		assertTrue("check same lane but different x lane is the same lane", pos1.sameLane(pos3));
	}
	@Test
	void testEquals() {
		assertTrue("Check that the passed position is the same as the position of the current object", pos1.equals(pos2));
	}

}
