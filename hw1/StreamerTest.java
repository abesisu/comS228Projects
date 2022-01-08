package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class StreamerTest {
	private Town t;
	private Streamer s;
	
	@Before
	public void setup() {
		t = new Town(4, 4);
		s = new Streamer(t, 0, 0);
	}
	
	@Test
	public void testLetter() { // The Casual object should have the letter 'C' assigned to it
		assertEquals('S', s.letter);
	}
	
	@Test
	public void testWho() { // The Casual object should return the enum CASUAL state
		assertEquals(State.STREAMER, s.who());
	}
	
	@Test
	public void testNext() { // Based off of the pdf key, the cell at (2, 2) should return a new Reseller object after two billing cycle iterations
		t.randomInit(10);
		t = ISPBusiness.updatePlain(t);
		t = ISPBusiness.updatePlain(t);
		assertEquals(s.getClass(), t.grid[2][2].next(t).getClass());
	}
}
