package edu.iastate.cs228.hw2;

/**
 * 
 * @author Abe Scheideman
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 

	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
										  // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    

	protected static long scanTime; 	  // execution time in nanoseconds. 

	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		sortingAlgorithm = algo;

		if (pts == null || pts.length == 0) throw new IllegalArgumentException("Point[] pts must hold at least one Point.");

		points = new Point[pts.length];
		for (int i = 0; i < pts.length; i++) {
			points[i] = pts[i];
		}
	}


	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		sortingAlgorithm = algo;

		File pointsFile = new File(inputFileName);
		Scanner pointCounter = new Scanner(pointsFile);
		Scanner pointScan = new Scanner(pointsFile);
		
		// Ensure there are the correct number of points to make an even number of Point objects.
		int numPoints = findNumPoints(pointCounter);
		points = new Point[numPoints];

		int currX;
		int currY;
		int i = 0;
		
		// While there are numbers left in the file, scan the next two ints, make a Point object, and put it into points[].
		while (pointScan.hasNextInt()) {
			currX = pointScan.nextInt();
			currY = pointScan.nextInt();
			points[i] = new Point(currX, currY);
			i++;
		}

		pointScan.close();
	}

	
	/**
	 * Private helper method to initialize the points[] array to the correct size. Counts each integer 
	 * in the file to ensure there are is an even number to correctly make the Point objects.
	 * 
	 * @param pointsFileScanner
	 * @return totalPoints
	 * @throws InputMismatchException
	 */
	private int findNumPoints(Scanner pointsFileScanner) throws InputMismatchException {
		int totalPoints = 0;

		while (pointsFileScanner.hasNextInt()) {
			totalPoints++;
			pointsFileScanner.nextInt();
		}
		
		// If total points is even, divide it by two to initialize points to the correct size. 
		if (totalPoints % 2 != 0) {
			throw new InputMismatchException("Cannot make a full set of points from the given number of coordinate values.");
		}
		else {
			totalPoints /= 2;
		}

		return totalPoints;
	}


	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 */
	public void scan()
	{
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two 
				// rounds of sorting, have aSorter do the following: 
				// 
				//     a) call setComparator() with an argument 0 or 1. 
				//
				//     b) call sort(). 		
				// 
				//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
				//
				//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
				//
				//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
		
		//Create object to be referenced by aSorter according to sortingAlgorithm
		AbstractSorter aSorter;
		switch (sortingAlgorithm) {
		case SelectionSort:
			aSorter = new SelectionSorter(points);
			break;
		case InsertionSort:
			aSorter = new InsertionSorter(points);
			break;
		case MergeSort:
			aSorter = new MergeSorter(points);
			break;
		default:
			aSorter = new QuickSorter(points);
			break;
		}

		aSorter.setComparator(0); 			// Sort based on x values first.		
		aSorter.sort();
		int x = aSorter.getMedian().getX(); // Get the median point, then get the x value.
		
		aSorter.setComparator(1); 			// Sort based on y values second.
		aSorter.sort();		
		int y = aSorter.getMedian().getY(); // Get the median point, then get the y value.
		
		medianCoordinatePoint = new Point(x, y); // Create a new coordinate point with the median x and y values and print it to a file for testing.
		try {
			writeMCPToFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String output;
		
		// Use separate spacing since SelectionSort/InsertionSort and MergeSort/QuickSort since are the same number of letters respectively. 
		if (sortingAlgorithm == Algorithm.SelectionSort || sortingAlgorithm == Algorithm.InsertionSort) {
			output = sortingAlgorithm + "     " + points.length + "  " + scanTime;
		}
		else {
			output = sortingAlgorithm + "         " + points.length + "  " + scanTime;
		}
		
		return output; 
	}


	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return "MCP: (" + medianCoordinatePoint.getX() + ", " + medianCoordinatePoint.getY() + ")";
	}


	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * @throws IOException, FileNotFoundException 
	 */
	public void writeMCPToFile() throws IOException, FileNotFoundException
	{
		File outputFileName = new File("MCP.txt");
		FileWriter fw = new FileWriter(outputFileName);
		fw.write(this.toString());
		fw.close();
	}	
}
