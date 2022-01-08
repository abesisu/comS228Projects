package edu.iastate.cs228.hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * 
 * @author Abe Scheideman
 *
 */

/**
 * 
 * This class will take a given infix string and manipulate it into a postfix string.
 * It also checks the given infix expression line from the input.txt file for correctness.
 *
 */
public class PostfixBuilder {	
	/**
	 * The infix representation of the expression.
	 */
	private String infix;
	
	/**
	 * The scanner used to scan the infix expression.
	 */
	private Scanner infixScanner;

	/**
	 * The operator stack used for building the postfix string and checking precedence.
	 */
	private MyLinkedStack<Operator> opStack;


	/**
	 * Constructor used to set the instance variables to scan the file containing the infix expressions.
	 * 
	 * @throws FileNotFoundException
	 */
	public PostfixBuilder() {
		infix = "";
		infixScanner = new Scanner(infix);
		opStack = new MyLinkedStack<Operator>();
	}
	

	/**
	 * Writes the postfix expression or corresponding exception to the output file.
	 * 
	 * @throws IOException 
	 */
	public void writeOutput() throws IOException {
		File inputFile = new File("input.txt");
		Scanner inputScanner = new Scanner(inputFile);
		File outputFile = new File("output.txt");
		FileWriter fw = new FileWriter(outputFile);
		
		//Scans each line of the input.txt and writes the output for each line to output.txt.
		while (inputScanner.hasNextLine()) {
			infix = inputScanner.nextLine();
			if (inputScanner.hasNextLine()) {
				fw.write(buildPostfix() + "\n");			
			}
			else {
				fw.write(buildPostfix());
			}
		}
		
		infixScanner.close();
		inputScanner.close();
		fw.close(); 
	}
	
	/**
	 * Uses the built in input precedence and stack precedence of the Operators. 
	 * It first checks the infix expression for correctness.
	 * When a operator is scanned, the precedence is checked with the top of the stack.
	 * 
	 * @return postfix
	 */
	protected String buildPostfix() {
		try {
			checkInfix();
		}
		catch (IncorrectInfixException e){
			//If the given infix expression is not formed correctly, write the message to output.txt.
			return e.getMessage();
		}
		
		String curr;
		String postfix = "";
		infixScanner = new Scanner(infix);
		
		//Scan all the symbols and form the postfix expression.
		while (infixScanner.hasNext()) {
				curr = infixScanner.next();
			
			//If the symbol is an operator and has a higher precedence than what is on the stack, push it on the stack.
			if (isOperator(curr) && isHigher(curr)) {
				Operator currOp = new Operator(curr);
				opStack.push(currOp);
			}
			//If the symbol is an operator and it has lower precedence, pop the stack
			//and append the operator until the input has higher precedence than the top of the stack.
			else if (isOperator(curr) && !isHigher(curr)) {
				while (!isHigher(curr)) {
					String topOp = opStack.pop().op;
					
					//Parentheses are supposed to be popped and not added to the postfix expression
					if (topOp.equals("(") || topOp.equals(")"))
						continue;
					
					postfix += topOp + " ";
				}
				
				//When this is true, a subexpression has been concatenated with postfix and the "(" needs to be removed.
				if(curr.equals(")")) {
					opStack.pop();
				}
			}
			//A number has been scanned and should be concatenated with the postfix expression.
			else if (!isOperator(curr)) {
					postfix += curr + " ";
			}
		}
		//If there are any remaining operators on the stack, add them to the end of the string.
		postfix = addLastOps(postfix);

		return postfix;
	}
	
	/**
	 * Checks whether or not the given input is an operator.
	 * 
	 * @param s
	 * @return true or false
	 */
	private boolean isOperator(String s) {
		return "()^*%/+-".contains(s);
	}
	
	
	/**
	 * Checks the precedence of the top of the stack compared to the input symbol.
	 * If the input has a lower or equal precedence than the top of the stack, 
	 * return false to indicate the stack should be popped.
	 * If the input precedence is higher than the top of the stack, 
	 * return true to indicate the symbol should be pushed.
	 * 
	 * @param input
	 * @return true or false
	 */
	private boolean isHigher(String input) {
		if (opStack.isEmpty()) {
			return true;
		}
		Operator newOp = new Operator(input);
		Operator topOp = opStack.peek();

		if (newOp.inputPrec > topOp.stackPrec) {
			return true;
		}

		return false;
	}
	
	
	/**
	 * Adds any last operators that are on the stack to the postfix expression.
	 * 
	 * @param postfix
	 * @return postfix
	 */
	private String addLastOps(String postfix) {
		while (!opStack.isEmpty()) {
			String topOp = opStack.pop().op;
			//This if statement checks for a case where a number has been surrounded with parentheses (5).
			//The infix expression would technically be correct which is why no exception is thrown. e.g. 2 + (5)
			if (topOp.equals("(") || topOp.equals(")"))
				continue;
			
			if (opStack.size() == 1) {
				postfix += topOp;
			}
			else {
				postfix += topOp + " ";
			}
		}
		return postfix;
	}
	

	/**
	 * Checks the scanned infix expression and evaluates the rank of the expression to ensure it is properly formed.
	 * Scans each symbol and increments the overall rank. If the rank is less than 0, there are too many operators.
	 * If the rank is above 1, the expression has too many operands. 
	 * The method also checks to make sure the parentheses are well formed.
	 * 
	 * @param infixExp
	 * @return true
	 * @throws IncorrectInfixException
	 */
	private  boolean checkInfix() throws IncorrectInfixException {
		infixScanner = new Scanner(infix);
		Scanner nextSymScanner = new Scanner(infix);
		
		int rank = 0;
		String currSym = "";
		String nextSym = nextSymScanner.next();
		MyLinkedStack<String> parentheses = new MyLinkedStack<String>();
		
		try {
			while (infixScanner.hasNext()) {
				currSym = infixScanner.next();
				if (nextSymScanner.hasNext()) {
					nextSym = nextSymScanner.next();
				}
				
				//Can't have floating parentheses e.g. "( )"
				if (currSym.equals("(") && nextSym.equals(")")) {
					throw new IncorrectInfixException("Error: no subexpression detected ()");
				}
				else if (currSym.equals("(")) {
					parentheses.push(currSym);
				}
				else if (currSym.equals(")")) {
					//If there is a closing parenthesis, there should be a matching opening parenthesis on the stack.
					try {
						parentheses.pop();
					} catch (NoSuchElementException e) {
						throw new IncorrectInfixException("Error: no opening parenthesis detected");
					}
				}
				
				//Calculate the rank after each symbol is scanned.
				rank += addRank(currSym);

				if (rank > 1) {
					throw new IncorrectInfixException("Error: too many operands (" + currSym + ")");
				}
				else if (rank < 0) {
					throw new IncorrectInfixException("Error: too many operators (" + currSym + ")");
				}
			}

			//If there are still opening parentheses on the stack, there were not enough closing parenthesis.
			if (!parentheses.isEmpty()) {
				throw new IncorrectInfixException("Error: no closing parenthesis detected");
			}
			
			return true;
		} finally {
			nextSymScanner.close();
		}
	}
	

	/**
	 * This method returns what value should be added to the rank for each new symbol scanned.
	 * If the operator is a parenthesis, it doesn't change the rank.
	 * If the operator is a normal operator, it subtracts one from the rank.
	 * Normal numbers add 1 to the rank.
	 * 
	 * @param currOp
	 * @return 0 or -1 or 1
	 */
	private int addRank(String currOp) {
		if (currOp.equals("(") || currOp.equals(")")) {
			return 0;
		}
		else if (currOp.equals("^") || currOp.equals("*") || currOp.equals("%") || 
				 currOp.equals("/") || currOp.equals("+") || currOp.equals("-")) {
			return -1;
		}
		else {
			return 1;
		}
	}

}
