
public class Fraction {
	
	// variables
	int num; // numerator
	private int den; // denominator
	
	// 2 parameter constructor
	public Fraction(int num, int den) {
		this.num = num;
		this.den = den;
		
		// if den is 0
		if(den == 0) {
			throw new IllegalArgumentException("The denomonater cannot be 0.");			
		}
		
		// if den is negative
		if(den < 0) {
			
			// and num is also negative
			if(num < 0) {
				
				// Make both num and den positive
				this.den = Math.abs(den);
				this.num = Math.abs(num);
			}
			
			// only den is negative
			else {
				this.den = Math.abs(den);
				this.num = -num;
			}
		}		
	}
	
	// 1 parameter constructor	
	public Fraction(int x) {
		this.num = x;
		this.den = 1;
	}

	// no parameter constructor
	public Fraction() {
		this.num = 0;
		this.den = 1;
	}
	
	private int getNum() {
		return num;
	}
	
	private int getDen() {
		return den;
	}
	
	@Override
	public String toString() {
		return(Integer.toString(this.num) + "/" + Integer.toString(this.den));
	}
	
	// change fraction to a double
	private float toDouble(Fraction x) {
		return (float)x.num / x.den;
	}
	
	// add two fractions
	static Fraction add(Fraction x, Fraction y) {
		
		Fraction result = new Fraction();
		int xn, yn = 0;
		
		// if denominators are the same
		if(x.den == y.den) {
			result.num = x.num + y.num;
			result.den = x.den;
			result = toLowestTerms(result);
			return result;
		}
			
		// denominators are different
		else {
			result.den = x.den * y.den;			
			
			xn = x.num * (result.den / x.den);
			yn = y.num * (result.den / y.den);
			result.num = xn + yn;
			
			result = toLowestTerms(result);
			return result;
		}
	}
	
	// subtract two fractions
	static Fraction subtract(Fraction x, Fraction y) {
		
		Fraction result = new Fraction();
		int xn, yn = 0;
		
		// if denominators are the same
		if(x.den == y.den) {
			result.num = x.num - y.num;
			result.den = x.den;
			result = toLowestTerms(result);
			return result;
		}
			
		// denominators are different
		else {
			int newDen = x.den * y.den;
			
			xn = x.num * (newDen / x.den);
			yn = y.num * (newDen / y.den);
			
			result.num = xn - yn;
			result.den = newDen;			
			
			result = toLowestTerms(result);
			return result;
		}		
	}
	
	// multiply two fractions
	static Fraction multiply(Fraction x, Fraction y) {
		
		Fraction result = new Fraction();
		
		result.num = x.num * y.num;
		result.den = x.den * y.den;
		result = toLowestTerms(result);
		return result;		
	}
	
	// divide two fractions
	static Fraction divide(Fraction x, Fraction y) {
		
		Fraction result = new Fraction();
		
		// create reciprocal of y
		int temp = y.num;
		y.num = y.den;
		y.den = temp;
		
		result.num = x.num * y.num;
		result.den = x.den * y.den;
		result = toLowestTerms(result);
		return result;		
	}
	
	// check if two fractions are equal
	static boolean areEqual(Fraction x, Fraction y) {
		
		if(!(x instanceof Fraction) || (!(y instanceof Fraction))) {
			return false;			
		}
		
		Fraction lowestX = toLowestTerms(x);
		Fraction lowestY = toLowestTerms(y);
		if((lowestX.num == lowestY.num) && (lowestX.den == lowestY.den)) {
			return true;
		} else {
			return false;
		}
	}
	
	// convert fraction to lowest terms
	static Fraction toLowestTerms(Fraction z) {
		
		int a = z.num;
		int b = z.den;
		
		int divisor = gcd(a, b);
		
		z.num = (z.num / divisor);
		z.den = (z.den / divisor);
		
		return z;		
	}
	
	// find greatest common divisor
	static int gcd(int a, int b) {
			
			int temp;
			
			// b > a, swap them
			if(b > a) {
				temp = a;
				a = b;
				b = temp;				
			}
			
			do {
			temp = a % b;
			a = b;			
			b = temp;
			} while(temp != 0);
			
			return a;		
	}
	
	// handle whole numbers (ex. 5/1)
	static boolean wholeNumber(Fraction x) {
		
		if(x.den == 1) {
			return true;
		} else {
			return false;
		}
	}
}
