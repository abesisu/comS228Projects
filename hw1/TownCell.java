package edu.iastate.cs228.hw1;

/**
 * 
 * @author Abe Scheideman
 * Represents the basic identity and functionality of a grid cell 
 * the Internet Service Provider, Blaze, provides coverage for. The
 * subclasses that implement TownCell have access to the fields and
 * methods implemented here.
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	/**
	 * Stores the letter to be printed for each subclass
	 */
	protected char letter;
	
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neighborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 
		
		//TODO: Write your code here.	
		int customerType;
		// Loop through all surrounding grid spaces, 
		// skipping those that are out of bounds.
		for (int n = row - 1; n <= row + 1; ++n) {
			for (int m = col - 1; m <= col + 1; ++m) {
				// A try-catch block is implemented for the
				// TownCells next to an edge of the grid. 
				try {
					// Uses helper method to determine which 
					// customer type to count for the census.
					if (row == n && col == m) {
						continue;
					}
					else {
						customerType = nCensusIndexHelper(plain.grid[n][m].who());
						++nCensus[customerType];
					}
					
				}
				catch(IndexOutOfBoundsException e) {
					continue;
				}
			}
		}
	}
	
	/**
	 * Private helper method that uses the state of the TownCell
	 * object to determine which customerType to count in the census.
	 * 
	 * @param state from the TownCell object
	 */
	private int nCensusIndexHelper(State state) {
		int index;
		if (state == State.RESELLER) {
			index = RESELLER;
		}
		else if (state == State.EMPTY) {
			index = EMPTY;
		}
		else if (state == State.CASUAL) {
			index = CASUAL;
		}
		else if (state == State.OUTAGE) {
			index = OUTAGE;
		}
		else {
			index = STREAMER;
		}
		
		return index;
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
	
	
}
