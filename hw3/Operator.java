package edu.iastate.cs228.hw3;

/**
 * 
 * @author Abe Scheideman
 *
 */

/**
 * 
 * This class stores the precedence values and string representation for the given symbol.
 *
 */
public class Operator {
	/**
	 * The given symbol for which the Operator represents.
	 */
	protected String op;
	
	/**
	 * The precedence used when the operator is not on the stack.
	 */
	protected int inputPrec;
	
	/**
	 * The precedence used when the operator is on the stack.
	 */
	protected int stackPrec;
	
	/**
	 * For the given operator, assigns the corresponding inputPrec and stackPrec.
	 */
	public Operator(String givenOp) {
		op = givenOp;
		setPrec();
	}
	
	
	/**
	 * For the given operator, this method sets the inputPrec and stackPrec.
	 * These values will be used when evaluating whether to pop or push the operator stack.
	 */
	private void setPrec() {
		if (op.equals(")")) {
			inputPrec = 0; stackPrec = 0;
		}
		else if (op.equals("(")) {
			inputPrec = 5; stackPrec = -1;
		}
		else if (op.equals("^")) {
			inputPrec = 4; stackPrec = 3;
		}
		else if (op.equals("*") || op.equals("%") || op.equals("/")) {
			inputPrec = 2; stackPrec = 2;
		}
		else if (op.equals("+") || op.equals("-")) {
			inputPrec = 1; stackPrec = 1;
		}	
	}
}

