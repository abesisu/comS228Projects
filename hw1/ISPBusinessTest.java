package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *  @author Abe Scheideman
 *  Test cases for Casual class.
 *
 */

public class ISPBusinessTest {
	private Town t;
	private Town tItr1;
	
	@Before
	public void setup() {
		t = new Town(4, 4);
		t.randomInit(10);
		tItr1 = new Town(4, 4);
		setTItr1(tItr1);
	}
	
	@Test
	public void testUpdatePlain() { // Checks whether updatePlain correctly updates the town grid after one iteration according to the pdf key
		Town tNew = ISPBusiness.updatePlain(t);
		assertEquals(tItr1, tNew);
	}
	
	/**
	 * Initializes tItr1 to the values expected of a randomly initiated town grid (inputs: 4 4 10)
	 * after one call to updatePlain(). It is to be used to compare the equality of the town grids
	 * in the test case.
	 * @param tItr1
	 */
	private static void setTItr1(Town tItr1) {
		tItr1.grid[0][0] =  new Empty(tItr1, 0, 0);
		tItr1.grid[0][1] =  new Empty(tItr1, 0, 1);
		tItr1.grid[0][2] =  new Empty(tItr1, 0, 2);
		tItr1.grid[0][3] =  new Empty(tItr1, 0, 3);
		tItr1.grid[1][0] = new Casual(tItr1, 1, 0);
		tItr1.grid[1][1] = new Casual(tItr1, 1, 1);
		tItr1.grid[1][2] = new Outage(tItr1, 1, 2);
		tItr1.grid[1][3] =  new Empty(tItr1, 1, 3);
		tItr1.grid[2][0] = new Casual(tItr1, 2, 0);
		tItr1.grid[2][1] = new Outage(tItr1, 2, 1);
		tItr1.grid[2][2] =  new Empty(tItr1, 2, 2);
		tItr1.grid[2][3] = new Outage(tItr1, 2, 3);
		tItr1.grid[3][0] = new Casual(tItr1, 3, 0);
		tItr1.grid[3][1] =  new Empty(tItr1, 3, 1);
		tItr1.grid[3][2] =  new Empty(tItr1, 3, 2);
		tItr1.grid[3][3] =  new Empty(tItr1, 3, 3);
	}	
	
}
