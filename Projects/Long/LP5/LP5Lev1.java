
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver program to run LP5-Level1
 *
 * @author RBK and G16: Ian Laurain, Vasudev Ravindran
 */
public class LP5Lev1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        boolean VERBOSE = false;

        if (args.length > 0) {
            File inputFile = new File(args[0]);
            in = new Scanner(inputFile);
        } else {
            in = new Scanner(System.in);
        }
        if (args.length > 1) {
            VERBOSE = true;
        }
        Graph g = Graph.readGraph(in, false);   // read undirected graph from stream "in"
        // Create your own class and call the function to find a maximum matching.
        // int result = Matching.matching(g);
        // System.out.println(result);
        // if (VERBOSE) {
        //     Output the edges of M.
        // }

        double startTime = System.nanoTime();
        MaximumCardinalityMatching maximumCardinalityMatching = new MaximumCardinalityMatching(g);

        if (maximumCardinalityMatching.errorStatus == 0) {

            if (VERBOSE) {

                for (Edge edge : maximumCardinalityMatching) {

                    System.out.println(edge);
                }
            } else {

                System.out.println(maximumCardinalityMatching.size());
            }
        } else {

            System.out.println("Graph is not Bipartite!!");
        }

        double executiontime = ((double)System.nanoTime() - startTime) / 1000000000;
        System.out.println("Execution time : " + executiontime + " seconds");
    }


}
