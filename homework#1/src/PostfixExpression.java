
/**
 * Program Name: PostfixExpression.java
 * @author Roei Burstein
 * Current Date: Tuesday, January 23, 2018
 * Computer Operating System: Mac OS High Sierra Version 10.13.2
 * Compiler: Eclipse Oxygen 4.7.0
 * <p>
 * Description: This program is a postfix evaluator and postfix to infix converter. 
 * The conversion process and evaluation process are done using array and linked stacks.
 * <p>
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class PostfixExpression {

	private String wholeExpr; // Whole postfix expression
	private ArrayList<String> tokens; // ArrayList of Strings that make up the expression
	private double result; // Evaluated result of postfix expression
	public static Scanner userScanner = new Scanner(System.in);

	/**
	 * Default constructor, creates a default postfix expression.
	 */
	public PostfixExpression() {
		wholeExpr = "";
		tokens = new ArrayList<String>();
		result = Double.NaN;
	} // end default constructor

	/**
	 * Constructor with postfix expression as the parameter.
	 * 
	 * @param s
	 *            String with the postfix expression
	 */
	public PostfixExpression(String s) {
		this();
		setExp(s);
	} // end constructor

	/**
	 * Mutator for the wholeExpr value in the object.
	 * 
	 * @param s
	 *            The postfix expression to save into the PostfixExpression object.
	 * @return True if the expression is valid, false if it is not.
	 */
	public boolean setExp(String s) {
		wholeExpr = s;
		tokenize();
		return evaluatePostfix();
	} // end setExp()

	/**
	 * Accessor for the wholeExpr variable.
	 * 
	 * @return The postfix expression.
	 */
	public String getWholeExp() {
		return wholeExpr;
	} // end getWholeExp()

	/**
	 * Accessor for the result variable.
	 * 
	 * @return The evaluated result.
	 */
	public double getResult() {
		return result;
	} // end getResult

	/**
	 * Converts postfix expression into an infix expression.
	 * 
	 * @return The resulting infix expression.
	 */
	public String postfixToInfix() {
		ArrayStack<String> stack = new ArrayStack<>(); // Array Stack which holds the tokens.

		for (String token : tokens) {
			if (isOperator(token)) {
				if (stack.size() < 2)
					return "error 1";

				String expression = buildExpression(token, stack.pop(), stack.pop());
				stack.push(expression);
			} else {
				stack.push(token);
			}
		}

		if (stack.size() != 1)
			return "error 2";

		return stack.pop();
	} // end postfixToInfix

	/**
	 * Checks if the input is an operator.
	 * 
	 * @param token
	 *            String with either operator or operand.
	 * @return True if the token is an operator, false if it is not.
	 */
	private boolean isOperator(String token) {
		assert (token.length() == 1);

		return "+-/*".contains(token);
	} // end isOperator

	/**
	 * Builds the infix expression in a nice, readable format.
	 * 
	 * @param operator
	 *            The operator between the left and right operands.
	 * @param right
	 *            The operand to the right of the operator.
	 * @param left
	 *            The operand to the left of the operator.
	 * @return The String with the infix expression.
	 */
	private String buildExpression(String operator, String right, String left) {
		return "(" + left + operator + right + ")";
	} // end buildExpresssion

	/**
	 * Splits the expression into individual operators and operands
	 */
	private void tokenize() {
		String[] tokenArray = wholeExpr.split("[ \t]+");
		tokens.clear(); // clear the ArrayList
		for (int i = 0; i < tokenArray.length; ++i) {
			tokens.add(tokenArray[i]); // add the next token to the ArrayList
		}
	} // end tokenize

	/**
	 * Evaluates the postfix expression. Saves the result into global result
	 * variable.
	 * 
	 * @return True if the postfix expression is valid, false if it is not.
	 */
	private boolean evaluatePostfix() {
		LinkedStack<Double> stack = new LinkedStack<Double>();

		for (String token : tokens) {
			if (isOperator(token)) {
				if (stack.isEmpty())
					break;
				Double right = stack.pop();

				if (stack.isEmpty())
					break;
				Double left = stack.pop();

				Double expressionResult = evaluateExpression(token, left, right);
				stack.push(expressionResult);
			} else {
				stack.push(Double.parseDouble(token));
			}
		}

		if (stack.size() == 1) {
			result = stack.pop();
			return true;
		} else {
			result = Double.NaN;
			return false;
		}
	} // end evaluatePostfix

	/**
	 * Calculates the result using the left, right, and operator of a binary
	 * expression.
	 * 
	 * @param token
	 *            Operator in the binary expression.
	 * @param left
	 *            Left operand in the binary expression.
	 * @param right
	 *            Right operand in the binary expression.
	 * @return Result of the binary expression.
	 */
	private Double evaluateExpression(String token, Double left, Double right) {
		Double expressionResult = left;

		switch (token) {
		case "+":
			expressionResult += right;
			break;

		case "-":
			expressionResult -= right;
			break;

		case "*":
			expressionResult *= right;
			break;

		case "/":
			expressionResult /= right;
			break;

		default:
			throw new ArithmeticException("Illegal Operator");
		}

		return expressionResult;
	} // end evaluateExpression

	/**
	 * Opens and parses the input file.
	 * 
	 * @return Scanner object with the contents of the file.
	 */
	public static Scanner openInputFile() {
		String filename;
		Scanner scanner = null;

		System.out.print("Enter the input filename: ");
		filename = userScanner.nextLine();
		File file = new File(filename);

		try {
			scanner = new Scanner(file);
		} // end try
		catch (FileNotFoundException fe) {
			System.out.println("Can't open input file\n");
			return null; // array of 0 elements
		} // end catch
		return scanner;
	} // end openInputFile

	/**
	 * Tester for the program.
	 */
	public static void stackTester() {
		ArrayStack<String> arrStack = new ArrayStack<>();
		LinkedStack<String> linkStack = new LinkedStack<>();
		String[] strArray = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
		String temp;

		// Testing ArrayStack
		System.out.println("\nTesting the ArrayStack:");
		for (int i = 0; i < strArray.length; ++i) {
			temp = strArray[i] + " 1";
			if (arrStack.push(temp))
				System.out.println("Pushed " + temp + ", size is now= " + arrStack.size());
			else
				System.out.println("Error: couldn't push " + temp + ", size is now= " + arrStack.size());
		}
		for (int i = 0; i < strArray.length; ++i) {
			temp = strArray[i] + " 2";
			System.out.println("Trying to push " + temp);
			if (!arrStack.push(temp)) {
				System.out.println("Out of space, removing " + arrStack.pop());
				if (arrStack.push(temp))
					System.out.println("Pushed " + temp);
				else
					System.out.println("Error pushing " + temp);
			}
		}
		System.out.println("The size of the ArrayStack is now " + arrStack.size());
		for (int i = 0; i < strArray.length / 2; ++i) {
			System.out.println("Popping " + arrStack.pop());
		}
		System.out.println("The size of the ArrayStack is now " + arrStack.size());

		temp = strArray[0] + " 3";
		if (arrStack.push(temp))
			System.out.println("Pushed " + temp + ", size is now= " + arrStack.size());
		else
			System.out.println("Error: couldn't push " + temp + ", size is now= " + arrStack.size());
		while (!arrStack.isEmpty()) {
			System.out.println("Popping " + arrStack.pop());
		}
		System.out.println("The size of the ArrayStack is now " + arrStack.size());

		// testing LinkedStack
		System.out.println("\nTesting the LinkedStack:");
		for (int i = 0; i < strArray.length; ++i)
			linkStack.push(strArray[i] + " 4");
		System.out.println("The size of the LinkedStack is " + linkStack.size());
		for (int i = 0; i < strArray.length / 2; ++i) {
			System.out.println("Popping " + linkStack.pop());
		}
		System.out.println("The size of the LinkedStack is now " + linkStack.size());
		while (!linkStack.isEmpty()) {
			System.out.println("Popping " + linkStack.pop());
		}
		System.out.println("The size of the LinkedStack is now " + linkStack.size());
	} // end stackTester

} // end PostfixExpression
