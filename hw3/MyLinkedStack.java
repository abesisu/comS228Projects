package edu.iastate.cs228.hw3;

import java.util.NoSuchElementException;

/**
 * 
 * @author Abe Scheideman
 *
 */

/**
 * 
 * Personal implementation of a singly-linked-list stack.
 * It has push(), peek(), pop(), size() and isEmpty() methods. 
 * It also has top and size instance variables, and a private Node class.
 * The default constructor initializes the top and size variables.
 *
 */
public class MyLinkedStack<E> {
	/**
	 * "Head" element or what is on top of the stack.
	 */
	private Node top;
	
	/**
	 * Tracks how many Nodes are in the list.
	 */
	private int size;
	
	/**
	 * Returns the number of Nodes in the list.
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Returns true if the stack has no Nodes in it and false otherwise.
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Makes the element a node and adds it to the top/front of the list/stack.
	 */
	public void push(E element) {
		Node newNode = new Node(element);
		newNode.next = top;
		top = newNode;
		size++;
	}
	
	/**
	 * Returns the data in the top Node without removing it from the stack.
	 */
	public E peek() {
		if (top == null) throw new NoSuchElementException();
		
		return top.data;
	}
	
	/**
	 * Returns the data in the top Node and removes it from the stack.
	 */
	public E pop() {
		if (top == null) throw new NoSuchElementException();
		
		E val = top.data;
		top = top.next;
		size--;
		
		return val;
	}
	
	/**
	 * Specified Node class for my implementation.
	 * Each node has data and a pointer to the next node.
	 */
	private class Node {
		public E data;
		public Node next;
		
		public Node(E element) {
			data = element;
			next = null;
		}
	}
	
}
