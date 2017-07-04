

/**
 * Abstract class. Provides Squareroot, Power and Factorial Operations for Polynomial
 * 
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */

public abstract class PolynomialAdvancedCalculator extends PolynomialBasicCalculator {

	
	/**
	 * Returns the square root of a given polynomial.
	 * 
	 * @param operand
	 * @return
	 * @throws Exception
	 */
	
	public Polynomial squareRoot(Polynomial operand) throws Exception {
		
		Polynomial start = new Polynomial(operand.getBase(), 1L);
		Polynomial end = operand;
		Polynomial middle;
		Polynomial one = new Polynomial(operand.getBase(), 1L);
		
		while(add(start, one).compareTo(end) < 0) {
			
			middle = getMiddleElement(start, end);
			long compare = operand.compareTo(multiply(middle, middle));
			if (compare < 0) {
				
				end = middle;
			} else if(compare > 0) {
				
				start = middle;
			} else {
				
				return middle;
			}
		}
		
		return start;
	}
	
	/**
	 * Returns the nth power of a given polynomial 
	 * 
	 * @param operand
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public Polynomial power(Polynomial operand, long n) throws Exception {
		
		if (n < 0) {
			
			return new Polynomial(operand.getBase(), 0L);
		}else if (n == 0) {
			
			return new Polynomial(operand.getBase(), 1L);
		} else if (n == 1) {
			
			return operand;
		} else {
			
			Polynomial result = power(operand, n/2);
			
			result = multiply(result, result);
			
			if(n%2 == 1) {
				
				result = multiply(operand, result);
			}
			
			return result;
		}
	}
	
	/**
	 * Shifts the polynomial by 1 thereby reducing powers terms by 1.
	 * Used in power calculation.
	 * 
	 * @param n
	 * @return
	 */
	
	private Polynomial shift(Polynomial n) {
		
		Polynomial result = new Polynomial(n.getBase());
		
		Term newTerm;
		for(Term term : n) {
			
			newTerm = term.clone();
			newTerm.setPower(newTerm.getPower() - 1);
			result.add(newTerm);
		}
		
		return result;
	}
	
	/**
	 * Find the nth power of a given polynomial. n - another polynomial
	 * 
	 * 
	 * @param operand
	 * @param n
	 * @return
	 * @throws Exception
	 */
	
	public Polynomial power(Polynomial operand, Polynomial n) throws Exception {
		
		raiseExceptionOnInvalidOperands(operand, n);
		
		if(n.isZero()) {
			
			return new Polynomial(n.getBase(), 1L);
		} else if(n.getLast().getPower() == 0) {
			
			return power(operand, n.getLast().getCoefficient());
		} else {
			
			Polynomial result = shift(n);
			
			result = power(operand, result);
			
			long firstTermOfn = n.getFirst().getPower() == 0 ? n.getFirst().getCoefficient() : 0;

			return multiply(power(result, n.getBase()), power(operand, firstTermOfn));
		}
	}
	
	/**
	 * 
	 * finds the factorial of a given polynomial
	 * 
	 * @param operand
	 * @return
	 * @throws Exception
	 */
	
	public Polynomial factorial(Polynomial operand) throws Exception {
	
		Polynomial result  = new  Polynomial(operand.getBase(), 1L);
		Polynomial one = new Polynomial(operand.getBase(), 1L);
		
		while(!operand.isZero()) {
			
			result = multiply(result, operand);
			operand = subtract(operand, one);
		}
		
		return result;
	}
}
