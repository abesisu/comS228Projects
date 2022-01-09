package edu.iastate.cs228.hw2;

/**
 *  
 * @author Abe Scheideman
 *
 */

/**
 * 
 * This class implements the version of the quicksort algorithm presented in the lecture.   
 *
 */

public class QuickSorter extends AbstractSorter
{
		
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *   
	 * @param pts   input array of integers
	 */
	public QuickSorter(Point[] pts)
	{
		super(pts);
	}
		

	/**
	 * Carry out quicksort on the array points[] of the AbstractSorter class.  
	 * 
	 */
	@Override 
	public void sort()
	{
		long startTime = System.nanoTime();
		quickSortRec(0, points.length - 1);
		// The quickSortRec method is most likely called logn times. However, the worst case is n times which is highly unlikely.
		// The partition method compares each point in the subarray passed to it which takes n time.
		// The worst case runtime is O(n^2) although it is almost always O(nlogn).
		PointScanner.scanTime = System.nanoTime() - startTime;
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last. 
	 * 
	 * @param first  starting index of the subarray
	 * @param last   ending index of the subarray
	 */
	private void quickSortRec(int first, int last)
	{
		// When the array has size 0 or 1, it is already sorted so just return.
		if (first >= last) return;
		
		int p = partition(first, last);
		
		// Use the pivot point to split the array rougly in half to recursively sort each side.
		quickSortRec(first, p - 1);
		quickSortRec(p + 1, last);
	}
	
	
	/**
	 * Operates on the subarray of points[] with indices between first and last.
	 * 
	 * @param first
	 * @param last
	 * @return i + 1	the location of the pivot point
	 */
	private int partition(int first, int last)
	{
		// It is somewhat safe to assume the last point in the array is not the largest or smallest and can thus be used as the pivot.
		int pivot = last;
		int i = first - 1;
		
		// The i counter keeps track of the last element that is smaller than pivot.
		// The j counter finds the next element that is smaller than pivot.
		for (int j = first; j < last; j++) {
			// Once an element smaller than pivot is found, place it at the end of the other elements that are smaller than the pivot.
			if (pointComparator.compare(points[j], points[pivot]) < 0) {
				i++;
				swap(i, j);
			}
		}
		
		// Place the pivot at the end of all the elements that are smaller than it and return it's location.
		swap(i + 1, pivot);
		return i + 1; 
	}	
}
