package edu.iastate.cs228.hw2;

/**
 *  
 * @author Abe Scheideman
 *
 */

/**
 * 
 * This class implements selection sort.   
 *
 */

public class SelectionSorter extends AbstractSorter
{	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts  
	 */
	public SelectionSorter(Point[] pts)  
	{
		super(pts);
	}	

	
	/** 
	 * Apply selection sort on the array points[] of the parent class AbstractSorter.  
	 * 
	 */
	@Override 
	public void sort()
	{
		// Time the sorting algorithm to output to the console.
		long startTime = System.nanoTime();
		
		int minIndex;
		//Maintain that the point at i is the next smallest point by comparing it against the rest of the points.
		//If a smaller point is found, set minIndex to that point and swap it with the current point at i.
		for (int i = 0; i < points.length - 1; i++) {
			//Set minIndex to the point next to the last sorted element.
			minIndex = i;
			
			//If the point at index j is smaller than the point at the current minIndex, set minIndex to the new smallest point.
			for (int j = i + 1; j < points.length; j++) {
				if (pointComparator.compare(points[j], points[minIndex]) < 0) {
					minIndex = j;
				}
			}
			
			//Swap the current point with the smallest point found at minIndex.
			swap(i, minIndex);
		}
		
		// The outer loop iterates over the entire array which takes n time. 
		// The inner loop iterates on average with a time of n/2. However, the runtime is still O(n^2).
		PointScanner.scanTime = System.nanoTime() - startTime;
	}	
}
