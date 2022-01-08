package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.Before;

public class ResellerTest {
	private Town t;
	private Reseller rs;
	
	@Before
	public void setup() {
		t = new Town(4, 4);
		rs = new Reseller(t, 0, 0);
	}
	
	@Test
	public void testLetter() { // The Casual object should have the letter 'C' assigned to it
		assertEquals('R', rs.letter);
	}
	
	@Test
	public void testWho() { // The Casual object should return the enum CASUAL state
		assertEquals(State.RESELLER, rs.who());
	}
	
	@Test
	public void testNext() { // Based off of the pdf key, the cell at (0, 0) should return a new Reseller object after one billing cycle iteration
		t.randomInit(10);
		t = ISPBusiness.updatePlain(t);
		assertEquals(rs.getClass(), t.grid[0][0].next(t).getClass());
	}
}
