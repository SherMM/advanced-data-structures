/**
 * Implements functionalities to operate on big integers.
 * The operations include add, subtract, multiply, divide, modulo, power, square root and factorial of very large numbers.
 * 
 * 
 * Note: Need the base to be set one time before the operations are used.
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */


public class BigNumber implements Comparable<BigNumber>, Cloneable {

	private static PolynomialCalculator polynomialCalculator = PolynomialCalculator.getInstance();
	private static Long base = null;

	private Polynomial polynomial;
	
	/**
	 * Gets the base of the BigNumber Class
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	
	private static Long getBase() throws Exception {
		
		if(BigNumber.base == null) {
			
			throw new Exception("base not set!!");
		}
		
		return base;
	}

	
	/**
	 * Sets the base of the BigNumber Class
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public static void setBase(Long base) throws Exception {
		
		
		if (BigNumber.base != null) {
			
			throw new Exception("base already set!!");
		}
		 
		BigNumber.base = base;
	}
	
	/**
	 * Construtor to create an empty BigNumber object.
	 * 
	 * 
	 * 
	 * @throws Exception
	 */

	public BigNumber() throws Exception {
		
		polynomial = new Polynomial(BigNumber.getBase());
	}
	
	/**
	 * Constructor to create an BigNumber object for a given long integer.
	 * 
	 * 
	 * @param number
	 * @throws Exception
	 */
	
	public BigNumber(Long number) throws Exception {
		
		polynomial = new Polynomial(BigNumber.getBase(), number);
	}

	/**
	 * Constructor to create an BigNumber object for a given integer as string.
	 * 
	 * 
	 * @param number
	 * @throws Exception
	 */
	
	
	public BigNumber(String number) throws Exception {
		
		polynomial = new Polynomial(BigNumber.getBase(), number);
	}
	
	/**
	 * Returns a clone of the calling object.
	 * 
	 * 
	 */
	
	
	public BigNumber clone() {
		
		BigNumber bigNumber = null;
		try {
			bigNumber = new BigNumber();
			bigNumber.polynomial = new Polynomial(BigNumber.getBase());
			
			for(Term term : this.polynomial) {
				
				bigNumber.polynomial.add(term.clone());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		return bigNumber;
	}
	
	/**
	 * returns true if the calling object is positive else false;
	 * 
	 * 
	 * @return
	 */
	
	public boolean isPositive() {
		
		return this.polynomial.isPositive();
	}
	
	/**
	 * returns true if the calling object is zero else false;
	 * 
	 * 
	 * 
	 * @return
	 */

	public boolean isZero() {
		
		return this.polynomial.isZero();
	}
	
	/**
	 * returns the absolute value of the calling object.
	 * 
	 * 
	 * @return
	 * @throws Exception
	 */
	
	public BigNumber abs() throws Exception {
		
		BigNumber result = new BigNumber();
		
		result.polynomial = this.polynomial.abs();
		
		return result;
	}
	
	/**
	 * Adds two big numbers
	 * 
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public static BigNumber add(BigNumber operand1, BigNumber operand2) throws Exception {
		
		BigNumber bigNumber = new BigNumber();
		bigNumber.polynomial = polynomialCalculator.add(operand1.polynomial, operand2.polynomial);
		return bigNumber;
	}
	
	/**
	 * Subtracts two big numbers
	 * 
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public static BigNumber subtract(BigNumber operand1, BigNumber operand2) throws Exception {
		
		BigNumber bigNumber = new BigNumber();
		bigNumber.polynomial = polynomialCalculator.subtract(operand1.polynomial, operand2.polynomial);
		return bigNumber;
	}
	
	/**
	 * Multiplies two big numbers
	 * 
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public static BigNumber multiply(BigNumber operand1, BigNumber operand2) throws Exception {
		
	
		BigNumber bigNumber = new BigNumber();
		bigNumber.polynomial = polynomialCalculator.multiply(operand1.polynomial, operand2.polynomial);
		return bigNumber;
	}
	
	/**
	 * Divides two big numbers
	 * 
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public static BigNumber divide(BigNumber operand1, BigNumber operand2) throws Exception {

		BigNumber bigNumber = new BigNumber();
		bigNumber.polynomial = polynomialCalculator.divide(operand1.polynomial, operand2.polynomial);
		return bigNumber;
	}
	
	/**
	 * Applies modulo on two big numbers
	 * 
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public static BigNumber mod(BigNumber operand1, BigNumber operand2) throws Exception {
		
		BigNumber bigNumber = new BigNumber();
		bigNumber.polynomial = polynomialCalculator.mod(operand1.polynomial, operand2.polynomial);
		return bigNumber;
	}
	
	/**
	 * finds square root of a big number
	 * 
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	public static BigNumber squareRoot(BigNumber operand) throws Exception {
		
		BigNumber bigNumber = new BigNumber();
		bigNumber.polynomial = polynomialCalculator.squareRoot(operand.polynomial);
		return bigNumber;
	}
	
	/**
	 * finds the power of big number to n.
	 * 
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public static BigNumber power(BigNumber operand, long n) throws Exception {
		
		BigNumber bigNumber = new BigNumber();
		bigNumber.polynomial = polynomialCalculator.power(operand.polynomial, n);
		return bigNumber;
	}
	
	/**
	 * finds the power of big number to another big number n.
	 * 
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	
	public static BigNumber power(BigNumber operand, BigNumber n) throws Exception {
	
		BigNumber bigNumber = new BigNumber();
		bigNumber.polynomial = polynomialCalculator.power(operand.polynomial, n.polynomial);
		return bigNumber;
	}
	
	
	/**
	 * finds the factorial of big number.
	 * 
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public static BigNumber factorial(BigNumber operand) throws Exception {

		BigNumber bigNumber = new BigNumber();
		bigNumber.polynomial = polynomialCalculator.factorial(operand.polynomial);
		return bigNumber;
	}

	/**
	 * prints the internal representation of Big Number;
	 * 
	 * 
	 */
	public void printList() {
		
		System.out.println(this.polynomial);
	}

	
	/**
	 * returns the string representation of given big number
	 * 
	 * 
	 */
	
	@Override
	public String toString() {
		
	
		return this.polynomial.toDecimalString();	
	}
	
	/**
	 * compares calling big number with that another big number;
	 * 
	 * 
	 */
	
	@Override
	public int compareTo(BigNumber o) {

		return this.polynomial.compareTo(o.polynomial);
	}
}
