import java.util.Scanner;
import java.lang.Comparable;
import java.util.Comparator;

/**
 * This program implements two different versions of Prim's algorithm
 * for finding the minimum spanning tree in an undirected graph. The
 * first version uses a binary heap for the priority queue, and the second
 * version uses an indexed heap for the priority queue.
 *
 * @author G16
 */
public class MST {

static final int Infinity = Integer.MAX_VALUE; // initialized value for d

/**
 * Uses binary heap to implement prims algorithm. Edges examined by
 * their weight priority in a normal binary heap data structure
 *
 * @param g : an undirected graph
 *
 * @return the weight of the minimum spanning tree
 */
public static int PrimMST(Graph g) {

        for (Vertex vert : g) {
                vert.seen = false;
                vert.parent = null;
        }

        // initialize
        int wmst = 0;
        Vertex src = g.verts.get(1);
        src.seen = true;
        int m = g.getNumEdges();

        // minPQ comparator
        Comparator<Edge> minh = new Comparator<Edge>() {
                public int compare(Edge edge1, Edge edge2) {
                        return edge1.compareTo(edge2);
                }
        };

        PQ<Edge> edges = new BinaryHeap(m, minh);
        for (Edge e : src.Adj) {
                edges.add(e);
        }

        while (!edges.isEmpty()) {
                Edge curr = edges.deleteMin();
                Vertex from = curr.From;
                Vertex to = curr.To;
                if (from.seen && to.seen) {
                        continue;
                }
                Vertex u = from.seen ? from : to;
                Vertex v = !from.seen ? from : to;
                v.parent = u;
                wmst += curr.Weight;
                v.seen = true;
                for (Edge adj : v.Adj) {
                        if (!(adj.From.seen && adj.To.seen)) {
                                edges.add(adj);
                        }
                        Vertex w = adj.otherEnd(v);
                        if (!w.seen) {
                                edges.add(adj);
                        }
                }
        }
        return wmst;
}

/**
 * Uses indexed heap to implement prims algorithm. Nodes examined by
 * their d (distance - related to weight) priority in an indexed heap \
 * data structure
 *
 * @param g : an undirected graph
 *
 * @return the weight of the minimum spanning tree
 */
public static int Prim(Graph g) {

        for (Vertex vert : g) {
                vert.seen = false;
                vert.parent = null;
                vert.d = Infinity;
        }

        // initialize
        int wmst = 0;
        Vertex src = g.verts.get(1);
        src.d = 0; // set initial distTo to zero
        int m = g.getNumNodes();

        // minPQ comparator
        Comparator<Vertex> minh = new Comparator<Vertex>() {
                public int compare(Vertex vert1, Vertex vert2) {
                        return vert1.compareTo(vert2);
                }
        };

        IndexedHeap<Vertex> nodes = new IndexedHeap<Vertex>(m, minh);
        for (Vertex u : g) {
                nodes.add(u);
        }

        while (!nodes.isEmpty()) {
                Vertex u = nodes.deleteMin();
                u.seen = true;
                wmst += u.d;
                for (Edge edge : u.Adj) {
                        Vertex v = edge.otherEnd(u);
                        if (!v.seen && edge.Weight < v.d) {
                                v.d = edge.Weight;
                                v.parent = u;
                                nodes.decreaseKey(v); // update priority
                        }
                }
        }
        return wmst;
}


/**
 * For testing purposes
 */
public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Graph g = Graph.readGraph(in, false);

        Timer timer = new Timer();

        // test for Prim method using BinaryHeap
        timer.start();
        int w1 = PrimMST(g);
        timer.end();
        System.out.println("Version 1 of Prim's Algorithm: ");
        System.out.println("Weight of minimum spanning tree: " + w1);
        System.out.println(timer);

        System.out.println();

        // test for Prim method using IndexedHeap
        timer.start();
        int w2 = Prim(g);
        timer.end();
        System.out.println("Version 2 of Prim's Algorithm: ");
        System.out.println("Weight of minimum spanning tree: " + w2);
        System.out.println(timer);

}
}
