package edu.iastate.cs228.hw2;

/**
 *  
 * @author
 *
 */

import java.util.Comparator;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 * 
 * This abstract class is extended by SelectionSort, InsertionSort, MergeSort, and QuickSort.
 * It stores the input (later the sorted) sequence. 
 *
 */
public abstract class AbstractSorter
{
	
	protected Point[] points;    // array of points operated on by a sorting algorithm. 
	                             // stores ordered points after a call to sort(). 
	
	protected String algorithm = null; // "selection sort", "insertion sort", "mergesort", or
	                                   // "quicksort". Initialized by a subclass constructor.
		 
	protected Comparator<Point> pointComparator = null;  
	
	
	// Add other protected or private instance variables you may need. 
	

	protected AbstractSorter()
	{
		// No implementation needed. Provides a default super constructor to subclasses. 
		// Removable after implementing SelectionSorter, InsertionSorter, MergeSorter, and QuickSorter.
	}
	
	
	/**
	 * This constructor accepts an array of points as input. Copy the points into the array points[]. 
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	protected AbstractSorter(Point[] pts) throws IllegalArgumentException
	{
		// TODO
		if (pts == null || pts.length == 0) throw new IllegalArgumentException("Point[] pts must hold at least one Point.");
		
		points = new Point[pts.length];
		for (int i = 0; i < pts.length; i++) {//I think this type of copy works. points[] and pts[] are used for swapping and storing the same elements. Only the order is changing so same elements can be used.
			points[i] = pts[i];
		}
	}

		
	
	
	

	/**
	 * Generates a comparator on the fly that compares by x-coordinate if order == 0, 
	 * by y-coordinate if order == 1. Assign the 
     * comparator to the variable pointComparator. 
     *  
	 * 
	 * @param order  0   by x-coordinate 
	 * 				 1   by y-coordinate
	 * 			    
	 * 
	 * @throws IllegalArgumentException if order is less than 0 or greater than 1
	 *        
	 */
	public void setComparator(int order) throws IllegalArgumentException
	{
		// TODO 
		if (order < 0 || order > 1) throw new IllegalArgumentException("order must be 0 or 1 to set the correct comparator.");
		
		pointComparator = new XComparator();
		
		private class XComparator extends Point {
			Point pointObj = new Point();
			
			private static int compare(Point lhs, Point rhs) {
				pointObj.setXorY(true);
				return lhs.compareTo(rhs);
			}
		}
		
		private class YComparator extends Point {
			private static int compare(Point lhs, Point rhs) {
				return lhs.compareTo(rhs);
			}
		}
		
		
		setXorY(true);
	}

	

	/**
	 * Use the created pointComparator to conduct sorting.  
	 * 
	 * Should be protected. Made public for testing. 
	 */
	public abstract void sort(); 
	
	
	/**
	 * Obtain the point in the array points[] that has median index 
	 * 
	 * @return	median point 
	 */
	public Point getMedian()
	{
		return points[points.length/2]; 
	}
	
	
	/**
	 * Copys the array points[] onto the array pts[]. 
	 * 
	 * @param pts
	 */
	public void getPoints(Point[] pts)
	{
		// TODO 
	}
	

	/**
	 * Swaps the two elements indexed at i and j respectively in the array points[]. 
	 * 
	 * @param i
	 * @param j
	 */
	protected void swap(int i, int j)
	{
		// TODO 
	}	
}
