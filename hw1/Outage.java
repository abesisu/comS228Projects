package edu.iastate.cs228.hw1;

/**
 * 
 * @author Abe Scheideman
 * Concrete TownCell subclass implementation of the Outage cell type.
 * Can be instantiated and represents a cell that has recently been put
 * out of internet. Utilizes inheritance from the superclass TownCell for 
 * storing field data and certain methods.
 * 
 */
public class Outage extends TownCell {
	public Outage(Town p, int r, int c) {
		super(p, r, c);
		letter = 'O';
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	@Override
	public State who() {
		return State.OUTAGE;
	}

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	@Override
	public TownCell next(Town tNew) {
		return new Empty(tNew, row, col);
	}

}
