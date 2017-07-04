import java.util.LinkedList;
import java.util.Random;

/**
 * Implements the algorithm to find kth quantile of an unsorted set (represented as array).
 * 
 * 
 * Sample input is an array generated in main function.
 * 
 * Same output:
 * 
 * For eg:
 * 
 * If the input array is:
 * 
 * 32 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1
 * 
 * Sample output: for k = 16,
 * 
 * Kth Quantile is: 2 4 6 8 10 12 14 16 18 20 22 24 26 28 30 
 * 
 * @author G16
 *
 */


public class KthQuantiles {

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
	 * Finds the k smallest elements of arr[p..r].  Returns index q.
	 * The k smallest elements are found in arr[p...q].
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

		if (q - p + 1 > k) {
			
			return select(arr, p, q-1, k);
		} else if((q - p + 1) == k) {
			
			return q;
		} else {

			return select(arr, q + 1, r, k-(q-p+1));
		}
	}
	
	/**
	 * Get the list of kth quantiles.
	 * 
	 * 
	 * 
	 * @param A
	 * @param left
	 * @param right
	 * @param k
	 * @return
	 */
	
	private static<T extends Comparable<? super T>> LinkedList<T> kthQuantiles(T[] A, int left, int right, int k) {

			if (k == 1) {
				
				return new LinkedList<T>();
			}
			
			LinkedList<T> list = new LinkedList<T>();
			int length = (right - left + 1);
			int middle = (k / 2) * (length / k);

			middle = select(A, left, right, middle);

			T result = A[middle];
			
			list.addAll(kthQuantiles(A, left, middle, k / 2));
			
			list.add(result);
	
			list.addAll(kthQuantiles(A, middle + 1, right, k / 2 + k % 2));
			
			return list;
		}
	
	
	/***
	 * Finds the list of kth quantiles and stores the same in the result array passed as parameter.
	 * 
	 * 
	 * @param A
	 * @param result
	 * @param k
	 */
	
	public static<T extends Comparable<? super T>> void kthQuantiles(T[] A, T[] result, int k) {
	
		int i=0;
		
		for(T value : kthQuantiles(A, 0, A.length-1, k)) {
			
			result[i++] = value;
		}
	}
	
	/**
	 * Driver Function
	 * 
	 * 
	 * 
	 * @param args
	 */
	
	
	public static void main(String[] args) {
		
		int k = 8;
		Integer[] A = new Integer[32];
		Integer[] result = new Integer[k-1];

		for(int i=0; i<A.length; i++) {
			
			A[i] = A.length - i;
		}
		
		for(int i=0; i<A.length; i++) {
			
			System.out.print(A[i] + " ");
		}
		
		System.out.println();
		System.out.print("Kth Quantile is: ");
		
		kthQuantiles(A, result, k);
		
		
		for(int i=0; i<result.length; i++) {
			
			System.out.print(result[i] + " ");
		}
		
		System.out.println();
	}
}
