/**
 * Driver program for running permutation analysis
 * for take two and heap's algorithms for n = 8..14
 */
public class SP5ProblemADriver {
public static void main(String[] args) {

        // time the different combinatoric functions
        Timer timer = new Timer();

        // permute take 2 method
        long[] times = new long[7];
        int[] pcounts = new int[7];
        int[] n = new int[7];
        int index = 0;
        for (int i = 8; i < 15; i++) {
                int[] a = new int[i];
                int[] c = new int[1]; // for storing permutation count #
                Permutation.init(a, -1);
                timer.start();
                int count = Permutation.takeTwo(a, a.length-1, c, false);
                timer.end();
                n[index] = i;
                pcounts[index] = count;
                times[index] = timer.elapsedTime;
                index++;
        }

        System.out.println();

        System.out.println("Results for Take Two Algorithm");
        System.out.println("------------------------------");
        for (int i = 0; i < times.length; i++) {
                System.out.println("For n = " + n[i] + ", runtime = " + times[i] + " msec");
                System.out.println("Number of permutations = " + pcounts[i]);
                System.out.println();
        }


        System.out.println("Results for Heap's Algorithm");
        System.out.println("------------------------------");
        // permute using heap's algorithm
        times = new long[7];
        pcounts = new int[7];
        n = new int[7];
        index = 0;
        for (int i = 8; i < 15; i++) {
                int[] a = new int[i];
                int[] c = new int[1]; // for storing permutation count #
                Permutation.init(a, -1);
                timer.start();
                int count = Permutation.heaps(a, a.length-1, c, false);
                timer.end();
                n[index] = i;
                pcounts[index] = count;
                times[index] = timer.elapsedTime;
                index++;
        }

        System.out.println();

        for (int i = 0; i < times.length; i++) {
                System.out.println("For n = " + n[i] + ", runtime = " + times[i] + " msec");
                System.out.println("Number of permutations = " + pcounts[i]);
                System.out.println();
        }
}
}
