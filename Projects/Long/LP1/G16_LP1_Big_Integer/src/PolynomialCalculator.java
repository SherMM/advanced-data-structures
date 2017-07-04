/**
 * Singleton class. Extends Polynomial Advanced Calculator. performs all basic and advanced functions on polynomial.
 * 
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */
public class PolynomialCalculator extends PolynomialAdvancedCalculator {
	
	private static PolynomialCalculator polynomialCalculator = new PolynomialCalculator();
	
	/**
	 * 
	 * Empty private constructor.
	 * 
	 */
	private PolynomialCalculator() {
		
	}
	
	/**
	 * gets the singleton instance of polynomial calculator.
	 * 
	 * @return
	 */
	public static PolynomialCalculator getInstance() {
		
		return polynomialCalculator;
	}
}
