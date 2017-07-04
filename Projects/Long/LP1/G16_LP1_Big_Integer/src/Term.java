/**
 * 
 * Represents a term of the polynomial.
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */

public class Term implements Comparable<Term>, Cloneable {

	private long coefficient;
	private long power;	
	
	/**
	 * 
	 * Constructor 
	 * 
	 * @param coefficient
	 * @param power
	 */
	public Term(long coefficient, long power) {

		this.coefficient = coefficient;
		this.power = power;
	}

	
	/**
	 * Constructor to copy from another term.
	 * 
	 * 
	 * @param polynomial
	 */
	private Term(Term polynomial) {

		this.coefficient = polynomial.getCoefficient();
		this.power = polynomial.getPower();
	}
	
	/**
	 * 
	 * Getter for coefficient
	 * @return
	 */
	public long getCoefficient() {
		
		return coefficient;
	}

	/**
	 * 
	 * Setter for coefficient
	 * @return
	 */

	public void setCoefficient(long coefficient) {
		
		this.coefficient = coefficient;
	}

	/**
	 * 
	 * Getter for power
	 * @return
	 */

	public long getPower() {
		
		return power;
	}


	/**
	 * 
	 * Setter for power
	 * @return
	 */

	public void setPower(long power) {
		
		this.power = power;
	}


	/**
	 * String representation of the term.
	 * 
	 */
	
	@Override
	public String toString() {
		
		return " (" + coefficient + ", " + power + ") ";
	}
	
	/**
	 * Adds this term to another term and returns the result.
	 * 
	 * @param term
	 * @return
	 */

	public Term addTo(Term term) {
		
		return new Term(this.getCoefficient() + term.getCoefficient(),
				this.getPower());
	}

	/**
	 * Subtracts this term by another term and returns the result.
	 * 
	 * @param term
	 * @return
	 */
	public Term subtractTo(Term term) {
		
		return new Term(this.getCoefficient() - term.getCoefficient(),
				this.getPower());
	}
	
	/**
	 * Multiplies this term by another term and returns the result.
	 * 
	 * @param term
	 * @return
	 */
	
	public Term multipliedBy(Term term) {
		
		return new Term(this.getCoefficient() * term.getCoefficient(),
				this.getPower() + term.getPower());
	}

	/**
	 * Compares this term with another term.
	 * 
	 */
	
	@Override
	public int compareTo(Term o) {

		if (this.getPower() > o.getPower()) {
			
			return this.getCoefficient() > 0 ? 1 : -1;
		} else if(this.getPower() < o.getPower()) {
			
			return o.getCoefficient() > 0 ? -1 : 1;
		}
		
		return Long.compare(this.getCoefficient(), o.getCoefficient());
	}
	
	/**
	 * Checks if the current term's coefficient is zero.
	 * 
	 * @return
	 */
	
	public boolean isZero() {
		
		return this.getCoefficient() == 0;
	}

	
	/**
	 * 
	 * Checks if the current term has valid values.
	 * @return
	 */
	
	public boolean isValid() {
		
		return this.getPower() >= 0 && this.getCoefficient() != 0;
	}
	
	/**
	 * 
	 * Creates a new clone of this term.
	 */
	
	public Term clone() {
		
		return new Term(this);
		
	}
}
