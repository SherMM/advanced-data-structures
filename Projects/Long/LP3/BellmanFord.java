import java.util.*;

/**
 * Implements Bellman-Ford shortest path algorithm for
 * finding the shortest path from a source node to every reachable
 * node in a digraph
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class BellmanFord {

static final int Infinity = Integer.MAX_VALUE;

/**
 * Calling function to run Bellman-Ford algorithm from a given
 * source vertex. In this case, source vertex is 1.
 *
 * @param g : a digraph
 *
 * @return list : a list of all edges (u,v) in digraph reachable from source vertex s
 */
public static List<Edge> findBellmanPaths(Graph g) {
        // assume source is vertex 1
        List<Edge> paths = findBellmanPaths(g, g.verts.get(1));
        return paths;
}

/**
 * Runs Bellman-Ford algorithm from a source vertex on a digraph. Updates Graph
 * and returns list of all edges reached during the search.
 *
 * @param g : a digraph
 * @param source : source Vertex
 *
 * @return list : a list of edges reached during the search
 */
public static List<Edge> findBellmanPaths(Graph g, Vertex source) {
        // initialize
        for (Vertex u : g) {
                u.d = Infinity;
                u.parent = null;
                u.count = 0;
                u.seen = false;
        }
        source.d = 0;
        source.seen = true;

        // shortest path edges
        List<Edge> paths = new ArrayList<Edge>();

        // FIFO queue to hold unprocessed vertexes
        Queue<Vertex> queue = new ArrayDeque<Vertex>();
        queue.add(source);

        while (!queue.isEmpty()) {
                Vertex u = queue.remove();
                u.seen = false; // no longer in queue
                u.count += 1;

                // cycle detected
                if (u.count >= g.getNumNodes()) {
                        g.updateCycle(u);
                        return null; // negative cycle
                }

                // relax edges
                for (Edge edge : u.Adj) {
                        Vertex v = edge.To;
                        if (v.d > u.d + edge.Weight) {
                                v.d = u.d + edge.Weight;
                                v.parent = edge;
                                if (!v.seen) {
                                        queue.add(v);
                                        v.seen = true;
                                        paths.add(edge);
                                }
                        }
                }
        }
        return paths;
}

/**
 * For testing purposes
 */
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, true);
        List<Edge> paths = findBellmanPaths(g);

        // check paths
        for (Edge edge : paths) {
                System.out.println(edge + " " + edge.To.d);
        }

        System.out.println();

        // check vertexes and predecessor vertexes
        for (Vertex v : g) {
                System.out.println(v.name + " " + v.d + " " + v.parent);
        }
}
}
