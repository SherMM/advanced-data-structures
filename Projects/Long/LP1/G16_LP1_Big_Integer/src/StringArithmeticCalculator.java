/**
 * 
 * Singleton class.
 * Provides arithmetic operation on number in string object.
 * 
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */

public class StringArithmeticCalculator {

	private StringBuilder[] powerStorage; //used to store power values for enhancement in power calculations.

	private static StringArithmeticCalculator stringArithmeticCalculator = new StringArithmeticCalculator();

	/**
	 * Constructor
	 * 
	 */
	
	private StringArithmeticCalculator() {
	
		powerStorage = new StringBuilder[5000];
	}
	
	/**
	 * 
	 * Gets the instance of the class
	 * @return
	 */
	
	public static StringArithmeticCalculator getInstance() {

		return stringArithmeticCalculator;
	}

	/**
	 * Checks if the given number in string is negative or not.
	 * 
	 * @param operand
	 * @return
	 */

	public boolean isNegative(StringBuilder operand) {

		return operand.length() > 0 && !Character.isDigit(operand.charAt(operand.length() - 1));
	}
	
	/**
	 * Flips the sign of the given string number.
	 * 
	 * @param operand
	 * @return
	 */

	public StringBuilder flipSign(StringBuilder operand) {

		StringBuilder result = new StringBuilder(operand);

		if (isNegative(result)) {

			result.deleteCharAt(operand.length() - 1);
		} else {

			result.append("-");
		}

		return result;
	}
	
	/**
	 * Gets the absolute value of the string.
	 * 
	 * 
	 * @param operand
	 * @return
	 */

	public StringBuilder abs(StringBuilder operand) {

		StringBuilder result = new StringBuilder(operand);

		if (isNegative(result)) {

			result.deleteCharAt(operand.length() - 1);
		}

		return result;
	}
	
	/**
	 * Compares two string numbers
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 */

	public int compare(StringBuilder operand1, StringBuilder operand2) {

		if (isNegative(operand1) && !isNegative(operand2)) {

			return -1;
		}

		if (!isNegative(operand1) && isNegative(operand2)) {

			return 1;
		}

		if (operand1.length() > operand2.length()) {

			return 1 * (isNegative(operand1) ? -1 : 1);
		}

		if (operand1.length() < operand2.length()) {

			return -1 * (isNegative(operand1) ? -1 : 1);
		}

		int i = operand1.length() - 1 - (isNegative(operand1) ? 1 : 0);

		while (i >= 0) {

			if (operand1.charAt(i) > operand2.charAt(i)) {

				return 1 * (isNegative(operand1) ? -1 : 1);
			} else if (operand1.charAt(i) < operand2.charAt(i)) {

				return -1 * (isNegative(operand1) ? -1 : 1);
			}

			i--;
		}

		return 0;
	}

	
	/**
	 * Adds two string numbers
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 */
	public StringBuilder add(StringBuilder operand1, StringBuilder operand2) {

		if (isNegative(operand1) && !isNegative(operand2)) {

			return subtract(operand2, abs(operand1));
		} else if (!isNegative(operand1) && isNegative(operand2)) {

			return subtract(operand1, abs(operand2));
		}

		int i = 0;
		int j = 0;
		int sum = 0, carry = 0;
		int value1 = 0, value2 = 0;
		boolean negativeSign = isNegative(operand1);

		StringBuilder operand1Absolute = abs(operand1);
		StringBuilder operand2Absolute = abs(operand2);

		StringBuilder result = new StringBuilder();

		while (i < operand1Absolute.length() || j < operand2Absolute.length()) {

			value1 = i < operand1Absolute.length() ? operand1Absolute.charAt(i) - '0' : 0;
			value2 = j < operand2Absolute.length() ? operand2Absolute.charAt(j) - '0' : 0;

			sum = (carry + value1 + value2);
			carry = sum / 10;
			sum = sum % 10;
			result.append(sum);

			i++;
			j++;
		}

		if (carry > 0) {

			result.append(carry);
		}

		if (negativeSign) {

			result.append("-");
		}

		return result;
	}

	/**
	 * Subtracts two string numbers.
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 */
	
	public StringBuilder subtract(StringBuilder operand1, StringBuilder operand2) {

		if (isNegative(operand1) && !isNegative(operand2)) {

			return add(operand1, flipSign(operand2));
		} else if (!isNegative(operand1) && isNegative(operand2)) {

			return add(operand1, operand2);
		}

		int i = 0;
		int j = 0;
		int sum = 0, borrow = 0;
		int value1 = 0, value2 = 0;
		boolean negativeSign = false;

		StringBuilder operand1Absolute = abs(operand1);
		StringBuilder operand2Absolute = abs(operand2);
		StringBuilder result = new StringBuilder();

		if (compare(operand1Absolute, operand2Absolute) > 0) {

			negativeSign = isNegative(operand1);
		} else if (compare(operand1Absolute, operand2Absolute) < 0) {

			negativeSign = isNegative(flipSign(operand2));
		}

		if (compare(operand1Absolute, operand2Absolute) < 0) {

			StringBuilder temp = operand1Absolute;
			operand1Absolute = operand2Absolute;
			operand2Absolute = temp;
		}

		while (i < operand1Absolute.length() || j < operand2Absolute.length()) {

			value1 = i < operand1Absolute.length() ? operand1Absolute.charAt(i) - '0' : 0;
			value2 = j < operand2Absolute.length() ? operand2Absolute.charAt(j) - '0' : 0;

			if (borrow == 1) {

				value1 -= 1;
			}

			sum = value1 - value2;

			if (sum < 0) {

				sum = sum + 10;
				borrow = 1;
			} else {

				borrow = 0;
			}

			result.append(sum);

			i++;
			j++;
		}

		if (negativeSign) {

			result.append("-");
		}

		return result;
	}

	/**
	 * Multiplies a string number by an integer digit.
	 * 
	 * 
	 * @param operand
	 * @param digit
	 * @param result
	 */
	private void multiply(StringBuilder operand, long digit, StringBuilder result) {

		long product = 1;
		long carry = 0;

		for (int i = 0; i < operand.length(); i++) {

			product = carry + (operand.charAt(i) - '0') * digit;
			carry = product / 10;
			product = product % 10;
			result.append(product);
		}

		if (carry > 0) {

			result.append(carry);
		}
		return;
	}
	
	/**
	 * Multiplies two string numbers.
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @return
	 */
	

	public StringBuilder multiply(StringBuilder operand1, StringBuilder operand2) {

		StringBuilder result = new StringBuilder();
		StringBuilder buffer = new StringBuilder();
		StringBuilder space = new StringBuilder();

		boolean negativeSign = isNegative(operand1) || isNegative(operand2);

		StringBuilder operand1Absolute = abs(operand1);
		StringBuilder operand2Absolute = abs(operand2);

		for (int i = 0; i < operand1Absolute.length(); i++) {

			multiply(operand2Absolute, operand1Absolute.charAt(i) - '0', buffer);

			result = add(result, buffer);
			space.append('0');
			buffer.setLength(0);
			buffer.append(space);
		}

		if (negativeSign) {

			result.append("-");
		}

		return result;
	}

	/**
	 * Multiplies a given string number by integer number.
	 * 
	 * 
	 * @param operand
	 * @param number
	 * @return
	 */
	public StringBuilder multiply(StringBuilder operand, long number) {

		StringBuilder result = new StringBuilder();
		StringBuilder buffer = new StringBuilder();
		StringBuilder space = new StringBuilder();

		boolean negativeSign = isNegative(operand) || (number < 0);

		StringBuilder operandAbsolute = abs(operand);
		number = Math.abs(number);

		while (number > 0) {

			multiply(operandAbsolute, number % 10, buffer);
			result = add(result, buffer);

			space.append('0');
			buffer.setLength(0);
			buffer.append(space);

			number = number / 10;
		}

		if (negativeSign) {

			result.append("-");
		}

		return result;
	}

	
	/**
	 * 
	 * Gets the nth power of a number as string.
	 * 
	 * @param number
	 * @param power
	 * @return
	 */
	public StringBuilder power(long number, long power) {

		StringBuilder result;

		if (power < powerStorage.length && powerStorage[(int) power] != null) {

			return powerStorage[(int) power];
		}

		if (power == 0) {

			result = new StringBuilder("1");
		} else if (power == 1) {

			result = new StringBuilder(Long.toString(number)).reverse();
		} else {

			result = power(number, power / 2);

			result = multiply(result, result);

			if (power % 2 == 1) {

				result = multiply(result, number);
			}
		}

		if (power < powerStorage.length) {

			powerStorage[(int) power] = result;
		}

		return result;
	}

	
	/**
	 * Divides a number in Base b and returns the remainder while storing the string quotient passed in function call
	 * 
	 * 
	 * @param number
	 * @param base
	 * @param quotient
	 * @return
	 */
	public long divide(StringBuilder number, long base, StringBuilder quotient) {

		long nominator = 0;

		int i = 0;

		while (nominator < base && i < number.length()) {

			nominator = nominator * 10 + (number.charAt(i) - '0');

			i += 1;
		}

		do {

			if (i < number.length() && nominator < base) {

				nominator = nominator * 10 + (number.charAt(i) - '0');

				i += 1;
			}

			quotient.append(nominator / base);

			nominator = nominator % base;

		} while (i < number.length());

		return nominator;
	}
}
