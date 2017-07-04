/**
 * Implements an algorithm to find and print out the nCk combinations
 * of a group of n numbers (1..n)
 *
 * @author G16: Ian Laurain & Vasudev Ravindran
 */
public class Combination {

// for keeping track of number of combinations found
private static int count = 0;

/**
 * Visits and prints out the current combination which is indicated
 * by the values in the array that aren't set to false
 *
 * @param a: a boolean array
 */
public static void visit(boolean[] a) {
        for (int i = 1; i < a.length; i++) {
                if (a[i]) {
                        System.out.print(i + " ");
                }
        }
        count++;
        System.out.println();
}

/**
 * Implements algorithm to find the nCk combinations of a group of numbers
 *
 * @param a: a boolean array
 * @param n: length of the array
 * @param k: number to choose from array of length n
 */
public static void combinations(boolean[] a, int n, int k) {
        if (k > n) {
                return;
        } else if (k == 0) {
                visit(a);
        } else {
                combinations(a, n-1, k);
                a[n] = true;
                combinations(a, n-1, k-1);
                a[n] = false;
        }
}

/**
 * For testing purposes
 */
public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int k = Integer.parseInt(args[1]);
        boolean[] a = new boolean[n+1]; // ignore 0 index
        for (int i = 0; i < a.length; i++) {
                a[i] = false;
        }

        combinations(a, n, k);
        System.out.println("Total combinations: " + count);
}
}
