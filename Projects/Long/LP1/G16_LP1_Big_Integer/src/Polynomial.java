import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;


/**
 * Stores the polynomial representation in Base base  for a given number. Used in Big Number Class.
 * 
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */
public class Polynomial implements Iterable<Term>, Comparable<Polynomial>{

	private long base;
	private LinkedList<Term> terms;
	private static StringArithmeticCalculator stringArithmeticCalculator = StringArithmeticCalculator.getInstance();
	
	/**
	 * 
	 * gets the base of polynomial.
	 * 
	 * @return
	 */
	
	public long getBase() {
		
		return base;
	}

	
	/**
	 * Creates a polynomial of value zero with the given base.
	 * 
	 * 
	 * @param base
	 */
	
	public Polynomial(long base) {
		
		this.base = base;
		this.terms = new LinkedList<Term>();
	}
	
	/**
	 * Creates a polynomial of given number with the given base.
	 * 
	 * @param base
	 * @param number
	 */
	
	public Polynomial(long base, long number) {
		

		this(base);
		long coefficient = 0;
		long power = 0;
		
		number = Math.abs(number);
		
		do {
			
			coefficient = (long) (number % base);
			this.add(new Term(coefficient, power));
			number = number / base;
			power++;
			
		} while(number > 0);
	}
	
	/**
	 * Creates a polynomial of given number string with the given base.
	 * 
	 * @param base
	 * @param number
	 */
	
	public Polynomial(long base, String number) {
		
		this(base);
		long coffecient = 0;
		long power = 0;
		StringBuilder dividend = new StringBuilder(number).reverse();
		StringBuilder quotient;
		long sign = 1;
		
		if(stringArithmeticCalculator.isNegative(dividend)) {
			
			sign = -1;
			dividend = stringArithmeticCalculator.abs(dividend);
		}
		
		dividend.reverse();
		
		do {
			
			quotient = new StringBuilder();
			coffecient = stringArithmeticCalculator.divide(dividend,  base, quotient) * sign;
			
			if (coffecient != 0) {
			
				this.add(new Term(coffecient, power));
			}
			
			dividend = quotient;
			power++;
			
		} while(!(dividend.length() <= 1 && dividend.toString().equals("0")));
	}
	
	/**
	 * Checks if the polynomial is empty.
	 * 
	 * 
	 * @return
	 */
	
	private boolean isEmpty() {
		
		return this.terms.isEmpty();
	}

	/**
	 * Checks if the polynomial is zero.
	 * 
	 * 
	 * @return
	 */
	
	public boolean isZero() {

		return this.isEmpty();
	}
	
	/**
	 * Checks if the polynomial is positive.
	 * 
	 * 
	 * @return
	 */
	
	public boolean isPositive() {
		
		return this.isEmpty() ? false : this.getLast().getCoefficient() >= 0;
	}
	

	/**
	 * Gets the first term of the polynomial
	 * 
	 * @return
	 */
	
	public Term getFirst() {
		
		return this.isEmpty() ? null : this.terms.getFirst();
	}
	
	/**
	 * Gets the last term of the polynomial
	 * 
	 */
	
	public Term getLast() {
		
		return this.isEmpty() ? null : this.terms.getLast();
	}
	
	/**
	 * adds a new term to the polynomial
	 * 
	 * @param term
	 */
	
	public void add(Term term) {
		
		if(term != null && term.isValid()) {
			
			this.terms.add(term);
		}
	}
	
	/**
	 * add a new term at the start of the polynomial.
	 * 
	 * @param term
	 */
	
	public void addFirst(Term term) {
		
		if(term != null && term.isValid()) {
			
			this.terms.addFirst(term);
		}
	}
	/**
	 * returns the absolute value of the polynomial
	 * 
	 * @return
	 */
	public Polynomial abs() {
		
		Polynomial result = new Polynomial(base);
		Term newTerm;
		for(Term term :  this) {
			
			newTerm = term.clone();
			newTerm.setCoefficient(Math.abs(newTerm.getCoefficient()));
			result.add(newTerm);
		}

		
		return result;
	}
	/**
	 * return the iterator for this polynomial
	 * 
	 * 
	 */
	
	@Override
	public Iterator<Term> iterator() {

		return this.terms.iterator();
	}
	
	/**
	 * Returns the reverse iterator for this polynomial
	 * 
	 * 
	 * @return
	 */

	public ListIterator<Term> reverseIterator() {

		return this.terms.listIterator(this.terms.size());
	}
	
	/**
	 * 
	 * Get the next element of polynomial iterator. if not found returns null.
	 * 
	 * @param bigNumberIterator
	 * @return
	 */
	
	public static Term next(Iterator<Term> polynomialIterator) {
		
		return polynomialIterator.hasNext() ? polynomialIterator.next() : null;
	}
	
	/**
	 * 
	 * Get the previous element of polynomial iterator. if not found, returns null.
	 * 
	 * @param bigNumberIterator
	 * @return
	 */	
	
	public static Term previous(ListIterator<Term> polynomialIterator) {
		
		return polynomialIterator.hasPrevious() ? polynomialIterator.previous() : null;
	}
	
	/**
	 * Compresses the same power terms of this polynomial.
	 * 
	 */
	
	public void compress() {
		
		LinkedList<Term> terms =  this.terms;
		
		this.terms = new LinkedList<Term>();

		Iterator<Term> polynomialIterator = terms.iterator();
		Term term = next(polynomialIterator);
		
		long lastProcessedPower = term != null ? term.getPower() : 0;
		long lastProcessedCoefficient = 0;
		
		while (term != null) {
			
			if(lastProcessedPower == term.getPower()) {
				
				lastProcessedCoefficient += term.getCoefficient();
			} else {
				
				this.add(new Term(lastProcessedCoefficient, lastProcessedPower));
				lastProcessedCoefficient = term.getCoefficient();
				lastProcessedPower = term.getPower();
			}
			
			term = next(polynomialIterator);
		}
		
		this.add(new Term(lastProcessedCoefficient, lastProcessedPower));
	}
	
	/**
	 * Fixes the overflow of coefficients of this polynomial.
	 * 
	 * 
	 */
	
	public void fixOverflows() {
		
		LinkedList<Term> terms;

		Iterator<Term> polynomialIterator;
		Term term;
		Term carry;

		compress();
		
		terms = this.terms;
		this.terms = new LinkedList<Term>();
		
		polynomialIterator = terms.iterator();
		term = next(polynomialIterator);
		carry = null;
		
		while(term != null) {
			
			if(carry != null) {
				
				if (term.getPower() == carry.getPower()) {
		
					long coefficient = term.getCoefficient() + carry.getCoefficient();

					carry.setCoefficient(coefficient / base);
					carry.setPower(term.getPower() + 1);
					
					term.setCoefficient(coefficient % base);
					this.add(term);	
					term = next(polynomialIterator);

				} else {

					this.add(carry);
					carry = null;
				}
				
			} else {
			
				if(Math.abs(term.getCoefficient()) >= base) {

					carry = new Term(term.getCoefficient()  / base, term.getPower()+1);
					term.setCoefficient(term.getCoefficient() % base);
				} 
				
				this.add(term);
				term = next(polynomialIterator);
			}
			
		}
		
		
		this.add(carry);
	}
	
	/**
	 * Splits the polynomials the polynomial into two based on the given power.
	 * Used in DAC multiplication (multiplyDAC)
	 * 
	 * @param power
	 * @return
	 */
	
	public Polynomial[] split(long power) {
		
		
		Polynomial[] polynomials = new Polynomial[2];

		polynomials[0] = new Polynomial(base, 0L);
		polynomials[1] = new Polynomial(base, 0L);
		
		for(Term term : this) {
			
			if (term.getPower() <= power) {

				polynomials[0].add(term.clone());
			} else {
				
				polynomials[1].add(term.clone());
			}
		}
		
		for(Term term : polynomials[1]) {
			
			term.setPower(term.getPower() - (power + 1));
		}
		
		
		return polynomials;
	}
	
	/**
	 * Compares this polynomial with another.
	 * 
	 * 
	 */
	
	@Override
	public int compareTo(Polynomial o) {

		ListIterator<Term> iterator1 = this.reverseIterator();
		ListIterator<Term> iterator2 = o.reverseIterator();

		Term p1;
		Term p2;
		
		while (iterator1.hasPrevious() && iterator2.hasPrevious()) {
	
			p1 = iterator1.previous();
			p2 = iterator2.previous();
			
			if(p1.getPower() > p2.getPower()) {
					
				return p1.getCoefficient() > 0? 1 : -1;
			} 
	
			if(p1.getPower() < p2.getPower()) {
				
				return p2.getCoefficient() > 0? -1 : 1;
			} 
			
			if(p1.getCoefficient() != p2.getCoefficient()) {
				
				return Long.compare(p1.getCoefficient(), p2.getCoefficient());
			}
		}
		
		
		if(iterator1.hasPrevious()) {
			
			p1 = iterator1.previous();
			return p1.getCoefficient() > 0 ? 1 : -1;
		}
		

		if(iterator2.hasPrevious()) {
			
			p2 = iterator2.previous();
			return p2.getCoefficient() > 0 ? -1 : 1;
		}
		
		return 0;
	}
	
	
	/**
	 * returns the decimal representation of the polynomial
	 * 
	 * 
	 * @return
	 */
	
	public String toDecimalString() {
		
		String resultString = "";
		StringBuilder result = new StringBuilder();
		StringBuilder buffer;
		
		for(Term term : this) {
			
			buffer = stringArithmeticCalculator.power(base, term.getPower());
			buffer = stringArithmeticCalculator.multiply(buffer, term.getCoefficient());
			result = stringArithmeticCalculator.add(result, buffer);
		}
		
		resultString = result.reverse().toString().replaceFirst("^0+(?!$)", "");
			
		return resultString.isEmpty() ? "0" : resultString;	
	}

	/**
	 * returns the string representation of the polynomial
	 * 
	 * 
	 */
	@Override
	public String toString() {
		
		return base + ": " + this.terms;
	}
}

