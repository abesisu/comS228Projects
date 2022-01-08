package edu.iastate.cs228.hw3;

/**
 * 
 * @author Abe Scheideman
 *
 */

/**
 * 
 * Represents my custom infix exception that will print a specific message
 * based on what the error detected is in the infix expression.
 *
 */
@SuppressWarnings("serial")
public class IncorrectInfixException extends Exception {
	public IncorrectInfixException(String errorMessage) {
		super(errorMessage);
	}
}
