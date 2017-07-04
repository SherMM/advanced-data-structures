import java.util.*;

/**
 * Implements DAG shortest path algorithm for
 * finding the shortest path from a source node to every reachable
 * node in a digraph
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class DAGShortestPaths {

static final int Infinity = Integer.MAX_VALUE; // initialized value for d

/**
 * Calling function to run DAG SP algorithm from a given
 * source vertex. In this case, source vertex is 1.
 *
 * @param g : a digraph
 *
 * @return list : a list of all edges (u,v) in digraph reachable from source vertex s
 */
public static List<Edge> findDAGPaths(Graph g) {
        List<Edge> paths = findDAGPaths(g, g.verts.get(1));
        return paths;
}

/**
 * Runs DAG SP algorithm from a source vertex on a digraph. Updates Graph
 * and returns list of all edges reached during the search.
 *
 * @param g : a digraph
 * @param source : source Vertex
 *
 * @return list : a list of edges reached during the search
 */
public static List<Edge> findDAGPaths(Graph g, Vertex source) {

        // get a topological ordering
        Deque<Vertex> order = TopoSort.sortByDFS(g);

        // intialize
        for (Vertex v : order) {
                v.seen = false;
                v.d = Infinity;
                v.parent = null;
        }
        source.d = 0;

        List<Edge> paths = new ArrayList<Edge>();

        // loop through vertexes in topological ordering
        for (Vertex from : order) {
                // relax adjacent edges
                for (Edge edge : from.Adj) {
                        Vertex to = edge.To;
                        if (from.d != Infinity && (to.d > from.d + edge.Weight)) {
                                to.d = from.d + edge.Weight;
                                to.parent = edge;
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
        List<Edge> paths = findDAGPaths(g);

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
