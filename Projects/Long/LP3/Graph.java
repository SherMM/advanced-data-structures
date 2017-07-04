/**
 * Class to represent a graph
 *
 * Written by Professor Balaji with some additions by G16
 */

import java.util.*;

class Graph implements Iterable<Vertex> {
public List<Vertex> verts;     // array of vertices
public Set<Edge> edgeSet;
public List<Edge> cycle; // for storing negative or zero-weight cycles
public int numNodes;     // number of verices in the graph
public int numEdges;
public boolean directed;

/**
 * Constructor for Graph
 *
 * @param size : int - number of vertices
 * @param edges : int - number of edges
 */
Graph(int size, int edges) {
        cycle = new ArrayList<Edge>();
        directed = false;
        numNodes = size;
        numEdges = edges;
        edgeSet = new HashSet<Edge>();
        verts = new ArrayList<>(size + 1);
        verts.add(0, null);
        // create an array of Vertex objects
        for (int i = 1; i <= size; i++)
                verts.add(i, new Vertex(i));
}

Graph(int size) {
  cycle = new ArrayList<Edge>();
  directed = false;
  numNodes = size;
  numEdges = 0;
  edgeSet = new HashSet<Edge>();
  verts = new ArrayList<>(size + 1);
  verts.add(0, null);
  // create an array of Vertex objects
  for (int i = 1; i <= size; i++)
          verts.add(i, new Vertex(i));
}

/**
 * Makes and returns a copy of the graph
 *
 */
public Graph copy() {
        Graph g = new Graph(this.numNodes, this.numNodes);
        for (Vertex v : this) {
                for (Edge e : v.Adj) {
                        int from = e.From.name;
                        int to = e.To.name;
                        int w = e.Weight;
                        if (!g.directed) {
                            g.addEdge(from, to, w);
                        } else {
                            g.addDirectedEdge(from, to, w);
                        }
                }
        }
        return g;
}

/**
 * Method to add an edge to the graph
 *
 * @param a
 *            : int - one end of edge
 * @param b
 *            : int - other end of edge
 * @param weight
 *            : int - the weight of the edge
 */
void addEdge(int a, int b, int weight) {
        Vertex u = verts.get(a);
        Vertex v = verts.get(b);
        Edge e = new Edge(u, v, weight);
        u.Adj.add(e);
        v.Adj.add(e);
        this.edgeSet.add(e);
}

/**
 * Method to add an arc (directed edge) to the graph
 *
 * @param a
 *            : int - the head of the arc
 * @param b
 *            : int - the tail of the arc
 * @param weight
 *            : int - the weight of the arc
 */
void addDirectedEdge(int a, int b, int weight) {
        Vertex head = verts.get(a);
        Vertex tail = verts.get(b);
        Edge e = new Edge(head, tail, weight);
        head.Adj.add(e);
        tail.revAdj.add(e);
        this.edgeSet.add(e);
}

/**
 * Returns the number of edges in the graph
 *
 * @return numEdges : the number of edges in the graph
 */
public int getNumEdges() {
        return this.numEdges;
}

/**
 * Returns the number of nodes/vertexes in the graph
 *
 * @return numNodes : the number of nodes in the graph
 */
public int getNumNodes() {
        return this.numNodes;
}

/**
 * Method to create an instance of VertexIterator
 */
public Iterator<Vertex> iterator() {
        return new VertexIterator();
}

/**
 * A Custom Iterator Class for iterating through the vertices in a graph
 *
 *
 * @param <Vertex>
 */
private class VertexIterator implements Iterator<Vertex> {
private Iterator<Vertex> it;
/**
 * Constructor for VertexIterator
 *
 * @param v
 *            : Array of vertices
 * @param n
 *            : int - Size of the graph
 */
private VertexIterator() {
        it = verts.iterator();
        it.next(); // Index 0 is not used.  Skip it.
}

/**
 * Method to check if there is any vertex left in the iteration
 * Overrides the default hasNext() method of Iterator Class
 */
public boolean hasNext() {
        return it.hasNext();
}

/**
 * Method to return the next Vertex object in the iteration
 * Overrides the default next() method of Iterator Class
 */
public Vertex next() {
        return it.next();
}

/**
 * Throws an error if a vertex is attempted to be removed
 */
public void remove() {
        throw new UnsupportedOperationException();
}
}

public void updateCycle(Vertex u) {

	Vertex vcurr = u;
    Vertex source = vcurr;

    do {
            this.cycle.add(vcurr.parent);
            vcurr = vcurr.parent.otherEnd(vcurr);
    } while (vcurr != source);
}

public static Graph readGraph(Scanner in, boolean directed) {
        // read the graph related parameters
        int n = in.nextInt(); // number of vertices in the graph
        int m = in.nextInt(); // number of edges in the graph

        // create a graph instance
        Graph g = new Graph(n, m);
        for (int i = 0; i < m; i++) {
                int u = in.nextInt();
                int v = in.nextInt();
                int w = in.nextInt();
                if(directed) {
                        g.directed = true;
                        g.addDirectedEdge(u, v, w);
                } else {
                        g.addEdge(u, v, w);
                }
        }
        in.close();
        return g;
}
}
