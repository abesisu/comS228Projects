package edu.iastate.cs228.hw1;

/**
 * 
 * @author Abe Scheideman
 * Concrete TownCell subclass implementation of the Reseller customer type.
 * Can be instantiated and represents the customers that provide cheaper, slower
 * internet to users off-grid. Utilizes inheritance from the superclass TownCell for 
 * storing field data and certain methods.
 * 
 */
public class Reseller extends TownCell {
	public Reseller(Town p, int r, int c) {
		super(p, r, c);
		letter = 'R';
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	@Override
	public State who() {
		return State.RESELLER;
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
		if (nCensus[CASUAL] <= 3) {
			return new Empty(tNew, row, col);
		}
		else if (nCensus[EMPTY] >= 3) {
			return new Empty(tNew, row, col);
		}
		else if (nCensus[CASUAL] >= 5) {
			return new Streamer(tNew, row, col);
		}
		else {
			return new Reseller(tNew, row, col);
		}
	}

}
