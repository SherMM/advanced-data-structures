import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This program implements two versions of Depth First Search.
 * The first is used when only the graph is given, and it returns
 * a stack of vertexes in reverse post order, or topological order
 * (if the graph is a DAG)
 *
 * The second version of Depth First Search is used when assigning
 * vertexes to a connected component and also tracking how many
 * connected components there are in total.

 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class DFS {

/**
 * Performs DFS on a graph and returns a stack/list of the vertexes
 * in topological order
 *
 * @param g : a directed Graph
 *
 * @return order : the topological order for the graph
 */
public static Deque<Vertex> dfs(Graph g) {
        // stack to hold finished vertexes
        Deque<Vertex> order = new ArrayDeque<Vertex>();
        for (Vertex v : g) {
                if(!v.seen) {
                        dfs(g, v, order);
                }
        }
        return order;
}

/**
 * A helper method for the dfs method above. From a given vertex,
 * it performs a recursive depth first Search
 *
 * @param g : a directed Graph
 * @param v : the vertex to start from
 * @param order : the stack of finished vertexes
 *
 */
private static void dfs(Graph g, Vertex v, Deque<Vertex> order) {
        v.seen = true;
        // examine vertex neighbors
        for (Edge edge : v.Adj) {
                Vertex out = edge.To;
                if (!out.seen) {
                        dfs(g, out, order);
                }
        }
        // want to add vertex after recursive call to get
        // reverse post order, which is also topological order
        order.push(v);
}


/**
 * For testing purposes
 */
public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        if (args.length > 0) {
                File inputFile = new File(args[0]);
                in = new Scanner(inputFile);
        } else {
                in = new Scanner(System.in);
        }

        Graph g = Graph.readGraph(in, true);
        Deque<Vertex> result = dfs(g);
        System.out.println(result.toString());
}
}
