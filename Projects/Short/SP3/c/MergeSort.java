import java.util.Random;
import java.util.Arrays;

/**
 * Implements MergeSort algorithms with optimizations from lecture
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class MergeSort {

// threshold indicating when to use insertion sort
private static final int THRESH = 11;

/**
 * Calling method for mergesort. Lets user only worry about passing in array
 * to be sorted
 *
 * @param items : array of items of generic type T
 */
@SuppressWarnings("unchecked")
public static<T extends Comparable<? super T> > void  mergeSort(T[] items) {

        int n = items.length;
        T[] temp = (T[]) new Comparable[n];
        int result = mergeSort(items, 0, n-1, temp);
        // need to copy results in temp back to items array
        if (result == 1) {
                for (int i = 0; i < temp.length; i++) {
                        items[i] = temp[i];
                }
        }
}

/**
 * Sorts an array of items using mergesort algorithm
 *
 * Starter pseudocode from RBK's class notes
 *
 * @param items : array to sort
 * @param low : bottom array index
 * @param high : upper array index
 * @param temp : array for doing merges
 *
 * @return integer indicating array state (i.e. are items in array or temp array)
 */
public static<T extends Comparable<? super T> > int mergeSort(T[] items, int low, int high, T[] temp) {

        // sorts below threshold value are performed by insertion sort
        int n = high - low + 1;
        if (n <= THRESH) {
                insertionSort(items, low, high);
                return 0;
        }

        int mid = (high + low) / 2;
        int t1 = mergeSort(items, low, mid, temp);
        int t2 = mergeSort(items, mid+1, high, temp);

        if (t1 == 0) {
                merge(items, temp, low, mid, high);
                return 1;
        } else {
                merge(temp, items, low, mid, high);
                return 0;
        }

}

/**
 * Merges two arrays
 *
 * @param arr1 : array of items that reps two subarrays to be merged
 * @param arr2 : array to merge arr1 subarrays into
 * @param low : bottom index of first subarray
 * @param mid : upper index of first subarray and bottom index (+1) of second subarray
 * @param high : upper index of second subarray
 */
public static<T extends Comparable<? super T> > void merge(T[] arr1, T[] arr2, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = low;
        while (i <= mid && j <= high) {
                if (arr1[i].compareTo(arr1[j]) <= 0) {
                        arr2[k++] = arr1[i];
                        i++;
                } else {
                        arr2[k++] = arr1[j];
                        j++;
                }
        }

        // handle remaining elements
        if (i <= mid) {
                for (int m = i; m <= mid; m++) {
                        arr2[k++] = arr1[m];
                }
        } else {
                for (int n = j; n <= high; n++) {
                        arr2[k++] = arr1[n];
                }
        }
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
 * For Testing purposes
 */
public static void main(String[] args) {
        int n = 4194304;
        int min = 0;
        int max = 750;
        Random rand = new Random();
        Timer timer = new Timer();

        // Testing an array of n randomly generated elements
        Integer[] array = new Integer[n];

        for (int i = 0; i < n; i++) {
                array[i] = rand.nextInt(max - min + 1);
        }

        // need copy to check sort
        Integer[] copy = Arrays.copyOf(array, array.length);

        System.out.println("Sorting a random array of length " + array.length);
        timer.start();
        mergeSort(array);
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
        mergeSort(array);
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
        mergeSort(array);
        timer.end();
        System.out.println(timer);

        Arrays.sort(copy);
        System.out.println("Array is sorted: " + Arrays.equals(copy, array));
        System.out.println();
}
}
