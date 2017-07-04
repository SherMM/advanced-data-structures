import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

/**
 * This program implements quicksort using a stack data structure
 * to simulate the recursiion that takes place in the textbook, recursive
 * version of quicksort
 *
 * @author G16
 */
public class QuickSortStack {

/**
 * Paritions an array/list around a randomly chosen index (pivot index)
 *
 * @param list the list of elements to be partitioned
 * @param lo integer index that is lowest pt of numbers to be partitioned
 * @param hi integer index that is highest pt of numbers to be partitioned
 *
 * @return the pivot index value
 */
public static<T extends Comparable<? super T> > int partition(List<T> list, int lo, int hi) {

        Random rand = new Random();
        int pindex = rand.nextInt((hi - lo) + 1) + lo; // index to pivot at
        T pivot = list.get(pindex);
        Collections.swap(list, lo, pindex);
        int back = lo + 1; // for tracking item at index behind current item
        for (int i = lo+1; i < hi+1; i++) {
                // check if item needs to be on other side of pivot
                if (list.get(i).compareTo(pivot) < 0) {
                        Collections.swap(list, i, back);
                        back++;
                }
        }
        Collections.swap(list, lo, back-1);
        return back-1;
}

/**
 * Sorts an array/list by continually partitioning elements around a pivot
 * element. Utilizes a stack to simulate the often-seen recursive implementation
 * of quicksort
 *
 * @param list the list to be sorted
 * @param lo the low index of the list
 * @param hi the high index of the list
 *
 * @return the sorted list
 */
public static<T extends Comparable<? super T> > void quicksort(List<T> list, int lo, int hi) {
        // handle case of empty list
        if (list.size() == 0) {
               return;
        }
        // stack to store integer indexes to simulate quicksort recursion
        ArrayDeque<Integer> stack = new ArrayDeque<Integer>();
        stack.push(lo); // push first two delimeters for partition onto stack
        stack.push(hi);

        while(!stack.isEmpty()) {
                // pop next two elements from stack
                hi = stack.pop();
                lo = stack.pop();

                // get next pivot index
                int pdx = partition(list, lo, hi);

                // handle elements to left side of pivot index in list
                if (pdx-1 > lo) {
                        stack.push(lo); // push bottom idx of left sublist
                        stack.push(pdx-1); // push top idx (exclude piv) of left sublist
                }

                // handle elements to right of pivot index in list
                if (pdx+1 < hi) {
                        stack.push(pdx+1); // push bottom idx of right sublist
                        stack.push(hi); // push top idx of left sublist
                }
        }
}

/**
 * Section for testing
 */
public static void main(String[] args) {

        List<Integer> list = new ArrayList<Integer>();
        int n = 25; // list length
        int min = 0; // rand integer range bottom
        int max = 51; // rand integer range top

        // generate random n random numbers and add to list
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
                int num = rand.nextInt((max - min) + 1) + min;
                list.add(num);
        }

        System.out.println("The original list: ");
        System.out.println(list.toString());

        quicksort(list, 0, list.size()-1);
        System.out.println("The sorted list: ");
        System.out.println(list);

}
}
