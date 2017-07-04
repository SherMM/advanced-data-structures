import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Deque;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Exception;

/**
 * This program implments two different methods for finding a topological
 * ordering in Directed Acyclic Graphs (DAG). The first method finds
 * vertexes with zero in-degree values, and adds them to a list until
 * it can't find anymore. It then uses this list length to determine if a
 * cycle exists, or a topological ordering has been found. The second method
 * uses the Depth First Search algorithm to find a topological ordering of
 * the graph.
 *
 * @author G16
 */
public class TopoSort {

/**
 * Returns a topological sorting of a directed graph if it is a DAG,
 * otherwise returns null
 *
 * @param g : a directed graph
 *
 * @return order : a list of vertexes which a topological sorting for g
 * @return null : if g has a cycle, null is returned
 */
public static List<Vertex> sortByInDegree(Graph g) {
        // Topological ordering
        List<Vertex> order = new ArrayList<Vertex>();
        // vertexes with in-degree value of zero
        Queue<Vertex> queue = new ArrayDeque<Vertex>();

        // find any vertexes with an in-degree value of 0
        for (Vertex u : g) {
                if (u.inDegree == 0) {
                        queue.add(u);
                }
        }

        // while there are vertexes with in-degree value of zero
        while (!queue.isEmpty()) {
                Vertex v = queue.remove();
                order.add(v);
                // process adjacent vertexes of current vertex
                for (Edge edge : v.Adj) {
                        Vertex out = edge.To;
                        out.inDegree -= 1;
                        if (out.inDegree == 0) {
                                queue.add(out);
                        }
                }
        }

        // if queue is empty but ordering has less elements than
        // the number of vertexes in graph, then there's a cycle
        if (order.size() < g.numNodes) {
                return null;
        }
        return order;
}

/**
 * Returns a topological ordering of a directed graph if it is a DAG,
 * otherwise returns null. Uses DFS to find the ordered and cycle (if it
 * exists in the digraph)
 *
 * @param g : a digraph
 *
 * @return a topological sorting of g
 * @return null : if g has a cycle, null is returned
 */
public static Deque<Vertex> sortByDFS(Graph g) {
                if (HasDirectedCycle.hasCycle(g)) {
                  return null;
                }
                return DFS.dfs(g);
}

/**
 * For testing purposes
 *
 */
public static void main(String[] args) throws FileNotFoundException {
        System.out.println();
        Scanner in;
        if (args.length > 0) {
                File inputFile = new File(args[0]);
                in = new Scanner(inputFile);
        } else {
                in = new Scanner(System.in);
        }

        Graph g = Graph.readGraph(in, true);

        Graph g1 = g.copy();
        Graph g2 = g.copy();

        List<Vertex> topoOrder1 = sortByInDegree(g1);
        System.out.println("Results for sorting by in-degree: ");
        System.out.println("----------------------------------");
        if (topoOrder1 != null) {
                System.out.println(topoOrder1.toString());
        } else {
                System.out.println("The Graph has a cycle");
        }

        System.out.println();

        System.out.println("Results for sorting by DFS: ");
        System.out.println("----------------------------------");
        Deque<Vertex> topoOrder2 = sortByDFS(g2);
        if (topoOrder2 != null) {
                System.out.println(topoOrder2.toString());
        } else {
                System.out.println("The Graph has a cycle");
        }

        System.out.println();

}
}
