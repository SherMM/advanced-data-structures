import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/**
 * Class that provides functionality of counting the number of shortest paths in
 * a graph. (Level 2).
 *
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 *
 */
public class LP3DriverLevelTwo {

	static final int Infinity = Integer.MAX_VALUE; // initialized value for d

	/**
	 * Finds the number of shortest paths of graph g
	 *
	 *
	 * @param g
	 * @return
	 */

	public static int findNumberOfDAGShortestPaths(Graph g) {

		Graph dag = createDAG(g);

		HasDirectedCycle.getCycle(dag);

		if (dag.cycle.size() > 0) {

			g.cycle.clear();
			g.cycle.addAll(dag.cycle);
			return 0;
		}

		int pathCount = findNumberOfDAGShortestPaths(dag, dag.verts.get(1));

		for (Vertex vertex : g) {

			vertex.pathCount = dag.verts.get(vertex.name).pathCount;
		}

		return pathCount;
	}

	/**
	 * Finds the number of shortest paths of graph g with source vertex to all
	 * other vertices
	 *
	 *
	 * @param g
	 * @return
	 */

	private static int findNumberOfDAGShortestPaths(Graph g, Vertex source) {

		Queue<Vertex> queue = new ArrayDeque<Vertex>();

		source.pathCount++;
		queue.add(source);

		while (!queue.isEmpty()) {
			Vertex curr = queue.remove();
			for (Edge edge : curr.Adj) {
				Vertex to = edge.To;
				to.pathCount++;
				queue.add(to);
			}
		}

		// sum number of shortest paths
		int count = 0;

		for (Vertex v : g) {

			count += v.pathCount;
		}

		return count;
	}

	/**
	 * Creates a DAG subgraph from graph g by including the edges which are part
	 * of some shortest path in single source shortest path algorithm output.
	 *
	 *
	 * @param g
	 * @return
	 */

	public static Graph createDAG(Graph g) {
		Graph dag = new Graph(g.getNumNodes());
		dag.directed = true;
		for (Vertex v : g) {
			for (Edge edge : v.Adj) {
				Vertex to = edge.To;
				if (to.d == v.d + edge.Weight) {
					dag.addDirectedEdge(v.name, to.name, edge.Weight);
				}
			}
		}
		return dag;
	}

	/**
	 * Level 2 driver module
	 *
	 *
	 * @param args
	 * @throws FileNotFoundException
	 */

	public static void main(String[] args) throws FileNotFoundException {

		Graph g = Graph.readGraph(args.length > 0 ? new Scanner(new File(args[0])) : new Scanner(System.in), true);

		String algo = LP3DriverLevelOne.determineSPAlgorithm(g);
		List<Edge> paths = LP3DriverLevelOne.findShortestPaths(g, algo);

		int pathCount = paths != null ? pathCount = findNumberOfDAGShortestPaths(g) : 0;

		if (pathCount == 0 && g.cycle.size() != 0) {

			System.out.println("Non-positive cycle in graph. DAC is not applicable");

			for (Edge edge : g.cycle) {

				System.out.println(edge.From + " " + edge.To + " " + edge.Weight);
			}

		} else {

			System.out.println(pathCount);

			if (g.verts.size() <= 101) {

				for (Vertex vertex : g) {

					System.out.println(
							vertex.name + " " + (vertex.d == Infinity ? "INF" : vertex.d) + " " + vertex.pathCount);
				}
			}
		}
	}
}
