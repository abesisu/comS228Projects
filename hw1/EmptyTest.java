package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class EmptyTest {
	private Town t;
	private Empty emt;
	
	@Before
	public void setup() {
		t = new Town(4, 4);
		emt = new Empty(t, 0, 0);
	}
	
	@Test
	public void testLetter() { // The Casual object should have the letter 'C' assigned to it
		assertEquals('E', emt.letter);
	}
	
	@Test
	public void testWho() { // The Casual object should return the enum CASUAL state
		assertEquals(State.EMPTY, emt.who());
	}
	
	@Test
	public void testNext() { // Based off of the pdf key, the cells in the first row, cell (1, 3), cell (2, 2) and cells (3, 1) - (3, 3) should all return a new Empty object
		t.randomInit(10);
		for (int m = 0; m < t.getWidth(); m++) {
			assertEquals(emt.getClass(), t.grid[0][m].next(t).getClass());
		}
		assertEquals(emt.getClass(), t.grid[1][3].next(t).getClass());
		assertEquals(emt.getClass(), t.grid[2][2].next(t).getClass());
		for (int m = 1; m < t.getWidth(); m++) {
			assertEquals(emt.getClass(), t.grid[3][m].next(t).getClass());
		}
	}
}
