import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Driver program for LP3 - Level One
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class LP3DriverLevelOne {

// needed to check so INF is printed out for vertexes that weren't updated
static final int Infinity = Integer.MAX_VALUE;

/**
 * Returns true if all edges have the same positive weight, otherwise
 * returns false. Used for determining if BFS shortest paths algorithm
 * should be used.
 *
 * @param g : a directed graph
 *
 * @return boolean
 */
public static boolean allEdgesSameWeight(Graph g) {
        Integer edgeWeight = null;
        for (Edge edge : g.edgeSet) {
                if (edgeWeight == null) {
                        edgeWeight = edge.Weight;
                } else {
                        if (edge.Weight != edgeWeight) {
                                return false;
                        }
                }
        }
        return true;
}

/**
 * Returns true if the parameter digraph is acyclic, otherwise
 * returns false. Used for determining if DAG shortest paths
 * algorithm should be used.
 *
 * @param g : a directed graph
 *
 * @return boolean
 */
public static boolean isDAG(Graph g) {
        return !HasDirectedCycle.hasCycle(g);
}

/**
 * Returns true if all edges in the digraph have positive weights, otherwise
 * returns false. Used to determine if Dijkstra's shortest paths algorithm
 * should be used.
 *
 * @param g : a directed graph
 *
 * @return boolean
 */
public static boolean allEdgesHavePositiveWeights(Graph g) {
        for (Edge edge : g.edgeSet) {
                if (edge.Weight < 0) {
                        return false;
                }
        }
        return true;
}

/**
 * Prints out the results of a particular shortest path search algorithm
 * according to the LP3 problem statement.
 *
 * @param g : a directed graph
 * @param algo : the algorithm that was used to find shortest paths in digraph
 *
 */
public static void printResults(Graph g, String algo) {
        // will only print out edges |V| <= 100
        boolean numNodesLessThan = (g.numNodes <= 100) ? true : false;
        int total = 0; // weight of all shortest paths from source to all reachable nodes
        for (Vertex v : g) {
                if (v.d != Infinity) {
                        total += v.d;
                }
        }
        System.out.println(algo + " " + total);

        // print out edges
        if (numNodesLessThan) {
                for (Vertex v : g) {
                        // handle cases where vertex wasn't updated (==infinity)
                        String cost;
                        if (v.d != Infinity) {
                                cost = Integer.toString(v.d);
                        } else {
                                cost = "INF";
                        }

                        // handle cases where vertex has no parten (source or unreachable)
                        String pred;
                        if (v.parent != null) {
                                pred = Integer.toString(v.parent.otherEnd(v).name);
                        } else {
                                pred = "-";
                        }

                        System.out.println(v.name + " " + cost + " " + pred);
                }
        }
        System.out.println();
}

public static List<Edge> findShortestPaths(Graph g, String algo) {
  List<Edge> paths;
  if (algo == "BFS") {
          // do a BFS search on digraph since all edges have same Weight
          paths = BFSPaths.findBFSPaths(g);
  } else if (algo == "DAG") {
          // run DAG shortest paths algorithm
          paths = DAGShortestPaths.findDAGPaths(g);
  } else if (algo == "DIJ") {
          // run Dijkstra's algorithm
          paths = Dijkstra.findDijkstraPaths(g);
  } else {
          // run Bellman-Ford algorithm
          paths = BellmanFord.findBellmanPaths(g);
  }
  return paths;
}

public static String determineSPAlgorithm(Graph g) {
  String algo;
  if (allEdgesSameWeight(g)) {
          // do a BFS search on digraph since all edges have same Weight
          algo = "BFS";
  } else if (isDAG(g)) {
          // run DAG shortest paths algorithm
          algo = "DAG";
  } else if (allEdgesHavePositiveWeights(g)) {
          // run Dijkstra's algorithm
          algo = "DIJ";
  } else {
          // run Bellman-Ford algorithm
          algo = "B-F";
  }
  return algo;
}

/**
 * For testing purposes
 * @throws FileNotFoundException 
 */
public static void main(String[] args) throws FileNotFoundException {

        Graph g = Graph.readGraph(args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(System.in), true);

        String algo = determineSPAlgorithm(g);
        List<Edge> paths = findShortestPaths(g, algo);

        // if path == null, there is a negative cycle and none of the algorithms are applicable
        if (paths == null) {
                System.out.println("Unable to solve problem. Graph has a negative cycle");
        } else {
                printResults(g, algo);
        }
}
}
