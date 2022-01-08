package edu.iastate.cs228.hw1;

/**
 * 
 * @author Abe Scheideman
 * Concrete TownCell subclass implementation of the Streamer customer type.
 * Can be instantiated and represents the customers that use a large amount
 * of bandwidth. Utilizes inheritance from the superclass TownCell for 
 * storing field data and certain methods.
 * 
 */
public class Streamer extends TownCell {
	public Streamer(Town p, int r, int c) {
		super(p, r, c);
		letter = 'S';
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	@Override
	public State who() {
		return State.STREAMER;
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
		else if (nCensus[RESELLER] > 0) {
			return new Outage(tNew, row, col);
		}
		else if (nCensus[OUTAGE] > 0) {
			return new Empty(tNew, row, col);
		}
		else if (nCensus[CASUAL] >= 5) {
			return new Streamer(tNew, row, col);
		}
		else {
			return new Streamer(tNew, row, col);
		}
	}


}
