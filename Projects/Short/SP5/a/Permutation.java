/**
 * Implements three different algorithms for finding the permutations
 * of a group of n numbers (from 1..n)
 *
 * @author G16: Ian Laurain & Vasudev Ravindran
 */
public class Permutation {

/**
 * Visits and prints out the current permutation which is indicated
 * by the values in the array that aren't set to zero
 *
 * @param a: an integer array
 * @param print: boolean to indicate if we want to print the permutation
 */
public static void visit(int[] a, boolean print) {
        for (int i = 1; i < a.length; i++) {
                if (a[i] != 0 && print) {
                        System.out.print(a[i] + " ");
                }
        }
        if (print) {
                System.out.println();
        }
}

/**
 * Implements the Take One algorithm for finding permutations of a group of 1..n
 * numbers
 *
 * @param a: an integer array
 * @param i: value to track when permutation should be visited
 * @param c: integer array to keep track of permutation count
 * @param print: boolean that indicates if we should print the permutation
 *
 * @return count: number of permutations found
 */
public static int takeOne(int[] a, int i, int[] c, boolean print) {
        if (i == 0) {
                visit(a, print);
                c[0]++;
        } else {
                for (int k = 1; k < a.length; k++) {
                        if (a[k] == 0) {
                                a[k] = i;
                                takeOne(a, i-1, c, print);
                                a[k] = 0;
                        }
                }
        }
        return c[0];
}


/**
 * Implements the Take Two algorithm for finding permutations of a group of 1..n
 * numbers
 *
 * @param a: an integer array
 * @param i: value to track when permutation should be visited
 * @param c: integer array to keep track of permutation count
 * @param print: boolean that indicates if we should print the permutation
 *
 * @return count: number of permutations found
 */
public static int takeTwo(int[] a, int i, int[] c, boolean print) {
        if (i == 0) {
                visit(a, print);
                c[0]++;
        } else {
                for (int j = 1; j <= i; j++) {
                        swap(a, i, j);
                        takeTwo(a, i-1, c, print);
                        swap(a, i, j);
                }
        }
        return c[0];
}

/**
 * Implement's heaps algorithm for Permute
 *
 * @param a: an integer array
 * @param n: length of integer array
 * @param c: array for tracking number of permutations found
 * @param print: boolean to indicate if we want visit function to print permutations
 *
 * @return count: number of permutations found
 */
public static int heaps(int[] a, int n, int[] c, boolean print) {
        if (n == 0) {
                visit(a, print);
                c[0]++;
        } else {
                for (int i = 1; i < n; i++) {
                        heaps(a, n-1, c, print);
                        if (n % 2 == 0) {
                                swap(a, i, n);
                        } else {
                                swap(a, 1, n);
                        }
                }
                heaps(a, n-1, c, print);
        }
        return c[0];
}

/**
 * Swaps items at positions i and j in an integer array
 *
 * @param a: an integer array
 * @param i: array index position
 * @param j: array index position
 */
public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
}

/**
 * Initializes an integer array with a specified fill value
 *
 * @param a: an integer array
 * @param fill: a value to fill the positions in array with
 */
public static void init(int[] a, int fill) {
        for (int i = 0; i < a.length; i++) {
                if (fill == -1) {
                        a[i] = i;
                } else {
                        a[i] = fill;
                }
        }
}

/**
 * For testing purposes
 */
public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int[] a = new int[n+1];

        init(a, 0);
        int[] c = new int[1];
        int count = takeOne(a, a.length-1, c, true);
        System.out.println("Total permutations: " + count);

        System.out.println();

        init(a, -1);
        c[0] = 0;
        count = takeTwo(a, a.length-1, c, true);
        System.out.println("Total permutations: " + count);

        System.out.println();

        init(a, -1);
        c[0] = 0;
        count = heaps(a, a.length-1, c, true);
        System.out.println("Total permutations: " + count);
}
}
