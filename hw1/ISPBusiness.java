package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Abe Scheideman
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {
	/**
	 * Constant value used to calculate annual profit
	 */
	private static final int TOTAL_BILLING_CYCLE = 12;

	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		// Loops through the town's grid setting each TownCell to the next correct customer cell
		for (int n = 0; n < tNew.getLength(); n++) {
			for (int m = 0; m < tNew.getWidth(); m++) {
				// Uses the next method from the old TownCell at the grid location
				tNew.grid[n][m] = tOld.grid[n][m].next(tNew); 
			}
		}
		return tNew;
	}

	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return townProfit
	 */
	public static int getProfit(Town town) {
		int townProfit = 0;
		Casual c = new Casual(town, town.getLength(), town.getWidth());

		// Loops through the grid and counts how many Casual cells there are
		for (int n = 0; n < town.getLength(); n++) {
			for (int m = 0; m < town.getWidth(); m++) {
				if (town.grid[n][m].getClass() == c.getClass()) {
					townProfit++;
				}
			}
		}
		return townProfit;
	}

	/**
	 * Calculates the profit from each iteration of the town for a year.
	 * Prints the profit utilization as a percentage of the max potential profit.
	 * @param town
	 */
	private static void printProfit(Town town) {
		Town tNew = town;
		int profit = 0;
		double profitUtilization;

		// Sums the profits from each billing cycle in a year for the town
		// Line 70 can be uncommented to print the entire town for each billing cycle
		for (int itr = 0; itr < TOTAL_BILLING_CYCLE; itr++) {
			// tNew.toString();
			profit += getProfit(tNew);
			tNew = updatePlain(tNew);
		}

		double maxProfit = town.getLength() * town.getWidth() * TOTAL_BILLING_CYCLE;
		profitUtilization = (profit / maxProfit) * 100.0;

		System.out.print(String.format("%.2f", profitUtilization) + "%");
	}


	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		Scanner userInput = new Scanner(System.in);

		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");

		// If user wants to use a file, try to open the file and populate the grid from it.
		// ISP4x4.txt
		if (userInput.nextInt() == 1) {
			boolean goodFile = false;

			while (!goodFile) {
				try {
					System.out.println("Please enter file path:");
					String filePath = userInput.next();

					Town town = new Town(filePath);
					File gridFile = new File(filePath);
					
					// Populate the grid using helper method
					fileGridInit(gridFile, town);
					
					printProfit(town);

					goodFile = true;
				}
				catch (FileNotFoundException e) {
					System.out.println("Invalid file path. Enter a valid file path: ");
					continue;
				}
			}


		}
		else {
			userInput.nextLine();
			System.out.println("Provide rows, cols and seed integer separated by spaces: ");
			String gridDetails = userInput.nextLine();

			Scanner stringScan = new Scanner(gridDetails);
			int userRow  = stringScan.nextInt();
			int userCol  = stringScan.nextInt();
			int userSeed = stringScan.nextInt();
			stringScan.close();

			// Initialize and populate the town
			Town town = new Town(userRow, userCol);
			town.randomInit(userSeed);

			printProfit(town);
		}
		userInput.close();
	}

	/**
	 * Private helper method to populate the town's grid from a file. Throws FileNotFoundException
	 * if the file can't be found. Uses "this" instantiation of the Town class to put the TownCell
	 * objects in the grid.
	 * @param gridFile
	 * @param t
	 * @return grid
	 * @throws FileNotFoundException
	 */
	private static void fileGridInit(File gridFile, Town t) throws FileNotFoundException {
		Scanner fileScanner = new Scanner(gridFile);
		char currChar;
		
		// Get to to the first letter of the grid
		while(fileScanner.hasNextInt()) {
			fileScanner.nextInt();
		}
		fileScanner.nextLine();
		
		
		while (fileScanner.hasNext()) {
			for (int n = 0; n < t.getLength(); ++n) {
				for (int m = 0; m < t.getWidth(); ++m) {
					currChar = fileScanner.next().charAt(0);
					if (currChar == 'C') {
						t.grid[n][m] = new Casual(t, n, m);
					}
					else if (currChar == 'S') {
						t.grid[n][m] = new Streamer(t, n, m);
					}
					else if (currChar == 'R') {
						t.grid[n][m] = new Reseller(t, n, m);
					}
					else if (currChar == 'E') {
						t.grid[n][m] = new Empty(t, n, m);
					}
					else {
						t.grid[n][m] = new Outage(t, n, m);
					}
				}
			}
		}

		fileScanner.close();
	}

}
