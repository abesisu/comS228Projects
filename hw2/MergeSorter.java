package edu.iastate.cs228.hw2;

import java.util.Arrays;

/**
 *  
 * @author Abe Scheideman
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		// Time mergeSortRec.
		long startTime = System.nanoTime();
		mergeSortRec(points);
		// The number of recursive calls is logn with each call taking n time leading to O(nlogn).
		PointScanner.scanTime = System.nanoTime() - startTime;
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 */
	private void mergeSortRec(Point[] pts)
	{
		//Recursively split the array in half until the length is 0 or 1 in which case the array is sorted.
		if (pts.length <= 1) {
			return;
		}
		else {
			Point[] leftArray  = Arrays.copyOfRange(pts, 0, pts.length / 2 );
			Point[] rightArray = Arrays.copyOfRange(pts, pts.length / 2, pts.length);
			
			// Recursively sort the left and right halves of pts.
			mergeSortRec(leftArray);
			mergeSortRec(rightArray);
			
			//Merge the two sorted arrays
			merge(pts, leftArray, rightArray);
		}
	}

	/**
	 * This is the method that does the merging for the MergeSort algorithm. 
	 * It uses counters for the left, right, and merged arrays to insert 
	 * the next smallest element from either the left or right array into the merged array.
	 * 
	 * @param left
	 * @param right
	 */
	private void merge(Point[] merged, Point[] left, Point[] right) 
	{
		int l = left.length;
		int r = right.length;
		int i = 0; int j = 0; int k = 0;
		
		// Copy the smallest point from either the left or right array into merged.
		while (i < l && j < r) {
			if (pointComparator.compare(left[i], right[j]) < 0) {
				merged[k] = left[i];
				k++; i++;
			}
			else {
				merged[k] = right[j];
				k++; j++;
			}
		}
		
		// If the left array has points that haven't been copied to merged, copy those now.
		if (i < j) {
			while (i < l) {
				merged[k] = left[i];
				k++; i++;
			}
		}
		// If the right array has points that haven't been copied to merged, copy those now.
		else {
			while (j < r) {
				merged[k] = right[j];
				k++; j++;
			}
		}
	}

}
