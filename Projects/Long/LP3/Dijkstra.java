import java.util.*;

/**
 * Implements Dijkstra's shortest path algorithm for
 * finding the shortest path from a source node to every reachable
 * node in a digraph
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class Dijkstra {

static final int Infinity = Integer.MAX_VALUE;   // initialized value for d

/**
 * Calling function to run Dijkstra's algorithm from a given
 * source vertex. In this case, source vertex is 1.
 *
 * @param g : a digraph
 *
 * @return list : a list of all edges (u,v) in digraph reachable from source vertex s
 */
public static List<Edge> findDijkstraPaths(Graph g) {
        List<Edge> paths = findDijkstraPaths(g, g.verts.get(1));
        return paths;
}

/**
 * Runs Dijkstra's algorithm from a source vertex on a digraph. Updates Graph
 * and returns list of all edges reached during the search.
 *
 * @param g : a digraph
 * @param source : source Vertex
 *
 * @return list : a list of edges reached during the search
 */
public static List<Edge> findDijkstraPaths(Graph g, Vertex source) {

        // initialize
        for (Vertex v : g) {
                v.seen = false;
                v.d = Infinity;
                v.parent = null;
        }
        source.d = 0;

        // minPQ comparator
        Comparator<Vertex> minh = new Comparator<Vertex>() {
                public int compare(Vertex vert1, Vertex vert2) {
                        return vert1.compareTo(vert2);
                }
        };

        // shortest path edges
        List<Edge> paths = new ArrayList<Edge>();

        // number of nodes
        int m = g.getNumNodes();
        // initialize priority queue
        IndexedHeap<Vertex> queue = new IndexedHeap<Vertex>(m, minh);
        for (Vertex v : g) {
                queue.add(v);
        }

        while (!queue.isEmpty()) {
                Vertex curr = queue.remove();
                curr.seen = true;
                // relax neighbor edges of curr
                for (Edge edge : curr.Adj) {
                        Vertex u = edge.From;
                        Vertex v = edge.otherEnd(u);
                        if ((v.d > u.d + edge.Weight) && !v.seen) {
                                v.d = u.d + edge.Weight;
                                v.parent = edge;
                                queue.decreaseKey(v);
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
        List<Edge> paths = findDijkstraPaths(g);

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
