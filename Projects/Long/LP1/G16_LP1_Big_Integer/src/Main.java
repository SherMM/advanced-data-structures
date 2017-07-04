import java.util.Scanner;

/**
 * 
 * Driver class
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */
public class Main {

	/**
	 * Driver function
	 * 
	 * 
	 * @param args
	 * @throws Exception
	 */
	
	public static void main(String[] args) throws Exception {
	
		long base = 10;
		
		if(args.length == 0) {
			
			System.out.println("No Base Set..Defaulting to 10..");
		} else {
			
			base = Long.parseLong(args[0]);
		}
		
		BigNumber.setBase(base); //set the base

		JExpress jExpress = new JExpress();
		Scanner scanner = new Scanner(System.in);
		
		while(scanner.hasNextLine()) { //get all the instructions

		    jExpress.addInstruction(scanner.nextLine());
		}
		scanner.close();

		//double startTime = System.nanoTime();
		jExpress.run(); // run the program
		//System.out.println("Execution time : " + (System.nanoTime() - startTime) / 1000000000 + " Seconds");
	}

}
