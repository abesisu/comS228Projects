package edu.iastate.cs228.hw1;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 *  @author Abe Scheideman
 *  Test cases for Casual class.
 *
 */

public class CasualTest {
	private Town t;
	private Casual cas;
	
	@Before
	public void setup() {
		t = new Town(4, 4);
		cas = new Casual(t, 0, 0);
	}
	
	@Test
	public void testLetter() { // The Casual object should have the letter 'C' assigned to it
		assertEquals('C', cas.letter);
	}
	
	@Test
	public void testWho() { // The Casual object should return the enum CASUAL state
		assertEquals(State.CASUAL, cas.who());
	}
	
	@Test
	public void testNext() { // Based off of the pdf key, the cells at (1,0) - (3, 0) and cell (1, 1) should all return a new Casual object
		t.randomInit(10);
		for (int n = 1; n < t.getLength(); n++) {
			assertEquals(cas.getClass(), t.grid[n][0].next(t).getClass());
		}
		assertEquals(cas.getClass(), t.grid[1][1].next(t).getClass());
	}
	
}