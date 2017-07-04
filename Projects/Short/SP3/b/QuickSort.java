import java.util.Random;
import java.util.Arrays;

/**
 * Implements standard quicksort with Hoare partitioning
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class QuickSort {

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

        // index of pivot element
        int pdx = partition(items, low, high);
        // sort the two zones partitioned in the array
        quickSort(items, low, pdx-1);
        quickSort(items, pdx+1, high);
}

/**
 * Paritions an array/list around a randomly chosen index (pivot index)
 *
 * @param list the list of elements to be partitioned
 * @param lo integer index that is lowest pt of numbers to be partitioned
 * @param hi integer index that is highest pt of numbers to be partitioned
 *
 * @return the pivot index value
 */
public static<T extends Comparable<? super T> > int partition(T[] items, int lo, int hi) {

        // get a random pivot
        Random rand = new Random();
        int pindex = rand.nextInt((hi - lo) + 1) + lo; // index to pivot at
        T pivot = items[pindex];
        swap(items, lo, pindex);
        int back = lo + 1; // for tracking item at index behind current item
        for (int i = lo+1; i < hi+1; i++) {
                // check if item needs to be on other side of pivot
                if (items[i].compareTo(pivot) < 0) {
                        swap(items, i, back);
                        back++;
                }
        }
        swap(items, lo, back-1);
        return back-1;
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
        System.out.println("Standard QuickSort");
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
