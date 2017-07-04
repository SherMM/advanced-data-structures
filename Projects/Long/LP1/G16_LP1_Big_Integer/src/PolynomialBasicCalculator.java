import java.util.Iterator;
import java.util.ListIterator;

/**
 * Provides the basic arithmetics of operation of add, subtract, multiply, divide and modulo for polynomials.
 * 
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */

public abstract class PolynomialBasicCalculator {

	/**
	 * Checks if two polynomials have same base
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 */
	public boolean checkBases(Polynomial operand1, Polynomial operand2) {
		
		return operand1.getBase() == operand2.getBase();
	}
	
	/**
	 * Raises exception if the polynomials are invalid. i.e. with different bases.
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @throws Exception
	 */
	
	public void raiseExceptionOnInvalidOperands(Polynomial operand1, Polynomial operand2) throws Exception {
		
		if(!checkBases(operand1, operand2)) {
			
			throw new Exception("Incompatible operands having different bases of " + operand1.getBase() + " and " + operand2.getBase());
		}
	}
	
	
	/**
	 * Flushes the rest of the iterator values to another polynomial
	 * 
	 * 
	 * @param currentTerm
	 * @param operandIterator
	 * @param destination
	 */
	private void flush(Term currentTerm, Iterator<Term> operandIterator, Polynomial destination) {

		while(currentTerm != null) {
			
			destination.add(currentTerm.clone());
			currentTerm = Polynomial.next(operandIterator);
		}
	
	}
	
	/**
	 * Adds two polynomials
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public Polynomial add(Polynomial operand1, Polynomial operand2) throws Exception {

		raiseExceptionOnInvalidOperands(operand1, operand2);
		
		Polynomial result = new Polynomial(operand1.getBase());
	
		Iterator<Term> operand1Iterator = operand1.iterator();
		Iterator<Term> operand2Iterator = operand2.iterator();
		
		Term operand1Term;
		Term operand2Term;
		
		operand1Term = Polynomial.next(operand1Iterator);
		operand2Term = Polynomial.next(operand2Iterator);
		
		while(operand1Term != null && operand2Term != null) { 
			
			
			if(operand1Term.getPower() < operand2Term.getPower()) {
				
				result.add(operand1Term.clone());
				operand1Term = Polynomial.next(operand1Iterator);
				
			} else if(operand1Term.getPower() > operand2Term.getPower()) {
				
				result.add(operand2Term.clone());
				operand2Term = Polynomial.next(operand2Iterator);		
			} else {
				
				result.add(operand1Term.addTo(operand2Term));
				operand1Term = Polynomial.next(operand1Iterator);
				operand2Term = Polynomial.next(operand2Iterator);
			}
		}
		
		flush(operand1Term, operand1Iterator, result);
		flush(operand2Term, operand2Iterator, result);

		result.fixOverflows();
		return result;
	}
	
	/**
	 * Subtracts two polynomials
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	
	public Polynomial subtract(Polynomial operand1, Polynomial operand2) throws Exception {
		
		operand2 = multiply(operand2, new Term(-1, 0));
		return add(operand1, operand2);
	}
	
	/**
	 * Multiplies the polynomial with the given term
	 * 
	 * @param operand
	 * @param term
	 * @return
	 */
	
	private Polynomial multiply(Polynomial operand, Term term) {
		
		Polynomial result = new Polynomial(operand.getBase());

		for(Term operandTerm : operand) {
			
			result.add(term.multipliedBy(operandTerm));
		}

		result.fixOverflows();
		return result;
	}
	
	

	/**
	 * Performs linear multiplication of two polynomials. i.e each term in polynomial2 is multiplied with polynomial1 
	 * and the summed result is returned.
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 
	private Polynomial multiplyLinear(Polynomial operand1, Polynomial operand2) throws Exception {
		
		raiseExceptionOnInvalidOperands(operand1, operand2);

		Polynomial result = new Polynomial(operand1.getBase());

		for(Term operand1Term : operand1) {
			
			result = add(result, multiply(operand2, operand1Term));
		}
		
		return result;
	}*/
	
	/**
	 * Performs DAC multiplication of two polynomials.
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	private Polynomial multiplyDAC(Polynomial operand1, Polynomial operand2) throws Exception {
		
		raiseExceptionOnInvalidOperands(operand1, operand2);

		Polynomial result = null;
		
		if(operand1.isZero() || operand2.isZero()) {
			
			return new Polynomial(operand1.getBase(), 0L);
		}
		
		if(operand1.getLast().getPower() == 0) {
			
			return multiply(operand2, operand1.getLast());
		}

		if(operand2.getLast().getPower() == 0) {
			
			return multiply(operand1, operand2.getLast());
		}
		
		long totalTermsInOperand1 = operand1.getLast().getPower() + 1;
		long totalTermsInOperand2 = operand1.getLast().getPower() + 1;
		
		long middlePower = Math.min(totalTermsInOperand1, totalTermsInOperand2) / 2 - 1;

		Polynomial[][] polynomials = new Polynomial[2][];
		
		polynomials[0] = operand1.split(middlePower);
		polynomials[1] = operand2.split(middlePower);
		
		Polynomial polynomial1 = multiplyDAC(polynomials[0][1], polynomials[1][1]);
		Polynomial polynomial2 = multiplyDAC(add(polynomials[0][1], polynomials[0][0]), add(polynomials[1][1], polynomials[1][0]));
		Polynomial polynomial3 = multiplyDAC(polynomials[0][0], polynomials[1][0]);
		
		result = multiply(polynomial1, new Term(1, (middlePower + 1) * 2));

		result = add(result, multiply( subtract(polynomial2, add(polynomial1, polynomial3)), new Term(1, (middlePower + 1))));
		result = add(result, polynomial3);

		
		return result;
	}
	
	/**
	 * multiplies two polynomials.
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public Polynomial multiply(Polynomial operand1, Polynomial operand2) throws Exception {
		
		return multiplyDAC(operand1, operand2);
	}
	
	/**
	 * Gets the middle element of a given polynomial. Used in Division logic.
	 * 
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	
	public Polynomial getMiddleElement(Polynomial start, Polynomial end) throws Exception {
		
		raiseExceptionOnInvalidOperands(start, end);
		
		Polynomial buffer = add(start, end);
		Term remainder = new Term(0, 0);
		Polynomial result = new Polynomial(start.getBase());
		Term temp;
		ListIterator<Term> polynomialIterator = buffer.reverseIterator();
		Term term = Polynomial.previous(polynomialIterator);
		
		while(term != null) {// || remainder.isValid()) {
		
			if (!remainder.isZero()) {
			
				if (term != null  && remainder.getPower() == term.getPower()) {
					
					term.setCoefficient(term.getCoefficient() + remainder.getCoefficient());
					
					
					remainder.setCoefficient(term.getPower() > 0 ? term.getCoefficient() % 2 * result.getBase() : term.getCoefficient() % 2);
					remainder.setPower(remainder.getPower() - 1);

					term.setCoefficient(term.getCoefficient() / 2);
					result.addFirst(term);
					term = Polynomial.previous(polynomialIterator);
				} else {
					
					temp = remainder.clone();
					temp.setCoefficient(temp.getCoefficient() / 2);
					
					result.addFirst(temp);
					
					remainder.setCoefficient(remainder.getPower() > 0 ? 
							remainder.getCoefficient() % 2 * result.getBase() : remainder.getCoefficient() % 2);
					
					remainder.setPower(remainder.getPower() - 1);
				}
			} else {
		
				if (term.getCoefficient() < 2 && term.getPower() > 0) {
				
					term.setCoefficient(term.getCoefficient() * result.getBase());
					term.setPower(term.getPower() - 1);
					
					remainder.setCoefficient(term.getCoefficient() % 2);
					remainder.setPower(term.getPower());
		
				}	else {
				
					remainder.setCoefficient(term.getPower() > 0 ? 
							term.getCoefficient() % 2 * result.getBase() : term.getCoefficient() % 2);
					
					remainder.setPower(term.getPower() - 1);	
				}
			
				term.setCoefficient(term.getCoefficient() / 2);
				result.addFirst(term);
				term = Polynomial.previous(polynomialIterator);
			}
		}
		
		result.fixOverflows();
		
		return result;
	}
	
	/**
	 * Divides two polynomials.
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public Polynomial divide(Polynomial operand1, Polynomial operand2) throws Exception {
		
		long sign, compare;
		Polynomial middle;
		Polynomial start;
		Polynomial end;
		Polynomial one;
		
		if(operand2.isZero()) {
			
			throw new IllegalArgumentException("Divide by Zero");
		}
		sign = 1;
		sign *= operand1.isPositive() ? 1 : -1;
		sign *= operand2.isPositive() ? 1 : -1;
		
		operand1 = operand1.abs();
		operand2 = operand2.abs();
		
		start = new Polynomial(operand1.getBase());
		middle = new Polynomial(operand1.getBase());
		one  = new Polynomial(operand1.getBase(), 1L);
		end = operand1;
		
		while(add(start, one).compareTo(end) < 0) {
			
			middle = getMiddleElement(start, end);

			 compare = multiply(operand2, middle).compareTo(operand1);

			if(compare < 0) {
				
				start = middle;
			} else if(compare > 0) {
				
				end = middle;
			} else {
				
				return multiply(middle, new Term(sign, 0));
			}
		}

		return multiply(start, new Term(sign, 0));
	}
	
	/**
	 * Finds the modulo of two polynomials.
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 * @throws Exception
	 */
	
	public Polynomial mod(Polynomial operand1, Polynomial operand2) throws Exception {
		
		Polynomial result = divide(operand1, operand2);
		
		return subtract(operand1, multiply(result, operand2));
	}
}
