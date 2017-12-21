/*
 * A fraction calculator app
 * 
 * @author Lee Gainer
 * @since October 2017
 */

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class FractionCalc {
	
	static public void main(String [] args) {
		
		System.out.println("This program is a fraction calculator.\n"
				+ "It will add, subtract, multiply, and divide fractions"
				+ " until you type 'Q' to quit.\n"
				+ "Please enter your fractions in the form of a/b, where a and b are integers.");
		
		printDashes();
		
		String op = "o";
		
		// User selects Q or q, program ends
		while(!op.equals("q") || op.equals("Q")) {
			
			op = getOperation();
			
			if(op.equals("q") || op.equals("Q")) {
				System.out.println("Thanks for using this calculator.");
				return;
			}
			
			// Get first fraction or integer		
			String firstFrac = getFraction();
			
			// Get second fraction or integer
			String secondFrac = getFraction();
					
			// Create new Fraction objects with user input integers
			Fraction fraction1 = createObject(firstFrac);
			Fraction fraction2 = createObject(secondFrac);
			
			// Find result of math operation and return result to user.
			// ex. 1/3 + 2/6 = 2/3
			
			// if either fraction is 0 (0/2, etc.)
			try {
				
				// run math operation user asked for
				if(op.equals("+")) {
					Fraction result = Fraction.add(fraction1, fraction2);
					outputResult(fraction1, fraction2, result, op);					
				} 
				
				else if(op.equals("-")) {
					Fraction result = Fraction.subtract(fraction1, fraction2);
					outputResult(fraction1, fraction2, result, op);
				}
				
				else if(op.equals("*")) {
					Fraction result = Fraction.multiply(fraction1, fraction2);
					outputResult(fraction1, fraction2, result, op);
				}
				
				else if(op.equals("/")) {
					Fraction result = Fraction.divide(fraction1, fraction2);
					outputResult(fraction1, fraction2, result, op);
				}
				
				else {
					Boolean result = Fraction.areEqual(fraction1, fraction2);
					if(result) {
						System.out.println(fraction1 + " and " + fraction2 + " are equal.");
					} else {
						System.out.println(fraction1 + " and " + fraction2 + " are not equal.");
					}
				}			
			}
			
			catch(ArithmeticException e) {
				System.out.println(fraction1 + " " + op + " " + fraction2 + " = undefined");
			}
			
			printDashes();
		}
		
		System.out.println("Thanks for using this calculator.");
		return;
	}

	/*
	 * Output a divider line of dashes
     * void -> void
	 */
	static void printDashes() {
		System.out.println("--------------------------------------------------------");
	}
	
	/*
	 * Get math operation (+, -, *, /, =, Q, q) from user
     * void -> String
	 */
	static String getOperation() {
		
		// Scanner for operation input
		Scanner op = new Scanner(System.in);
		System.out.println("Please enter a mathmatical operation: +, -, *, /, = or Q to quit.");
		String mathOp = op.nextLine();
		
		// Create set of acceptable math operations
		Set<String> acceptableOps = new HashSet<String>();
		acceptableOps.add("+");
		acceptableOps.add("-");
		acceptableOps.add("/");
		acceptableOps.add("*");
		acceptableOps.add("=");
		acceptableOps.add("Q");
		acceptableOps.add("q");
		
		while(!acceptableOps.contains(mathOp)) {
			System.out.println("That is not a valid input.\nSelect from: +, -, *, /, =, or Q: ");
			Scanner op2 = new Scanner(System.in);
			mathOp = op2.nextLine();
		}
		
		return mathOp;
	}
	
	/*
	 * Validate fraction input from user
     * String -> boolean
	 */
	static boolean validateFraction(String x) {
		
		// Is fraction an integer?
		if(x.matches("[0-9-]+")) {
			
			return true;
		} 
		
		String xNum = null;
		String xDen = null;		
		
		// If fraction has a /, split it
		if(x.contains("/")) {
			
			// split fraction
			String[] xParts = x.split("/");
			xNum = xParts[0];
			xDen = xParts[1];	
			
			// Are the parts integers??
			if((xNum.matches("[0-9-]+")) && (xDen.matches("[0-9-]+"))) {
				
				int den = Integer.parseInt(xDen);
				
				// If denominator is 0
				if(den == 0) {
					
					return false;
				}
								
				return true;
				
			}  else {
				
				// There are non-number chars in the parts
				System.out.println("Input contains non-number chars.");
				return false;
			}			
		}
		
		return false;
	}
	
	/*
	 * Prompts user for a valid fraction
     * void -> String
	 */
	static String getFraction() {
		
		// Scanner for fraction
		Scanner f = new Scanner(System.in);
		System.out.println("Please enter a fraction (a/b) or an integer: ");
		String fraction = f.nextLine();
		
		while(!validateFraction(fraction)) {
			System.out.println("That is not a valid input.\nPlease enter (a/b) or (a) "
					+ "where a and b are integers and b isn't 0: ");
			Scanner f2 = new Scanner(System.in);
			fraction = f2.nextLine();
		}
		
		return fraction;
	}
	
	/*
	 * Calls proper constructor to create Fraction object, returns object
     * String -> Object
	 */
	private static Fraction createObject(String x) {
		
		// If x is an integer
		if(x.matches("-?[0-9]+")) {
			int f = Integer.parseInt(x);
			Fraction fraction = new Fraction(f);
			return fraction;
		}
		
		// If x is a fraction
		else if(x.contains("/")) {
			
			String xNum;
			String xDen;
			
			// split fraction
			String[] xParts = x.split("/");
			xNum = xParts[0];
			xDen = xParts[1];
			
			int n = Integer.parseInt(xNum);
			int d = Integer.parseInt(xDen);
			Fraction fraction = new Fraction(n, d);
			return fraction;		
		}
		
		else {
			Fraction fraction = new Fraction();
			return fraction;
		}			
	}
	
	/*
	 * Checks result of math operation and outputs accordingly
     * Object, Object, Object, String -> void
	 */
	private static void outputResult(Fraction x, Fraction y, Fraction result, String op) {
		
		if(Fraction.wholeNumber(result)) {
			
			// Change fraction to whole number (ex. 5/1 to 5)
			int n = result.num;
			System.out.println(x + " " + op + " " + y + " = " + n);
		} else {
			System.out.println(x + " " + op + " " + y + " = " + result);
		}		
	}
}
