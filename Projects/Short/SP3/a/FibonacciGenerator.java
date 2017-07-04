import java.text.DecimalFormat;
import java.text.NumberFormat;

/***
 * 
 * Implements both the Linear as well as Logarithmic version of Fibonacci Series generation algorithms.
 * 
 * Note: Input should be given as command line argument as follows:
 * 
 * For running linear version, the command line arguments should be:
 * 
 * Normal n p
 * 
 * For running log version, the command line arguments should be:
 * 
 * Fast n p
 * 
 * Also starting sequence of numbers are f(1) = 0 and f(2) = 1;
 * 
 * Sample Input:
 * 
 * Normal 1000 999953
 * 
 * Sample output:
 * 
 * 666971
 * Execution Time : 0.000182063 Seconds
 * 
 * @author G16
 *
 */

public class FibonacciGenerator {

	/**
	 * Linear version of Fibonacci Series
	 * 
	 * 
	 * @param n
	 * @param p
	 * @return
	 * @throws Exception
	 */
	public static long linearFibonacci(long n, long p) throws Exception {
		
		long i, iMinusOnethTerm, iMinusTwothTerm, ithTerm = 0;
		
		if (n < 1) {
			
			throw new Exception("Invalid input");
		}
		
		if(n == 1) {
			
			return 0; 
			
		} else if (n == 2) {
			
			return 1;
		}
		
		i=3;
		iMinusOnethTerm = 0;
		iMinusTwothTerm = 1;

		while (i <= n) {
			
			ithTerm = (iMinusOnethTerm + iMinusTwothTerm) % p;
			i += 1;
			iMinusTwothTerm = iMinusOnethTerm;
			iMinusOnethTerm = ithTerm;
		}
		
		return ithTerm;
	}
	
	/**
	 * Multiplies two 2x2 matrices.
	 * 
	 * Used in Logarithmic Version.
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @param p
	 * @return
	 */
	
	private static Long[][] multiply2X2(Long[][] operand1, Long[][] operand2, long p) {
		
		Long[][] result = new Long[2][2];
		
		result[0][0] = ((operand1[0][0]*operand2[0][0]) % p + (operand1[0][1]*operand2[1][0]) % p) % p;
		result[0][1] = ((operand1[0][0]*operand2[0][1]) % p + (operand1[0][1]*operand2[1][1]) % p) % p;
		result[1][0] = ((operand1[1][0]*operand2[0][0]) % p + (operand1[1][1]*operand2[1][0]) % p) % p;
		result[1][1] = ((operand1[1][0]*operand2[0][1]) % p + (operand1[1][1]*operand2[1][1]) % p) % p;
		
		return result;
	}
	
	/**
	 * Find the nth power of  2x2 matrix [[1, 1], [1, 0]];
	 * 
	 * Used in Logarithmic Version.
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @param p
	 * @return
	 */
	
	private static Long[][] powerMatrix(long n, long p) {
		
		Long[][] basicMatrix = {{1L, 1L},{1L, 0L}};
		Long[][] resultMatrix;
		
		if (n == 1) {
			
			return basicMatrix;
		} 
		
		resultMatrix = powerMatrix(n / 2, p);
		
		resultMatrix = multiply2X2(resultMatrix, resultMatrix, p);
		
		if (n % 2 == 1) {
			
			resultMatrix = multiply2X2(resultMatrix, basicMatrix, p);
		}
		
		return resultMatrix;
		
	}
	
	
	/**
	 * Logarithmic Version of Fibonacci Series
	 * 
	 * 
	 * @param operand1
	 * @param operand2
	 * @param p
	 * @return
	 */
	
	public static long logFibonacci(long n, long p) throws Exception {
		
		if (n < 1) {
			
			throw new Exception("Invalid input");
		}
		
		if(n == 1) {
			
			return 0; 
			
		} else if (n <= 3) {
			
			return 1;
		} 
		
		return powerMatrix(n - 3, p)[0][0];
	}
	
	/**
	 * Driver function
	 * 
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		long n, p;
		NumberFormat formatter = new DecimalFormat("###.##########");
		
		try {
			
			if(args.length != 3) {
				
				throw new Exception("Invalid option(s) specified!!!");
			}
			
			n = Long.parseLong(args[1]);
			p = Long.parseLong(args[2]);

			double startTime = System.nanoTime();

			if (args[0].equals("Normal")) {
			
				System.out.println(linearFibonacci(n, p));
			} else {
				
				System.out.println(logFibonacci(n, p));
			}

			System.out.println("Execution Time : " + formatter.format((System.nanoTime() - startTime) / 1000000000) + " Seconds");
			
		} catch (Exception e) {
			
			System.out.println(e.toString());
		}
		
	}

}
