import java.util.Random;
import java.util.Arrays;

/**
 * Implements dual partitioning version of quicksort
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class DualQuickSort {

// threshold indicating when to use insertion sort
private static final int THRESH = 11;

/**
 * Calling method for quicksort. Lets user only worry about passing in array
 * to be sorted
 *
 * @param items : array of items of generic type T
 */
public static<T extends Comparable<? super T> > void quickSort(T[] items) {
        quickSort(items, 0, items.length-1);
}

/**
 * Quicksort method to sort an array of generic items. Assumes dual partitioning
 *
 * @param items : array of items of generic type T
 * @param low : lower index to start sorting at
 * @param high : higher index to start sorting at
 */
public static<T extends Comparable<? super T> > void quickSort(T[] items, int low, int high) {
        // handle smaller subarrays with insertion sort
        int n = high - low;
        if (n <= THRESH) {
                insertionSort(items, low, high);
                return;
        }

        // indexes for next round of/recursive call to quicksort
        int[] idxs = partition(items, low, high);
        int i = idxs[0];
        int k = idxs[1];

        // sort the three zones partitioned in the array
        quickSort(items, low, i-1);
        quickSort(items, i+1, k-1);
        quickSort(items, k+1, high);
}

/**
 * Partitions an array using the dual partitioning method into
 * three subarrays: items less than piv1, items between piv1 and piv2,
 * and items greater than piv2
 *
 * Starter pseudo code from http://www.nowherenearithaca.com/2014/02/visualization-of-yaroslavskiys-dual.html
 *
 * @param items : array to be partitioned
 * @param low : bottom index in array
 * @param high : upper index in array
 *
 * @return integer array of pivot indexes
 */
public static<T extends Comparable<? super T> > int[] partition(T[] items, int low, int high) {
  // indexes to return to main quicksort algorithm for
  // further recursive calls
  int[] idxs = new int[2];

  // need to check that piv1 (begging of array) is actuall less than piv2 (end of array)
  if (items[low].compareTo(items[high]) > 0) {
          swap(items, low, high);
  }

  // dual pivots
  T piv1 = items[low];
  T piv2 = items[high];

  // indexes used for comparison throughout algorithm
  int i = low + 1;
  int j = i;
  int k = high - 1;
  while (j <= k) {
          if (items[j].compareTo(piv1) < 0) {
                  swap(items, j, i);
                  i++;
          } else {
                  if (items[j].compareTo(piv2) >= 0) {
                          while (items[k].compareTo(piv2) > 0 && j < k) {
                                  k--;
                          }
                          swap(items, j, k);
                          k--;
                          if (items[j].compareTo(piv1) < 0) {
                                  swap(items, j, i);
                                  i++;
                          }
                  }
          }
          j++;
  }
  i--;
  k++;

  // partitioning done, swap elements
  swap(items, low, i);
  swap(items, high, k);

  // store and return final pivot indexes
  idxs[0] = i;
  idxs[1] = k;
  return idxs;
}


/**
 * Sorts array of items using insertion sort method
 *
 * @param items : arrays of generic items to sort
 * @param low : bottom index in array
 * @param high : upper index in array
 */
public static<T extends Comparable<? super T> > void insertionSort(T[] items, int low, int high) {
        for (int i = low; i <= high; i++) {
                T tmp = items[i];
                int j = i - 1;
                while (j >= low && items[j].compareTo(tmp) > 0) {
                        items[j+1] = items[j];
                        j -= 1;
                }
                items[j+1] = tmp;
        }
}

/**
 * Swaps elements at position i and j in array items
 *
 * @param items : arrays of generic items
 * @param i : an index to swap on
 * @param j : an index to swap on
 */
public static<T extends Comparable<? super T> > void swap(T[] items, int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
}

/**
 * For testing purposes
 */
public static void main(String[] args) {
        int n = 4194304;
        int min = 0;
        int max = 750;
        Random rand = new Random();
        Timer timer = new Timer();

        Integer[] array = new Integer[n];

        for (int i = 0; i < n; i++) {
                array[i] = rand.nextInt(max - min + 1);
        }

        Integer[] copy = Arrays.copyOf(array, array.length);

        System.out.println("Sorting a random array of length " + array.length);
        timer.start();
        quickSort(array);
        timer.end();
        System.out.println(timer);

        Arrays.sort(copy);
        System.out.println("Array is sorted: " + Arrays.equals(copy, array));
        System.out.println();

        // Testing an array of n distinct elements
        for (int i = 0; i < n; i++) {
                array[i] = i;
        }

        // shuffle so it's unsorted to begin with
        Shuffle.shuffle(array, 0, array.length-1);
        copy = Arrays.copyOf(array, array.length);
        System.out.println("Sorting a unique array of length " + array.length);
        timer.start();
        quickSort(array);
        timer.end();
        System.out.println(timer);

        Arrays.sort(copy);
        System.out.println("Array is sorted: " + Arrays.equals(copy, array));
        System.out.println();

        // Testing an array of n elements with duplicates
        int dups = 1000;    // number of duplicates per item
        for (int i = 0; (i < n) && (i + dups < n); i+=dups) {
                int num = i;
                for (int j = i; (j < i + dups) && (j < n); j++) {
                        array[j] = num;
                }
        }

        Shuffle.shuffle(array, 0, array.length-1);
        copy = Arrays.copyOf(array, array.length);
        System.out.println("Sorting an array (with duplicates) of length " + array.length);
        timer.start();
        quickSort(array);
        timer.end();
        System.out.println(timer);

        Arrays.sort(copy);
        System.out.println("Array is sorted: " + Arrays.equals(copy, array));
        System.out.println();
}
}
