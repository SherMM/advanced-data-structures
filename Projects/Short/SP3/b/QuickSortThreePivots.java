import java.util.Random;
import java.util.Arrays;

/**
 * Implements the 3-way (3 pivot) partitioning for quick sort
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class QuickSortThreePivots {

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
 * Quicksort method to sort an array of generic items
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

        // indexes of pivots
        int[] pdxs = partition(items, low, high);
        // sort the two zones partitioned in the array
        quickSort(items, low, pdxs[0]-1);
        quickSort(items, pdxs[1]+1, high);
}

/**
 * Partions array using three-way pivot method, where array is partitioned
 * into three distinct zones (<, ==, > pivot element)
 *
 * pseudo code ideas referecned at: http://www.sorting-algorithms.com/quick-sort-3-way
 *
 * @param items : array being partitioned
 * @param low : bottom index in array
 * @param high : upper index in array
 *
 * @return integer array of pivot indexes
 */
public static<T extends Comparable<? super T> > int[] partition(T[] items, int low, int high) {
        int[] idxs = new int[2];

        int i = low;
        int j = low;
        int k = high;

        int pdx = low;
        T piv = items[pdx];

        while (j <= k) {
                int comp = items[j].compareTo(piv);
                if (comp < 0) {
                        swap(items, i, j);
                        i++;
                        j++;
                } else if (comp > 0) {
                        swap(items, j, k);
                        k--;
                } else {
                        j++;
                }
        }

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

        System.out.println("3-pivot QuickSort");
        System.out.println("-------------------------------------------");
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
