package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

public class TownTest {
	private Town t;
	private Town tRand;
	
	@Test
	public void testRandomInit() { // The random Town grid should be populated according to the pdf key with the given seed and dimensions
		tRand = new Town(4, 4);
		tRand.randomInit(10);
		t = new Town(4, 4);
		setT(t);
		assertEquals(t, tRand);
	}
	
	/**
	 * Initializes t to the values expected of a randomly initiated town grid (inputs: 4 4 10)
	 * or a town initiated from ISP4x4.txt. It is to be used to compare the equality of the 
	 * town grids in testFileGridInit() and testRandomInit() in TownTest.
	 * @param t
	 */
	private static void setT(Town t) {
		t.grid[0][0] =   new Outage(t, 0, 0);
		t.grid[0][1] = new Reseller(t, 0, 1);
		t.grid[0][2] =   new Outage(t, 0, 2);
		t.grid[0][3] = new Reseller(t, 0, 3);
		t.grid[1][0] =    new Empty(t, 1, 0);
		t.grid[1][1] =    new Empty(t, 1, 1);
		t.grid[1][2] =   new Casual(t, 1, 2);
		t.grid[1][3] =   new Outage(t, 1, 3);
		t.grid[2][0] =    new Empty(t, 2, 0);
		t.grid[2][1] = new Streamer(t, 2, 1);
		t.grid[2][2] =   new Outage(t, 2, 2);
		t.grid[2][3] = new Streamer(t, 2, 3);
		t.grid[3][0] =    new Empty(t, 3, 0);
		t.grid[3][1] =   new Outage(t, 3, 1);
		t.grid[3][2] = new Reseller(t, 3, 2);
		t.grid[3][3] = new Reseller(t, 3, 3);
	}
}
