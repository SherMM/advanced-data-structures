import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Comparator;
import java.util.Random;

/**
 * 
 * Implements Both External and Internal Version of Selection Algorithm.
 * 
 * 
 * 
 * Sample Input:
 * 
 * Input should be in the following format as command line argument:
 * 
 * For running External version, the command line arguments should be:
 * 
 * External n k
 * 
 * For running Internal version, the command line arguments should be:
 * 
 * Internal n k
 * 
 * Sample Output: 
 * 
 * (for n=1000000, k=100, External Version)
 * 
 * Execution Time : 0.940914481 Seconds
 * 
 * 
 * 
 * @author G16
 *
 */

public class Selection {

	
	/**
	 * External Version. Custom class of Binary Heap is used so as to make the algorithm run in single pass.
	 * 
	 * 
	 * @param arr
	 * @param k
	 * @param output
	 */
	public static<T extends Comparable<? super T>> void select(T[] arr, int k, T[] output) {
	
		
		BinaryHeap<T> binaryHeap;
		
		for(int i=0; i<k; i++) {
			
			output[i+1] = arr[i];
		}
		
		binaryHeap=new BinaryHeap<T>(output, new Comparator<T>() {

			@Override
			public int compare(T o1, T o2) {
				// TODO Auto-generated method stub
				return o1.compareTo(o2);
			}
			
		});

		for(int i=k; i<arr.length; i++) {
			
			if(arr[i].compareTo(binaryHeap.min()) > 0) {
				
				binaryHeap.assign(1, arr[i]);
				binaryHeap.percolateDown(1);
			}
		}

		for(int i=1; i<=k; i++) {
			
			output[i] = binaryHeap.getItem(i);
		}
	}
	
	/**
	 * swaps the values of array at indices i and j.
	 * 
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	
	private static<T extends Comparable<? super T>> void exchange(T[] arr, int i, int j) {
		
		T temp  = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	
	/**
	 * partitions the array based on the random pivot.
	 * 
	 * 
	 * @param arr
	 * @param p
	 * @param r
	 * @return
	 */
	
	private static<T extends Comparable<? super T>> int partition(T[] arr, int p, int r) {
		
		int i = new Random().nextInt(r - p + 1) + p;
		T pivot = arr[i];
		
		exchange(arr, i, r);
		
		i=p-1;
		
		for(int j=p; j<r; j++) {
			
			if (arr[j].compareTo(pivot) <= 0) {
				
				i += 1;
				exchange(arr, i, j);
			}
		}
		
		exchange(arr, i+1, r);
		
		return i+1;
	}
	
	/**
	 * Finds the k largest elements of arr[p..r].  Returns index q.
	 * The k largest elements are found in arr[q..r].
	 * 
	 * 
	 * @param arr
	 * @param p
	 * @param r
	 * @param k
	 * @return
	 */
	
	public static<T extends Comparable<? super T>> int select(T[] arr, int p, int r, int k) {
		
		int q = partition(arr, p, r);

		if (r - q >= k) {
			
			return select(arr, q+1, r, k);
		} else if((r - q + 1) == k) {
			
			return q;
		} else {

			return select(arr, p, q-1, k-(r-q+1));
		}
	}
	
	/**
	 * Prints the array from start to end
	 * 
	 * 
	 * @param arr
	 * @param start
	 * @param end
	 */
	
	public static<T extends Comparable<? super T>> void print(T[] arr, int start, int end) {
		
		for(int i=start; i<=end; i++) {
			
			System.out.print(arr[i] + " ");
		}
		
		System.out.println();
		
	}
	
	/**
	 * 
	 * Driver Function
	 * @param args
	 */
	
	public static void main(String[] args) {
	
		Integer[] arr = new Integer[Integer.parseInt(args[1])];
		Random random = new Random();
		double startTime;
		NumberFormat formatter = new DecimalFormat("###.##########");
		int k = Integer.parseInt(args[2]);
		Integer[] output;
		int q;
		
		for(int i=0; i<arr.length; i++) {
			
			arr[i] = new Integer(random.nextInt(Integer.MAX_VALUE));
		}
		
		startTime = System.nanoTime();
		
		if(args[0].equals("Internal")) {
		
			q=Selection.select(arr, 0, arr.length-1, k);
			
			if(args.length > 3 && args[3].equals("print")) {
				
				print(arr, q, arr.length-1);
			}
		} else {
			
			output = new Integer[k+1];
			
			Selection.select(arr, k, output);

			if(args.length > 3 && args[3].equals("print")) {
				
				print(output, 1, output.length-1);
			}
		}
		
		System.out.println("Execution Time : " + formatter.format((System.nanoTime() - startTime) / 1000000000) + " Seconds");
	}
}
