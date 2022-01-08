package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class OutageTest {
	private Town t;
	private Outage out;
	
	@Before
	public void setup() {
		t = new Town(4, 4);
		out = new Outage(t, 0, 0);
	}
	
	@Test
	public void testLetter() { // The Casual object should have the letter 'C' assigned to it
		assertEquals('O', out.letter);
	}
	
	@Test
	public void testWho() { // The Casual object should return the enum CASUAL state
		assertEquals(State.OUTAGE, out.who());
	}
	
	@Test
	public void testNext() { // Based off of the pdf key, the cells at (1, 2), (2,1), and (2, 3) should all return a new Outage object
		t.randomInit(10);
		assertEquals(out.getClass(), t.grid[1][2].next(t).getClass());
		assertEquals(out.getClass(), t.grid[2][1].next(t).getClass());
		assertEquals(out.getClass(), t.grid[2][3].next(t).getClass());
	}
}
