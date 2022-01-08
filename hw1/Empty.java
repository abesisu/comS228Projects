package edu.iastate.cs228.hw1;

/**
 * 
 * @author Abe Scheideman
 * Concrete TownCell subclass implementation of the Outage cell type.
 * Can be instantiated and represents a cell that is not being used. 
 * Utilizes inheritance from the superclass TownCell for 
 * storing field data and certain methods.
 * 
 */
public class Empty extends TownCell {
	public Empty(Town p, int r, int c) {
		super(p, r, c);
		letter = 'E';
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	@Override
	public State who() {
		return State.EMPTY;
	}

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	@Override
	public TownCell next(Town tNew) {
		census(nCensus);
		if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
			return new Reseller(tNew, row, col);
		}
		return new Casual(tNew, row, col);			
	}

}
