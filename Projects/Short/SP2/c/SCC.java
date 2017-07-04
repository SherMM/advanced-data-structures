import java.util.ArrayDeque;
import java.util.List;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Exception;

/**
 * This program implements the Kosaraju-Sharir algorithm for finding
 * the number of strongly connected components in a directed graph.
 * It relies heavily on Depth-First-Search for its functionality
 *
 * @author G16
 */
public class SCC {

/**
 * Finds and returns the number of strongly connected components
 * in a digraph
 *
 * @param g : a digraph to be examined
 *
 * @return int comp : the number of SCC in the digraph g
 */
public static int findNumSCC(Graph g) {
        Graph r = g.reverse();
        // get latest->earliest finishing vertexes in DFS
        Deque<Vertex> order = DFS.dfs(g);
        int comp = 0; // # of connected components
        for (Vertex v : order) {
                Vertex u = r.getVertex(v.name);
                if (!u.seen) {
                        componentSearch(r, g, u, comp);
                        comp++;
                }
        }

        // need to set component groups in original graph
        //for (Vertex v : r) {
        //        g.getVertex(v.name).group = v.group;
        //}
        return comp;
}

/**
 * Performs DFS on a directed graph and assigns each vertex
 * to a connected component
 *
 * @param g : a directed Graph - reverse of h
 * @param h : a directed Graph - original input graph
 * @param v : the vertex to start from
 * @param comp : the current connected component
 */
public static void componentSearch(Graph g, Graph h, Vertex v, int comp) {
        v.seen = true;
        v.group = comp;  // component number for vertex
        h.getVertex(v.name).group = comp;
        // examine vertex neighbors
        for (Edge edge : v.Adj) {
                Vertex out = edge.To;
                if (!out.seen) {
                        componentSearch(g, h, out, comp);
                }
        }
}

/**
 * Returns a list of lists, each contained the vertexes belonging
 * to a specific SCC
 *
 * @param g : a digraph
 *
 * @return comps : a list of connected components
 */
public static ArrayList<List<Vertex> > getSCC(Graph g) {
        // get the number of strongly connected components
        int numSCC = findNumSCC(g);

        // initialze array for holding component vertexes
        ArrayList<List<Vertex> > comps = new ArrayList<List<Vertex> >();
        for (int i = 0; i < numSCC; i++) {
                List<Vertex> list = new ArrayList<Vertex>();
                comps.add(i, list);
        }

        // add component vertex to its specific bucket
        for (Vertex v : g) {
                comps.get(v.group).add(v);
        }
        return comps;
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
        int n = findNumSCC(g1);
        System.out.println("Number of Strongly Connected Components: " + n);
        System.out.println();

        // print out vertexes in each component
        System.out.println("Components and their vertexes: ");
        System.out.println("-------------------------------");
        Graph g2 = g.copy();
        ArrayList<List<Vertex> > components = getSCC(g2);
        for (int i = 0; i < components.size(); i++) {
                System.out.println("Component: " + i);
                System.out.println();
                for (Vertex v : components.get(i)) {
                        System.out.print(v.name + " ");
                }
                System.out.println();
                System.out.println();
        }
}
}
