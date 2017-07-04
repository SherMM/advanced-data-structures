import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Singleton class.
 * Used to get post fix expression of the given Instruction.
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */
public class Tokenizer {

	private HashMap<Character, Operator> operators; 	
	private int counter;

	private static final Tokenizer tokenizer = new Tokenizer();
	
	/**
	 * 
	 * Gets the singleton instance.
	 * 
	 * @return
	 */
	public static Tokenizer getInstance() {
		
		return tokenizer;
	}	

	/**
	 * 
	 * Constructor
	 * 
	 */
	private Tokenizer() {

		counter = 0;
		operators = new HashMap<Character, Operator>();
		operators.put('(', new Operator('(',12345678, false));
		operators.put(')', new Operator(')',12345678, false));
		operators.put('!', new Operator('!',1234567, false));
		operators.put('~', new Operator('~',1234567, false));
		operators.put('^', new Operator('^',123456, true));
		operators.put('*', new Operator('*',12345, false));
		operators.put('/', new Operator('/',12345, false));
		operators.put('%', new Operator('%',12345, false));
		operators.put('+', new Operator('+',1234, false));
		operators.put('-', new Operator('-',1234, false));
	}

	/**
	 * Resets the counter used to name constant variables.
	 * 
	 */
	public void reset() {
		
		counter = 0;
	}
	
	/**
	 * Generates the name for constant variables.
	 * 
	 * @return
	 */
	private String getNextNumberName() {
		
		return "@" + (++counter);
	}

	/**
	 * Checks if the character is an operator.
	 * 
	 * @param character
	 * @return
	 */
	
	private boolean isOperator(char character) {
		
		return this.operators.containsKey(character);
	}

	/**
	 * Checks if the character is an operand.
	 * 
	 * @param character
	 * @return
	 */
	
	private boolean isOperand(char character) {
		
		return character >= 'a' && character <= 'z';
	}
	
	/**
	 * Checks if the character is a number.
	 * 
	 * @param character
	 * @return
	 */
	
    private boolean isNumber(char character) {
		
		return character >= '0' && character <= '9';
	}
	
    /**
     * 
     * Processor the operator type character at index i of given string instruction
     * 
     * @param instruction
     * @param i
     * @param operatorStack
     * @param outputQueue
     * @return
     */
    
    private int processOperator(String instruction, int i, Stack<Operator> operatorStack , Queue<Token> outputQueue) {
    	
		Operator currentOperator = this.operators.get(instruction.charAt(i));

		while(!operatorStack.isEmpty() && operatorStack.peek().value != '(' && operatorStack.peek().value != ')' 
			&& ((currentOperator.associativity == false && currentOperator.precedence <= operatorStack.peek().precedence) ||
				 (currentOperator.associativity == true && currentOperator.precedence < operatorStack.peek().precedence))) {
		
			outputQueue.add(operatorStack.pop());
		}
		
		operatorStack.add(currentOperator);

		return i + 1;
    }
    
    /**
     * 
     * Processor the operand type character at index i of a given string instruction
     * 
     * @param instruction
     * @param i
     * @param operatorStack
     * @param outputQueue
     * @return
     */
    
    private int processOperand(String instruction, int i, Queue<Token> outputQueue) throws Exception {
    	
		Operand operand;
		
	    StringBuilder name = new StringBuilder();
		
		name.ensureCapacity(instruction.length() - i + 1);
		
		while( i < instruction.length() && isOperand(instruction.charAt(i))) {
			
			name.append(instruction.charAt(i));
			i += 1;
		}

		operand  = new Operand(name.toString());
		outputQueue.add(operand);
		
		return i;
    }
    
    /**
     * 
     * Processor the number type character at index i of a string instruction.
     * 
     * @param instruction
     * @param i
     * @param operatorStack
     * @param outputQueue
     * @return
     */
    
    private int processNumber(String instruction, int i, Queue<Token> outputQueue) throws Exception {
    	
		Operand operand = new Operand(this.getNextNumberName());
		StringBuilder number = new StringBuilder();
		
		number.ensureCapacity(instruction.length() - i + 1);
		
		while( i < instruction.length() && isNumber(instruction.charAt(i))) {
			
			number.append(instruction.charAt(i));
			i += 1;
		}
		
		operand.setValue(new BigNumber(number.toString()));
		outputQueue.add(operand);
		
		return i;
    }
    
    /**
     * Processes the subexpression of the current expression.
     * 
     * 
     * @param operatorStack
     * @param outputQueue
     */
    private void processAtom(Stack<Operator> operatorStack, Queue<Token> outputQueue) {
    	
		Operator operator = operatorStack.pop();
		
		while(operator.value != '(') {
			
			outputQueue.add(operator);
			operator = operatorStack.pop();
		}
    }
    
    /**
     * Uses Shunting Yard Algorithm to get the postfix format for a given string instruction.
     * 
     * 
     * @param instruction
     * @return
     * @throws Exception
     */
	public Queue<Token> getPostfixNotation(String instruction) throws Exception {
		
		Queue<Token> outputQueue = new LinkedList<Token>();
		Stack<Operator> operatorStack = new Stack<Operator>();
		
		int i=0;
		
		reset();
		
		while (i<instruction.length()) {

			if(instruction.charAt(i) == ' ') {
			
				i += 1;
				continue;
			}
			
			if(instruction.charAt(i) == '(') {
				
				operatorStack.add(this.operators.get(instruction.charAt(i)));
				i += 1;
				
			} else if(instruction.charAt(i) == ')') {

				processAtom(operatorStack, outputQueue);
				i += 1;
				
			} else if(isOperator(instruction.charAt(i))) {
				
				i = processOperator(instruction, i, operatorStack, outputQueue);
				
			} else if(isOperand(instruction.charAt(i))) {
				
				i = processOperand(instruction, i, outputQueue);
				
			} else if(isNumber(instruction.charAt(i))) {
		
				i = processNumber(instruction, i, outputQueue);
			}
		}
		
		while(!operatorStack.isEmpty()) {
			
			outputQueue.add(operatorStack.pop());
		}
		
		return outputQueue;
	}
}
