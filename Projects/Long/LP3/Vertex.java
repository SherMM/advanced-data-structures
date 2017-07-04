/**
 * Class to represent a vertex of a graph
 *
 *
 */

import java.util.*;

public class Vertex implements Index, Comparable<Vertex> {
public int name;     // name of the vertex
public boolean seen;     // flag to check if the vertex has already been visited
<<<<<<< HEAD
public Vertex parent;     // parent of the vertex
public Edge parentEdge;
=======
public Edge parent;     // parent of the vertex
>>>>>>> 9de8fb050ab99eead915a104f8fe5bacaef58f9f
public List<Edge> Adj, revAdj;     // adjacency list; use LinkedList or ArrayList
public int index; // denotes location in PriorityQueue
public int d; //  distance to the vertex from the source vertex
public int count; // for Bellman-Ford algorithm
public int inDegree; // # of incoming edges of a vertex
public int pathCount; // # of paths from source to vertex in Digraph
static final int Infinity = Integer.MAX_VALUE; // initialized value for d


/**
 * Constructor for the vertex
 *
 * @param n
 *            : int - name of the vertex
 */
Vertex(int n) {
        count = 0;
        pathCount = 0;
        inDegree = 0;
        d = Infinity;
        name = n;
        index = 0;
        seen = false;
        parent = null;
        Adj = new ArrayList<Edge>();
        revAdj = new ArrayList<Edge>(); /* only for directed graphs */
}

/**
 * Changes the index of an item to the idx parameters
 *
 * @param idx : the new index for an item
 */
public void putIndex(int idx) {
        this.index = idx;
}

/**
 * Returns the current index for an item
 *
 * @return index : the current index of the item
 */
public int getIndex() {
        return this.index;
}

/**
 * Returns -1 if vertex1 less that vertex2, 1 if greater, and 0 if equal
 *
 * @param that : the vertex to compare the current vertex with
 *
 * @return integer : indicates less-than, greater-than, or equal
 */
public int compareTo(Vertex that) {
        return this.d - that.d;
}

/**
 * Method to represent a vertex by its name
 */
public String toString() {
        return Integer.toString(name);
}
}
