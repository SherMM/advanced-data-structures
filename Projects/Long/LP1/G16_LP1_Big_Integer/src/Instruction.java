import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Stores the details of the instruction to be executed in a Processor(Processor.java).
 * 
 * It stores four types of instruction.
 * 
 * 	1.	ASSIGNMENT (Eg. a = 1 + 2)
 * 	2.  CONDITIONAL_LOOP  (Eg. a?4)
 *  3.  PRINTLIST (Eg. a) )
 *  4.  DISPLAY (Eg a)
 * 
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */

public class Instruction {

	public int index;
	public int lineno;
	public String expression;
	
	
	public  enum InstructionType { ASSIGNMENT, CONDITIONAL_LOOP, PRINTLIST, DISPLAY };
	
	
	/**
	 * Creates a new instruction with the given lineno, expression
	 * 
	 * 
	 * @param lineno
	 * @param expression
	 */
	
	public Instruction(int lineno, String expression) {

		this.index = -1;
		this.lineno = lineno;
		this.expression = expression;
	}

	/**
	 * Creates a new instruction with the given string
	 * 
	 * 
	 * @param lineno
	 * @param expression
	 */
	
	public Instruction(String command) {
		
		Pattern pattern = Pattern.compile("^([0-9]+)(?:\\s*)((.*)$)");
		Matcher matcher = pattern.matcher(command);
		
		if (matcher.find()) {

			this.lineno = Integer.parseInt(matcher.group(1));
			this.expression = matcher.group(2).replace(" ", "");
		}
	}
	
	/***
	 * Returns the string representation of the object.
	 * 
	 * 
	 */
	@Override
	public String toString() {
		return "Instruction [index=" + index + ", lineno=" + lineno + ", expression=" + expression + "]";
	}
	
	/**
	 * Finds the type of instruction stored in the calling object.
	 * 
	 * 
	 * @return
	 */
	
	
	public InstructionType getType() {
		
		if(this.expression.contains("=")) {
			
			return InstructionType.ASSIGNMENT;
		}

		if(this.expression.contains("?")) {
			
			return InstructionType.CONDITIONAL_LOOP;
		}

		if(this.expression.endsWith(")")) {
			
			return InstructionType.PRINTLIST;
		}
		
		return InstructionType.DISPLAY;
		
	}
}
