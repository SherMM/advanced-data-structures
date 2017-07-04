
/**
 * Stores the Operator data.
 * 
 * 
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */

public class Operator implements Token {
		
		char value;
		int precedence;
		boolean associativity;

		/**
		 * Constructor for creating a new Operator.
		 * 
		 * @param value
		 * @param precedence
		 * @param associativity
		 */
		
		public Operator(char value, int precedence, boolean associativity) {

			this.value = value;
			this.precedence = precedence;
			this.associativity = associativity;
		}

		/**
		 * finds the hashcode for the Operator
		 * 
		 * 
		 */
		@Override
		public int hashCode() {

			return Character.getNumericValue(value);
		}

		
		/**
		 * Checks if the two operators are equal
		 * 
		 * 
		 */
		
		@Override
		public boolean equals(Object obj) {
			
			if (this == obj) {
			
				return true;
			}
			
			if (obj == null) {
		
				return false;
			}
			
			if (getClass() != obj.getClass()) {
		
				return false;
			}
		
			Operator other = (Operator) obj;
			
			if (value != other.value) {
			
				return false;
			}
			
			return true;
		}

		/**
		 *	Returns the string representation of the object.
		 * 
		 * 
		 */
		@Override
		public String toString() {
			return "" + value;
		}
	}