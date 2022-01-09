package edu.iastate.cs228.hw2;


/**
 *  
 * @author Abe Scheideman
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 			
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
		PointScanner[] scanners = new PointScanner[4]; 
		Scanner userInput = new Scanner(System.in);
		int key;
		int trial = 1;
		int numPoints;
		String fileName;
		
		System.out.println("keys:  1 (random integers)  2 (file input)  3 (exit)");
		System.out.print("Trial " + trial + ": ");
		key = userInput.nextInt();
		
		// Initialize each PointScanner object either from an array of random points or from a file with the coordinates.
		do {
			if (key == 1) {
				System.out.print("Enter number of random points: ");
				numPoints = userInput.nextInt();
				Random generator = new Random();
				
				scanners[0] = new PointScanner(generateRandomPoints(numPoints, generator), Algorithm.SelectionSort);
				scanners[1] = new PointScanner(generateRandomPoints(numPoints, generator), Algorithm.InsertionSort);
				scanners[2] = new PointScanner(generateRandomPoints(numPoints, generator), Algorithm.MergeSort);
				scanners[3] = new PointScanner(generateRandomPoints(numPoints, generator), Algorithm.QuickSort);
			}
			else if (key == 2) {
				System.out.println("Points from a file");
				System.out.print("File name: ");
				fileName = userInput.next();

				scanners[0] = new PointScanner(fileName, Algorithm.SelectionSort);
				scanners[1] = new PointScanner(fileName, Algorithm.InsertionSort);
				scanners[2] = new PointScanner(fileName, Algorithm.MergeSort);
				scanners[3] = new PointScanner(fileName, Algorithm.QuickSort);
			}
			
			// Output the performance table and get the next user input.
			printPerformanceTable(scanners);
			trial++;
			System.out.print("Trial " + trial + ": ");
			key = userInput.nextInt();
		} while (key != 3);
		
		userInput.close();
	}

	/**
	 * This method formats the console output correctly. 
	 * It calls each PointScanner objects respective scan() and stats() method.
	 * @param pScanners
	 */
	private static void printPerformanceTable(PointScanner[] pScanners) 
	{
		System.out.println("\nalgorithm         size  time (ns)");
		System.out.println("- - - - - - - - - - - - - - - - -");

		for (PointScanner ps : pScanners) {
			ps.scan();
			System.out.println(ps.stats());
		}

		System.out.println("- - - - - - - - - - - - - - - - -\n");
	}


	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		Point[] pts = new Point[numPts];
		
		// Create the new point object and put it in the array pts.
		for (int i = 0; i < numPts; i++) {
			pts[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
		}
		
		return pts; 
	}

}
