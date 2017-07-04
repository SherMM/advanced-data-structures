/**
 * 
 * Stores the Operand data
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */
public class Operand implements Token, Comparable<Operand> {

	public String name;
	public BigNumber value;
	
	/**
	 * Constructor for creating a new operand.
	 * 
	 * 
	 * @param name
	 * @throws Exception
	 */
	
	public Operand(String name) throws Exception {
		
		this.name = name;
		this.value = new BigNumber();
	}
	
	/**
	 * Sets the value of the operand
	 * 
	 * 
	 * @param value
	 */
	
	public void setValue(BigNumber value) {
		
		this.value = value;
	}
	
	/**
	 * 
	 * String representation of the Operand
	 * 
	 */

	@Override
	public String toString() {
		
		return "" + name + "{" + value + "}";
	}

	/**
	 * Compares the calling Operand with another Operand object.
	 * 
	 */
	@Override
	public int compareTo(Operand o) {

		return this.name.compareTo(o.name);
	}
}
