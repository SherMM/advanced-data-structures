// Non-mutative mergesort implementation
// (c) 2016 Ian C. Laurain
// CS6301.002


import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MergeSort<T extends Comparable <? super T> > {

/**
 * Empty constructor
 */
public MergeSort() {

}

/**
 * merges two sorted lists
 * @param list1 first list to merge
 * @param list2 second list to merge
 */
public List<T> merge(List<T> list1, List<T> list2) {
        List<T> merged = new ArrayList<T>();
        int i = 0;
        int j = 0;
        while (i < list1.size() && j < list2.size()) {
                if (list1.get(i).compareTo(list2.get(j)) <= 0) {
                        merged.add(list1.get(i));
                        i++;
                } else {
                        merged.add(list2.get(j));
                        j++;
                }
        }
        // handle remaining elements
        if (i < list1.size()) {
                List<T> list1Sub = list1.subList(i, list1.size());
                merged.addAll(list1Sub);
        } else {
                List<T> list2Sub = list2.subList(j, list2.size());
                merged.addAll(list2Sub);
        }
        return merged;
}

/**
 * Sorts a list using mergesort algorithm
 * @param list the list to be sorted
 */
public List<T> mergeSort(List<T> list) {
        if (list.size() == 1 || list.size() == 0) {
                return list;
        }

        int low = 0;
        int high = list.size();
        int mid = list.size() / 2;
        List<T> lower = list.subList(low, mid);
        List<T> upper = list.subList(mid, high);

        return merge(mergeSort(lower), mergeSort(upper));

}

/**
 * check if two lists are equal
 * @param list1 the first list to check against
 * @param list2 the second list to check against
 */
public boolean equals(List<T> list1, List<T> list2) {
        // sort original list (list2) to check that sort works
        if (list1.size() != list2.size()) {
                return false;
        }

        Collections.sort(list2);
        for (int i = 0; i < list1.size(); i++) {
                if (list1.get(i).compareTo(list2.get(i)) != 0) {
                        return false;
                }
        }
        return true;
}

public static void main(String[] args) {
        System.out.println("Compilation Completed");
        System.out.println();
        List<Integer> mergeList1 = new ArrayList<Integer>();
        List<Integer> mergeList2 = new ArrayList<Integer>();
        List<Integer> testList1 = new ArrayList<Integer>();

        // sizes of 2 lists for testing merge method
        int x = 10;
        int y = 10;

        int n = 50;
        int min = 0;
        int max = 500;

        // add elements to the lists
        Random rand = new Random();
        for (int i = 0; i < x; i++) {
                mergeList1.add(rand.nextInt(max - min + 1));
        }
        for (int i = 0; i < y; i++) {
                mergeList2.add(rand.nextInt(max - min + 1));
        }

        // test merge
        MergeSort<Integer> mt = new MergeSort<Integer>();

        // sort two lists first so merge works correctly
        Collections.sort(mergeList1);
        Collections.sort(mergeList2);

        System.out.println("Two lists before merge:");
        System.out.println(mergeList1.toString());
        System.out.println(mergeList2.toString());

        System.out.println("After merge:");
        System.out.println(mt.merge(mergeList1, mergeList2).toString());
        System.out.println();

        // test merge sort
        for (int i = 0; i < n; i++) {
                testList1.add(rand.nextInt(max - min + 1));
        }

        System.out.println("List state before merge sort:");
        System.out.println(testList1.toString());

        MergeSort<Integer> ms = new MergeSort<Integer>();
        List<Integer> merged = ms.mergeSort(testList1);

        System.out.println("List state after merge sort:");
        System.out.println(merged.toString());
        System.out.print("The list sorted: ");
        System.out.println(ms.equals(merged, testList1));
        System.out.println();

        // test for empty list
        List<Integer> none = new ArrayList<Integer>();
        System.out.println(ms.mergeSort(none));
}
}
