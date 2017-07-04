import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Arrays;

/**
 * Implements two algorithms for finding most frequently occuring
 * integer in an array of integers
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class MostFrequent {

/**
 * Finds and returns most frequently occurring integer in an array
 * of integers
 *
 * @param arr : array of integers
 *
 * @return freq : most frequently occurring integer
 */
public static int mostFrequent(int[] arr) {
        HashMap<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
                int number = arr[i];
                // get value for key number
                Integer count = counts.get(number);
                // increment count or set to 1 on first occurrence
                counts.put(number, count == null ? 1 : count + 1);
        }

        Integer freq = 0;
        Integer freqCount = 0; // most frequent will occur at least 0 times
        for (Map.Entry<Integer, Integer> keyval : counts.entrySet()) {
                Integer number = keyval.getKey();
                Integer count = keyval.getValue();
                if (count.compareTo(freqCount) > 0) {
                        freq = number;
                        freqCount = count;
                }
        }
        return freq;
}

/**
 * Finds and returns most frequently occurring integer in an array
 * of integers
 *
 * @param arr : array of integers
 *
 * @return freq : most frequently occurring integer
 */
public static int iterativeMostFrequent(int[] arr) {
        Arrays.sort(arr);
        int freq = arr[0]; // best so far
        int count = 1;
        int curr = arr[0]; // currently examining
        int currCount = 1;
        for (int i = 1; i < arr.length; i++) {
                if (arr[i] != curr) {
                        if (currCount > count) {
                                freq = curr;
                                count = currCount;
                        }
                        curr = arr[i];
                        currCount = 1;
                } else {
                        currCount++;
                }
        }

        // check after loop breaks one final time
        if (currCount > count) {
                freq = curr;
                count = currCount;
        }
        return freq;
}

/**
 * For testing purposes
 */
public static void main(String[] args) {

        // generate random array
        int n = 10000000;
        int min = 0;
        int max = 1000;
        Random rand = new Random();

        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
                array[i] = rand.nextInt(max - min + 1);
        }

        Timer timer = new Timer();

        System.out.println("Using HashMap method: ");
        System.out.println("----------------------");
        System.out.println("Finding most frequent integer out of " + n + " integers");
        System.out.println("Range of input integers: " + min + " to " + max);
        timer.start();
        int freq = mostFrequent(array);
        timer.end();
        System.out.println("Most frequent item: " + freq);
        System.out.println(timer);

        System.out.println("Using sorting method: ");
        System.out.println("----------------------");
        System.out.println("Finding most frequent integer out of " + n + " integers");
        System.out.println("Range of input integers: " + min + " to " + max);
        // try sorting method
        timer.start();
        int freq1 = iterativeMostFrequent(array);
        timer.end();
        System.out.println("Most frequent item: " + freq1);
        System.out.println(timer);
}
}
