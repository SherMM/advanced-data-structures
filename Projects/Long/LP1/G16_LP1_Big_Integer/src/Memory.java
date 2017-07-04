import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


/**
 * This class is used to store the data and instruction of a program (JExpress.java)
 * 
 * 
 * 
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */
public class Memory {

	private TreeMap<String, BigNumber> dataMemory;
	private ArrayList<Instruction> instructionMemory;	
	private HashMap<Integer, Integer> lineNoInstrLookup;
	
	
	/**
	 * Constructor for creating a new memory object.
	 * 
	 */
	
	public Memory() {

		dataMemory = new TreeMap<String, BigNumber>();
		instructionMemory = new ArrayList<Instruction>();
		lineNoInstrLookup = new HashMap<Integer, Integer>();
	}
	
	/**
	 * Writes data to a specific location.
	 * 
	 * 
	 * @param location
	 * @param value
	 */
	
	public void writeData(String location, BigNumber value) {
		
		dataMemory.put(location, value);
	}
	
	/**
	 * reads data from a specific location.
	 * 
	 * 
	 * @param location
	 * @return
	 */
	
	public BigNumber readData(String location) {
		
		return dataMemory.containsKey(location) ? dataMemory.get(location) : null;
	}
	
	/**
	 * 
	 * writes instruction to the memory.
	 * 
	 * @param instruction
	 */
	
	public void writeInstruction(Instruction instruction) {
		
		instruction.index = instructionMemory.size();
		instructionMemory.add(instruction);
		lineNoInstrLookup.put(instruction.lineno, instruction.index);
	}
	
	/**
	 * 
	 *	Reads instruction from the memory at index.
	 * 
	 * @param index
	 * @return
	 */
	
	public Instruction readInstructionAtIndex(int index) {
		
		return index >=0 && index <instructionMemory.size() ? instructionMemory.get(index) : null;
	}
	
	/**
	 * 
	 *	Reads instruction from the memory at lineno.
	 * 
	 * @param index
	 * @return
	 */
	
	public Instruction readInstructionAtLineNo(int lineno) {
		
		return lineNoInstrLookup.containsKey(lineno) ? readInstructionAtIndex(lineNoInstrLookup.get(lineno)) : null;
	}
	
	/**
	 * Clears the memory.
	 * 
	 * 
	 */
	
	public void clear() {
		
		this.dataMemory.clear();
		this.instructionMemory.clear();
		this.lineNoInstrLookup.clear();
	}
}
