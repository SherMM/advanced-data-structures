import java.util.Queue;
import java.util.Stack;

/**
 * Singleton Class.
 * Provides functions to execute an instruction.
 * Instruction should be tokenized into postfix expression .
 * 
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */

public class Processor {
	
	private static final Processor processor = new Processor();

	/**
	 * Empty private constructor
	 * 
	 */
	private Processor() {
		
		
	}
	
	/**
	 * Gets the singleton instance.
	 * 
	 * 
	 * @return
	 */
	
	public static Processor getInstance() {
		
		return processor;
	}
	
	/**
	 * Evaluates the add operation on the instruction
	 * 
	 * 
	 * @param evaluator
	 * @return
	 * @throws Exception
	 */
	
	public boolean evaluateAddOperation(Stack<Token> evaluator) throws Exception {
		
		Token token2 = evaluator.pop();
		Token token1 = evaluator.pop();
		Operand result = new Operand("result");
		
		if(token1 instanceof Operand && token2 instanceof Operand) {
			
			result.value = BigNumber.add(((Operand) token1).value, ((Operand) token2).value);
			evaluator.push(result);
			return true;
		} else {
			
			evaluator.push(token1);
			evaluator.push(token2);
			return false;
		}
	}
	
	/**
	 * Evaluates the subtract operation on the instruction
	 * 
	 * 
	 * @param evaluator
	 * @return
	 * @throws Exception
	 */
	
	public boolean evaluateSubtractOperation(Stack<Token> evaluator) throws Exception {
		
		Token token2 = evaluator.pop();
		Token token1 = evaluator.pop();
		Operand result = new Operand("result");
		
		if(token1 instanceof Operand && token2 instanceof Operand) {
			
			result.value = BigNumber.subtract(((Operand) token1).value, ((Operand) token2).value);
			evaluator.push(result);
			return true;
		} else {
			
			evaluator.push(token1);
			evaluator.push(token2);
			return false;
		}
	}
	
	/**
	 * Evaluates the multiply operation on the instruction
	 * 
	 * 
	 * @param evaluator
	 * @return
	 * @throws Exception
	 */
	
	
	public boolean evaluateMultiplyOperation(Stack<Token> evaluator) throws Exception {
		
		Token token2 = evaluator.pop();
		Token token1 = evaluator.pop();
		Operand result = new Operand("result");
		
		if(token1 instanceof Operand && token2 instanceof Operand) {
			
			result.value = BigNumber.multiply(((Operand) token1).value, ((Operand) token2).value);
			evaluator.push(result);
			return true;
		} else {
			
			evaluator.push(token1);
			evaluator.push(token2);
			return false;
		}
	}
	
	/**
	 * Evaluates the divide operation on the instruction
	 * 
	 * 
	 * @param evaluator
	 * @return
	 * @throws Exception
	 */
	
	public boolean evaluateDivideOperation(Stack<Token> evaluator) throws Exception {
		
		Token token2 = evaluator.pop();
		Token token1 = evaluator.pop();
		Operand result = new Operand("result");
		
		if(token1 instanceof Operand && token2 instanceof Operand) {
			
			result.value = BigNumber.divide(((Operand) token1).value, ((Operand) token2).value);
			evaluator.push(result);
			return true;
		} else {
			
			evaluator.push(token1);
			evaluator.push(token2);
			return false;
		}
	}
	
	/**
	 * Evaluates the modulo operation on the instruction
	 * 
	 * 
	 * @param evaluator
	 * @return
	 * @throws Exception
	 */
	
	public boolean evaluateModuloOperation(Stack<Token> evaluator) throws Exception {
		
		Token token2 = evaluator.pop();
		Token token1 = evaluator.pop();
		Operand result = new Operand("result");
		
		if(token1 instanceof Operand && token2 instanceof Operand) {
			
			result.value = BigNumber.mod(((Operand) token1).value, ((Operand) token2).value);
			evaluator.push(result);
			return true;
		} else {
			
			evaluator.push(token1);
			evaluator.push(token2);
			return false;
		}
	}
	
	/**
	 * Evaluates the power operation on the instruction
	 * 
	 * 
	 * @param evaluator
	 * @return
	 * @throws Exception
	 */
	
	public boolean evaluatePowerOperation(Stack<Token> evaluator) throws Exception {
		
		Token token2 = evaluator.pop();
		Token token1 = evaluator.pop();
		Operand result = new Operand("result");
		
		if(token1 instanceof Operand && token2 instanceof Operand) {

			result.value = BigNumber.power(((Operand) token1).value, ((Operand) token2).value);
			evaluator.push(result);
			return true;
		} else {
			
			evaluator.push(token1);
			evaluator.push(token2);
			return false;
		}
	}
	
	/**
	 * Evaluates the factorial operation on the instruction
	 * 
	 * 
	 * @param evaluator
	 * @return
	 * @throws Exception
	 */
	
	public boolean evaluateFactorialOperation(Stack<Token> evaluator) throws Exception {
		
		Token token = evaluator.pop();
		Operand result = new Operand("result");

		if(token instanceof Operand) {

			result.value = BigNumber.factorial(((Operand) token).value);
			evaluator.push(result);
			return true;
		} else {
			
			evaluator.push(token);
			return false;
		}
	}
	
	/**
	 * Evaluates the square root operation on the instruction
	 * 
	 * 
	 * @param evaluator
	 * @return
	 * @throws Exception
	 */
	
	public boolean evaluateSquareRootOperation(Stack<Token> evaluator) throws Exception {
		
		Token token = evaluator.pop();
		Operand result = new Operand("result");

		if(token instanceof Operand) {

			result.value = BigNumber.squareRoot(((Operand) token).value);
			evaluator.push(result);
			return true;
		} else {
			
			evaluator.push(token);
			return false;
		}
	}
	
	/**
	 * Evaluates the instruction
	 * 
	 * 
	 * @param evaluator
	 * @return
	 * @throws Exception
	 */
	
	public void evaluate(Stack<Token> evaluator, Operator operator) throws Exception {
					
		switch (operator.value) {
		
			case '+':
				
				if(evaluateAddOperation(evaluator)) return;
				break;
				
			case '-':
				
				if(evaluateSubtractOperation(evaluator)) return;
				break;
			
			case '*':
				
				if(evaluateMultiplyOperation(evaluator)) return;
				break;
				
			case '/':
				
				if(evaluateDivideOperation(evaluator))  return;
				break;
			
			case '%':
				
				if(evaluateModuloOperation(evaluator)) return;
				break;
			
			case '^':

				if(evaluatePowerOperation(evaluator)) return;	
				break;
				
			case '!':				
				
				if(evaluateFactorialOperation(evaluator)) return;
				break;

			case '~':
					
				if(evaluateSquareRootOperation(evaluator)) return;		
				break;							
			
			default:
				break;
			
		}
		

		evaluator.push(operator);
		return;
	}
	
	
	/**
	 * Executes an instruction
	 * 
	 * 
	 * @param evaluator
	 * @return
	 * @throws Exception
	 */
	
    public BigNumber execute(Queue<Token> postfixExpression) throws Exception  {

    	Stack<Token> evaluator = new Stack<Token>();
    	
    	for(Token token : postfixExpression) {
 
    		if(token instanceof Operand) {

    			evaluator.push(token);
    			
    		} else if(token instanceof Operator) {
    			
    			evaluate(evaluator, (Operator) token);
    		}
    	}
    	
    	return ((Operand) evaluator.pop()).value;
	}
}
