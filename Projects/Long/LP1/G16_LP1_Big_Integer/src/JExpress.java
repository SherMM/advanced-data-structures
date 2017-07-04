import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 
 * This class stores the instructions given as a string in the Instruction Object and executes them.
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */

public class JExpress {

	private Tokenizer tokenizer;
	private Processor processor;
	private Memory memory;
    
	/**
	 * Constructor for creating a new JExpress Application.
	 * 
	 */
	
	public JExpress() {
		
		memory = new Memory();
		tokenizer = Tokenizer.getInstance();
		processor = Processor.getInstance();
	}
	
	/**
	 * Updates all the variables values by reading the latest values stored in Memory (Memory.java)
	 * 
	 * 
	 * 
	 * @param postfixExpression
	 */
	
	public void getAllMemoryValues(Queue<Token> postfixExpression) {
		
		Token token;
		int size = postfixExpression.size();
		
		while(size-- > 0) {
			
			token = postfixExpression.poll();
			
			if(token instanceof Operand) {
				
				Operand operand = (Operand) token;
				
				if(!operand.name.startsWith("@")) {

					operand.value = memory.readData(operand.name);
				}
				
				postfixExpression.add(operand);
			} else {
				
				postfixExpression.add(token);
			}
		}
	}
	
	/**
	 * Creates a new instruction from command string and stores the same in memory.
	 * 
	 * 
	 * 
	 * @param command
	 */
	
	public void addInstruction(String command) {
		
		memory.writeInstruction(new Instruction(command));
	}
	
	/**
	 * Run the list of instructions stored in memory.
	 * 
	 * 
	 * 
	 * @throws Exception
	 */
	
	
	public void run() throws Exception {

		String[] commands;
		String expression;
		Pattern pattern;
		Matcher matcher;
	    Instruction  currentInstruction = memory.readInstructionAtIndex(0);
	    
	    while(currentInstruction != null) {

			switch (currentInstruction.getType()) {
			
				case ASSIGNMENT:
				
					commands = currentInstruction.expression.split("=");
					String operand = commands[0];
					expression = commands[1];
					Queue<Token> postfixExpression = tokenizer.getPostfixNotation(expression);
					getAllMemoryValues(postfixExpression);
					memory.writeData(operand, processor.execute(postfixExpression));
					currentInstruction = memory.readInstructionAtIndex(currentInstruction.index + 1);
					break;
		
				case CONDITIONAL_LOOP:
					pattern = Pattern.compile("^([a-z]+)\\?([0-9]+)(:([0-9]+)|\\s*)$");
					matcher = pattern.matcher(currentInstruction.expression);
					int nonzeroline, zeroline = -1;
					
					String variable;
					
					
					if(matcher.find()) {
	
						variable = matcher.group(1);
						nonzeroline = Integer.parseInt(matcher.group(2));

						//for(int i=0; i<matcher.groupCount(); i++) {
						
							//System.out.println(matcher.groupCount() + " " + matcher.group(i));
						//}
						if(!memory.readData(variable).isZero()) {
							
							currentInstruction = memory.readInstructionAtLineNo(nonzeroline);
						} else if(matcher.group(3).contains(":")) {

							zeroline = Integer.parseInt(matcher.group(3).replace(":", ""));
							currentInstruction = memory.readInstructionAtLineNo(zeroline);
						} else {
							
							currentInstruction = memory.readInstructionAtIndex(currentInstruction.index + 1);
						}
						
					}
					break;
					
				case PRINTLIST:
					
					expression = currentInstruction.expression.substring(0, currentInstruction.expression.length() - 1); 
					memory.readData(expression).printList();
					currentInstruction = memory.readInstructionAtIndex(currentInstruction.index + 1);
					break;
				default:
					System.out.println(memory.readData(currentInstruction.expression).toString());
					currentInstruction = memory.readInstructionAtIndex(currentInstruction.index + 1);
					break;
			}
	    }
	}
}
