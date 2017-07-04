/**
 * Class to represent a vertex of a graph
 *
 * Written by Professor Balaji with some additions by G16
 *
 */

import java.util.*;

public class Vertex {
public int group; // for storing the component a vertex is a member of
public int inDegree; // # of incoming edges of a vertex
public int name;     // name of the vertex
public boolean seen;     // flag to check if the vertex has already been visited
public Vertex parent;     // parent of the vertex
public int distance;     // distance to the vertex from the source vertex
public List<Edge> Adj, revAdj;     // adjacency list; use LinkedList or ArrayList

/**
 * Constructor for the vertex
 *
 * @param n
 *            : int - name of the vertex
 */
Vertex(int n) {
        inDegree = 0;
        name = n;
        seen = false;
        parent = null;
        Adj = new ArrayList<Edge>();
        revAdj = new ArrayList<Edge>(); /* only for directed graphs */
}

/**
 * Method to represent a vertex by its name
 */
public String toString() {
        return Integer.toString(name);
}
}
