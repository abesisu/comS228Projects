package edu.iastate.cs228.hw2;

/**
 *  
 * @author Abe Scheideman
 *
 */

/**
 * 
 * This class implements insertion sort.   
 *
 */

public class InsertionSorter extends AbstractSorter 
{
	
	/**
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 * 
	 * @param pts  
	 */
	public InsertionSorter(Point[] pts) 
	{
		super(pts);
	}	

	
	/** 
	 * Perform insertion sort on the array points[] of the parent class AbstractSorter.  
	 */
	@Override 
	public void sort()
	{
		// Time the sorting algorithm to output to the console.
		long startTime = System.nanoTime();
		
		Point newPoint;
		int j;
		
		//Maintain that all points left of i are in sorted order and the elements at i and to the right need to be sorted.
		//Iterate over the array to take the next element and insert it into the correct sorted location.
		for (int i = 1; i < points.length; i++) {
			//Store the value of the newly encountered point to put it into the correct location.
			newPoint = points[i];
			j = i - 1;
			
			//While the point in the sorted array is greater than the newly encountered point, shift the sorted point right to make room for the new point.
			while (j > -1 && pointComparator.compare(points[j], newPoint) > 0) {
				points[j + 1] = points[j];
				j--;
			}
			
			//Insert the new point into the gap created for it (at index j + 1 because j was decremented before exiting the while loop).
			points[j + 1] = newPoint;
		}
		
		// Iterates over each point in the array which takes n time. It also iterates over the sorted elements to create a gap which can take n time.
		// Although the second loop usually isn't the full n time, the runtime is still O(n^2).
		PointScanner.scanTime = System.nanoTime() - startTime;
	}		
}
