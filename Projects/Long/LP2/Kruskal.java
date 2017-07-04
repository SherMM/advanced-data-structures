import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Implements Kruskal's algorithms for finding MST of an
 * undirected graph
 *
 * @author G16: Ian Laurain & Vasudev Ravindran
 */
public class Kruskal {

public static int kruskalMST(Graph g) {

        Set<Edge> edgeSet = new HashSet<Edge>();

        for (Vertex v : g) {
                makeSet(v);
                // get edges to sort later
                Set<Edge> temp = new HashSet<Edge>(v.Adj);
                edgeSet.addAll(temp);
        }
        List<Edge> edges = new ArrayList<Edge>(edgeSet);
        Collections.sort(edges); // sort by weight

        int wmst = 0;
        for (Edge edge : edges) {
                Vertex ru = find(edge.From); // rep for u's component
                Vertex rv = find(edge.To); // rep for v's component
                if (!ru.equals(rv)) {
                        // u and v are in different components
                        // since they don't share same rep
                        wmst += edge.Weight;
                        union(ru, rv);
                }
        }
        return wmst;
}

/**
 * Sets parent of vertex u
 *
 * @param u : vertex to set paretn of 
 */
public static void makeSet(Vertex u) {
        u.parent = u;
        u.rank = 0;
}

/**
 * Finds the parent representative of a node set
 *
 * @param u : vertex set to find parent of
 */
public static Vertex find(Vertex u) {
        if (!u.equals(u.parent)) {
                u.parent = find(u.parent);
        }
        return u.parent;
}

/**
 * Performs the set operation of union
 *
 * @param : x set x
 * @param : y set y
 */
public static void union(Vertex x, Vertex y) {
        if (x.rank > y.rank) {
                y.parent = x;
        } else if (y.rank > x.rank) {
                x.parent = y;
        } else { // x.rank == y.rank
                x.parent = y;
                y.rank++;
        }
}

/**
 * For testing porposes
 */
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, false);
        Timer timer = new Timer();

        timer.start();
        int w1 = kruskalMST(g);
        timer.end();
        System.out.println("Running Kruskal's MST algorithm");
        System.out.println("MST Weight: " + w1);
        System.out.println(timer);
}
}
