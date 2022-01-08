package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


/**
 *  @author Abe Scheideman
 *  Town object used to initialize and store the data of the town.
 *
 */
public class Town {

	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;

	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}

	/**
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		File gridFile = new File(inputFileName);
		Scanner fileScanner = new Scanner(gridFile);
		length = fileScanner.nextInt();
		width = fileScanner.nextInt();
		grid = new TownCell[length][width];
		fileScanner.close();
	}

	/**
	 * Returns width of the grid.
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns length of the grid.
	 * @return length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 * @param seed
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		for (int n = 0; n < length; ++n) {
			for (int m = 0; m < width; ++m) {
				int randInt = rand.nextInt(5);
				
				if (randInt == 0) {
					grid[n][m] = new Reseller(this, n, m);
				}
				else if (randInt == 1) {
					grid[n][m] = new Empty(this, n, m);
				}
				else if (randInt == 2) {
					grid[n][m] = new Casual(this, n, m);
				}
				else if (randInt == 3) {
					grid[n][m] = new Outage(this, n, m);
				}
				else {
					grid[n][m] = new Streamer(this, n, m);
				}

			}
		}
	}

	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 * @return s 
	 */
	@Override
	public String toString() {
		String s = "";
		for (int n = 0; n < length; n++) {
			for (int m = 0; m < length; m++) {
				s += grid[n][m].letter;
				s += ' ';
			}
			s += "\n";
		}
		System.out.println(s);
		return s;
	}
	
	/**
	 * Custom equals() method to compare the grid of the randomly initialized town (tNew 
	 * after 1 iteration and the expected grid of the town after one iteration (tItr1).
	 * Only called from the ISPBusinessTest class when checking the updatePlain method in ISPBusiness, 
	 * and TownTest in testRandomInit().
	 * @param obj
	 * @return
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null | obj.getClass() != getClass()) {
			return false;
		}
		
		Town tNew = (Town) obj;
		Boolean fieldsSame = false;
		Boolean gridSame = true;
		
		fieldsSame = (length == tNew.getLength() && width == tNew.getWidth());
		
		while(gridSame) {
			for (int n = 0; n < length; n++) {
				for (int m = 0; m < width; m++) {
					if (tNew.grid[n][m].getClass() != grid[n][m].getClass()) {
						gridSame = false;
						break;
					}
				}
				if (!gridSame) {
					break;
				}
			}
			break;
		}
		
		return fieldsSame && gridSame;
	}
}
