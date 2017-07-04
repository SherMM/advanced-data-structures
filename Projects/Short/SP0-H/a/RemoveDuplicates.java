import java.util.HashSet;
import java.util.Random;

/**
 * Implements an algorithm to find number of distinct items
 * in an array, and move these items to front of array
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class RemoveDuplicates {

/**
 * Finds and returns the number of distinct elements in an array.
 * Also moves these items to the front of array.
 *
 * @param arr : array or objects of type T
 *
 * @return k : number of distinct items in array
 */
public static<T> int findDistinct(T[] arr) {
        HashSet<T> set = new HashSet<T>();

        // add items to set
        for (int i = 0; i < arr.length; i++) {
                set.add(arr[i]);
        }

        // add items to front of array
        int k = 0;
        for (T item : set) {
                arr[k] = item;
                k++;
        }
        return k;
}

/**
 * For testing purposes
 */
public static void main(String[] args) {
        // Generate random array of integers
        int n = 1000000;
        int min = 0;
        int max = 25;
        Random rand = new Random();

        Integer[] array = new Integer[n];

        for (int i = 0; i < n; i++) {
                array[i] = rand.nextInt(max - min + 1);
        }

        int k = findDistinct(array);
        System.out.println(k);

        // simple test for visual purposes
        Integer[] simple = {1, 2, 3, 4, 5, 6, 1, 2, 2, 3, 2, 6, 7, 6, 6, 7, 7, 6, 6};
        int k1 = findDistinct(simple);
        System.out.println(k1);
}
}
