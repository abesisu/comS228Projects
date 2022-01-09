package edu.iastate.cs228.hw2;

import java.util.Comparator;

/**
 * 
 * @author Abe Scheideman
 *
 */

/**
 * This class is a wrapper for the compareTo method. It allows two point objects to be compared by their Y value.
 * 
 * @return either -1, 1, or 0
 *
 */
public class YComparator implements Comparator<Point> {
	public int compare(Point lhs, Point rhs) {
		Point.setXorY(false);
		return lhs.compareTo(rhs);
	}
}
