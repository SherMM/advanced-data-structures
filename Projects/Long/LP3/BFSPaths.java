import java.util.*;

/**
 * Implements BFS shortest path algorithm for
 * finding the shortest path from a source node to every reachable
 * node in a digraph
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class BFSPaths {

static final int Infinity = Integer.MAX_VALUE;   // initialized value for d

/**
 * Calling function to run BFS SP algorithm from a given
 * source vertex. In this case, source vertex is 1.
 *
 * @param g : a digraph
 *
 * @return list : a list of all edges (u,v) in digraph reachable from source vertex s
 */
public static List<Edge> findBFSPaths(Graph g) {
        List<Edge> paths = findBFSPaths(g, g.verts.get(1));
        return paths;
}

/**
 * Runs BFS SP algorithm from a source vertex on a digraph. Updates Graph
 * and returns list of all edges reached during the search.
 *
 * @param g : a digraph
 * @param source : source Vertex
 *
 * @return list : a list of edges reached during the search
 */
public static List<Edge> findBFSPaths(Graph g, Vertex source) {

        // intialize vertexes in graph
        for (Vertex v : g) {
                v.d = Infinity;
                v.parent = null;
                v.seen = false;
        }

        List<Edge> paths = new ArrayList<Edge>();

        // FIFO queue to hold unprocessed vertexes
        Queue<Vertex> queue = new ArrayDeque<Vertex>();

        // initialize source vertex
        source.d = 0;
        source.seen = true;
        queue.add(source);

        while (!queue.isEmpty()) {
                Vertex curr = queue.remove();
                // relax edges
                for (Edge edge : curr.Adj) {
                        Vertex u = edge.From;
                        Vertex v = edge.To;
                        if (!v.seen) {
                                v.d = u.d + 1;
                                v.parent = edge;
                                v.seen = true;
                                queue.add(v);
                                paths.add(edge);
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
        List<Edge> paths = findBFSPaths(g);

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
