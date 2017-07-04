// Tests priority queue and mergesort sorting runtimes for
// sorting lists of items
// (c) 2016 Ian C. Laurain
// CS6301.002

import java.util.Random;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class SortingTest {
//private static int phase = 0;
//private static long startTime, endTime, elapsedTime;

/**
 * Tests mergesort and priority queue for sorting lists
 * of items. Both are O(nlogn), but we will use these Tests
 * to get visually quantifiable results
 * User timer method in Search.java written by Dr. Balaji
 */
public static void main(String[] args) {
        ArrayList <Integer>list = new ArrayList<Integer>();
        // number of items to add to list
        int n = 10000000;

        // numbers that control range of random integer
        int min = 0;
        int max = 50000;
        
        // add elements to the array list
        Random rand = new Random();
        for(int i=0; i < 1000000; i++) {
                list.add(rand.nextInt(max - min + 1));
        }

        // PQ test
        Search.timer();
        PriorityQueue <Integer>pq = new PriorityQueue<Integer>(list);
        System.out.println("Start removing items from PQ");
        while (!pq.isEmpty()) {
          pq.poll();
        }
        Search.timer();

        System.out.println();

        // MergeSort test
        Search.timer();
        MergeSort <Integer> ms = new MergeSort<Integer>();
        System.out.println("Starting mergesort");
        ms.mergeSort(list);
        Search.timer();
}
}
