package edu.iastate.cs228.hw3;

import java.io.IOException;

/**
 * 
 * @author Abe Scheideman
 *
 */

/**
 * 
 * The main method for this project. By making a PostfixBuilder object, the writeOuput() method is called.
 * All infix expressions are taken from input.txt and the converted postfix expressions are written to output.txt.
 *
 */
public class Infix2Postfix {

	public static void main(String[] args) throws IOException {
		PostfixBuilder converter = new PostfixBuilder();
		converter.writeOutput();
	}
}
