import java.util.*;

/**
 *@author G16 - Ian Laurain & Vasudev Ravindran
 */
public class DirectedMST {
	
	public static Set<Edge> dmst(Graph g) {
		Graph copy = g.copy();
		Set<Edge> zeroMST = contract(copy, 1);
		return zeroMST;
	}

	public static Set<Edge> contract(Graph g, int root) {
		Set<Edge> mst = new HashSet<Edge>();

		Graph g0 = initGraph(g, root);

		// run DFS to find if zero-weight MST
		List<Edge> order = dfs(g0.getVertex(1));

		// find all vertexes traversed by dfs
		Set<Vertex> visited = new HashSet<Vertex>();
		for (Edge edge : order) {
			visited.add(edge.From);
			visited.add(edge.To);
		}

		// if num nodes traversed by dfs is same as num nodes
		// in zero-weight graph, we know zero-weight mst is found
		if (visited.size() == g0.size()) {
			mst.addAll(order);
			return mst;
		}

		// get a node not in dfs result
		Vertex z = null;
		for (Vertex v : g0) {
			if (!visited.contains(v)) {
				z = v;
			}
		}

		// get zero-weight cycle tracking back from node z above
		Deque<Edge> cycle = findCycle(z);
		
		shrink(g, cycle);
		return mst;
	}

	public static Graph initGraph(Graph g, int root) {

		List<Edge> zeroEdges = new ArrayList<Edge>();
		int numVerts = 0;

		// alter weights of edges of all non-root verts
		for (Vertex v : g) {
			if (v.name != root) {
				// min-weight incoming edge
				Edge min = v.getMinWeightIncomingEdge();

				// min-weight edge weight
				int minw = min.cost;
				for (Edge inc : v.revAdj) {
					inc.cost -= minw;
					if (inc.cost == 0) {
						if (!inc.From.seen) {
							numVerts++;
							inc.From.seen = true;
						}
						if (!inc.To.seen) {
							numVerts++;
							inc.To.seen = true;
						}
						zeroEdges.add(inc);
					}
				}
			}
		}

		// init zero-weight edge graph
		Graph g0 = new Graph(numVerts, zeroEdges.size());
		for (Edge edge : zeroEdges) {
			Vertex from = edge.From;
			Vertex to = edge.To;
			from.seen = false; // reset seen parameter
			to.seen = false;
			g0.addDirectedEdge(from.name, to.name, edge.cost);
		}

		return g0;
	}

	public static List<Edge> dfs(Vertex start) {
		Set<Vertex> visited = new HashSet<Vertex>();
		List<Edge> order = new ArrayList<Edge>();
		Deque<Vertex> stack = new ArrayDeque<Vertex>();
		stack.push(start);
		while (!stack.isEmpty()) {
			Vertex curr = stack.pop();
			if (!visited.contains(curr)) {
				visited.add(curr);
				for (Edge edge : curr.Adj) {
					order.add(edge);
					stack.push(edge.To);
				}
			}
		}
		return order;
	}

	public static Deque<Edge> findCycle(Vertex z) {
		Set<Vertex> visited = new HashSet<Vertex>();
		Deque<Edge> path = new ArrayDeque<Edge>();
		Vertex curr = z;
		while (!visited.contains(curr)) {
			visited.add(curr);
			// get zero back edge
			Edge edge = curr.revAdj.get(0);
			curr = edge.From;
			path.push(edge);
		}

		// add final edge in cycle
		path.push(curr.revAdj.get(0));

		// back track through path to get edges in cycle
		Deque<Edge> cycle = new ArrayDeque<Edge>();
		while (!path.isEmpty()) {
			Edge edge = path.pop();
			cycle.addFirst(edge);
			if (curr == edge.From) {
				break;
			}
		}
		return cycle;
	}

	public static void shrink(Graph g, Deque<Edge> cycle) {
		// cycle data structure has cyclical edge represented
		// by first and last elements in list. shrink to this node
		int v = cycle.peek().From.name;
		Vertex superv = new Vertex(v);
		superv.cycle = cycle;
		HashMap<Integer, Edge> froms = new HashMap<>();
		HashMap<Integer, Edge> tos = new HashMap();

		// track number of original edges in graph g
		int edges = g.getNumEdges();

		// handle edges in and out of shrunk vertex
		for (Edge edge : cycle) {
			// need to access vertex from original graph
			Vertex orig = g.getVertex(edge.To.name);
			for (Edge from : orig.revAdj) {
				// don't want edges already in cycle
				if (!edge.equals(from)) {
					if (froms.containsKey(from.From.name)) {
						if (from.cost < ((Edge) froms.get(from.From.name)).cost) {
							froms.put(from.From.name, from);
							edges--; //node removed
						}
					} else {
						froms.put(from.From.name, from);
					}
				}
			}
			for (Edge to : edge.From.Adj) {
				// don't want edges already in cycle
				if (!edge.equals(to)) {
					if (tos.containsKey(to.To.name)) {
						if (to.cost < ((Edge) tos.get(to.To.name)).cost) {
							tos.put(to.To.name, to);
							edges--; // node removed
						}
					} else {
						tos.put(to.To.name, to);
					}
				}
			}
		}

		// get new number of nodes
		Set<Vertex> nodes = new HashSet<Vertex>();
		for (Edge edge : cycle) {
			Vertex vf = edge.From;
			Vertex vt = edge.To;
			nodes.add(vf);
			nodes.add(vt);
		}

		int n = nodes.size()-1; // account for shrunken node;

		// clear old rev and adj lists
		superv.Adj.clear();
		superv.revAdj.clear();

		// make min, out and in-going edges Adj and revAjd of supervertex
		for (Map.Entry<Integer, Edge> entry : froms.entrySet()) {
			Edge edge = entry.getValue();
			superv.revAdj.add(edge);
		}

		for (Map.Entry<Integer, Edge> entry : tos.entrySet()) {
			Edge edge = entry.getValue();
			superv.Adj.add(edge);
		}


		//return superv;

	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		// read in graph as digraph
		Graph g = Graph.readGraph(in, true);
		Set<Edge> m = dmst(g);
	}
}
