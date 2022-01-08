package edu.iastate.cs228.hw1;

import org.junit.Test;
import static org.junit.Assert.*;

public class TownCellTest {
	private Town t;
	
	@Test
	public void testCensus() { // For a grid initialized randomly with 4 rows and columns, the census for the first element should be equal to arrayCheck
		int[] arrayCheck = {1, 2, 0, 0, 0};
		int[] nCensus = new int[5];
		
		t = new Town(4, 4);
		t.randomInit(10);
		
		t.grid[0][0].census(nCensus);
		for (int i = 0; i < arrayCheck.length; i++) {
			assertTrue(arrayCheck[i] == nCensus[i]);
		}
	}
}
