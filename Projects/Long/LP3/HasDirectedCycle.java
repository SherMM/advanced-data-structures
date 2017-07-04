import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * This implements a method that utilizes depth first search
 * while looking for back edges, in order to determine if there
 * is a cycle in a directed Graph
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class HasDirectedCycle {

/**
 * Returns a boolean if a directed graph has a cycle, and false otherwise
 *
 * @param g : a directed Graph
 *
 * @return a boolean indicating the presence of a cycle
 */
public static boolean hasCycle(Graph g) {
        // will be used to determine if vertex i is in recursive call
        boolean[] curr = new boolean[g.numNodes+1];
        // passed to recursive function; tracks occurence of cycle
        int[] cycle = new int[1]; // cycle[0] == 1 indicates a cycle
        for (Vertex v : g) {
                if (!v.seen) {
                        hasCycle(g, v, curr, cycle);
                }
        }
        /*
           Since we just use this method to perform a check on a graph
           we need to reset the seen vertexes. Akin to copying but more
           efficient since we don't have to loop through vertexes and edges
           as we would in a full graph copy
         */
        for (Vertex v : g) {
                v.seen = false;
        }
        return cycle[0] == 1;
}

/**
 * Traverses a directed graph in DFS manner to find wheter
 * a cycle is present or not
 *
 * @param g : a directed Graph
 * @param v : vertex from where search starts
 * @param curr : boolean array to track which nodes are being recursed on
 * @param cycle : a single integer array passed recursively and set to 1 when cycle found
 *
 */
private static void hasCycle(Graph g, Vertex v, boolean[] curr, int[] cycle) {
        curr[v.name] = true; // vertex is being recursed on
        v.seen = true;
        for (Edge edge : v.Adj) {
                Vertex out = edge.To;
                // vertex being recursed on has a backward edge -> cycle
                if (curr[out.name]) {
                        cycle[0] = 1; // indicates a cycle
                        return;
                } else if (!out.seen) {
                        hasCycle(g, out, curr, cycle);
                }
        }
        // vertex no longer being recursed on
        curr[v.name] = false;
}

public static boolean getCycle(Graph g) {
        // will be used to determine if vertex i is in recursive call
        boolean[] curr = new boolean[g.numNodes+1];
        // passed to recursive function; tracks occurence of cycle
        int[] cycle = new int[1]; // cycle[0] == 1 indicates a cycle

        g.cycle.clear();
        getCycle(g, g.verts.get(1), curr, cycle);
        for (Vertex v : g) {
                v.seen = false;
        }
        if (cycle[0] != 1) {
                g.cycle.clear();
        }
        return cycle[0] == 1;
}


private static void getCycle(Graph g, Vertex v, boolean[] curr, int[] cycle) {
        curr[v.name] = true; // vertex is being recursed on
        v.seen = true;

        for (Edge edge : v.Adj) {
                Vertex out = edge.To;
                // vertex being recursed on has a backward edge -> cycle
<<<<<<< HEAD
                out.parent = v;
=======
                out.parent = edge;
>>>>>>> 9de8fb050ab99eead915a104f8fe5bacaef58f9f
                
                if (curr[out.name]) {
                        cycle[0] = 1; // indicates a cycle
                        // store cycle in graph
                        g.updateCycle(out);
                        return;
                } else if (!out.seen) {
<<<<<<< HEAD

                        cycleVertices.push(out);
                        getCycle(g, out, curr, cycle, cycleVertices);
                        cycleVertices.pop();
=======
                	
                        getCycle(g, out, curr, cycle);
>>>>>>> 9de8fb050ab99eead915a104f8fe5bacaef58f9f
                }
        }
        // vertex no longer being recursed on
        curr[v.name] = false;
}

/**
 * For Testing purposes
 *
 */
public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        if (args.length > 0) {
                File inputFile = new File(args[0]);
                in = new Scanner(inputFile);
        } else {
                in = new Scanner(System.in);
        }

        Graph g = Graph.readGraph(in, true);
        boolean cyc = hasCycle(g);
        System.out.println(cyc);
}
}
