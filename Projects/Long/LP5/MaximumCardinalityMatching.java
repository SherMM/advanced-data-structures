
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * This class implements the algorithm for finding maximum matchings in 
 * bipartite graphs. 
 *
 * @author G16: Ian Laurain, Vasudev Ravindran 
 */
public class MaximumCardinalityMatching implements Iterable<Edge> {

	public Integer errorStatus; // for displaying if graph is not bipartite
	public Integer msize; // maximum matching value
	public Graph g; // input graph
	public MaximumCardinalityMatching(Graph g) {

		this.g = g;
		this.msize = 0;
		this.errorStatus = 0;

		if (checkIfBipartiteGraph()) {
			// find maximum matching
			bipartiteMatching();
		} else {
			// graph isn't bipartite
			this.errorStatus = 100; 
		}
	}

	/**
     * Runs BFS on a graph to check if the input graph is bipartite
     *
     * @return boolean value : returns true if bipartite, otherwise false
	 */
	private boolean checkIfBipartiteGraph() {

		for (Vertex u : g) {
			u.seen = false;
			u.parent = null;
			u.distance = Integer.MAX_VALUE;
		}

		Queue<Vertex> Q = new LinkedList<>();

		// Run BFS on every component

		for (Vertex src : g) {

			if (!src.seen) {


				src.distance = 0;
				Q.add(src);
				src.seen = true;

				while (!Q.isEmpty()) {

					Vertex u = Q.remove();

					for (Edge e : u.Adj) {

						Vertex v = e.otherEnd(u);

						if (!v.seen) {

							v.seen = true;
							v.parent = u;
							v.distance = u.distance + 1;
							Q.add(v);
						} else {
							if (u.distance == v.distance) {

								return false;
							}
						}
					}
				}
			}
		}

		return true;
	}

	/**
     * Finds the maximum bipartite matching in a graph
     * Updates private msize variable which stores the maximum matching value
	 */
	private void bipartiteMatching() {

		Vertex v;
		// queues to handle outer notes and augmenting path logic
		Queue<Vertex> q = new LinkedList<Vertex>();
		Queue<Vertex> roots = new LinkedList<Vertex>();
		boolean loop = true;

		for (Vertex u : g) {

			u.mate = null;
		}

		for (Vertex u : g) {

			if (u.mate == null) {

				for (Edge e : u.Adj) {

					v = e.otherEnd(u);

					if (v.mate == null) {

						u.mate = v;
						v.mate = u;
						msize++;
						break;
					}
				}
			}
		}


		// find augmenting path, increase size of maximum matching
		while (loop) {

			// initialize the queue of vertices for outer nodes
			roots.clear();

			for (Vertex u : g) {

				u.seen = false;
				u.parent = null;

				if (u.mate == null && u.distance % 2 == 0) {
					// has no mate and is outer node
					u.seen = true;
					roots.add(u);
				}
			}

			// control variable to return to outer while loop for next iteration
			loop = false;

			// while queue isn't empty and augmenting paths have been found
			while (!roots.isEmpty()) {

				q.clear();
				q.add(roots.remove());

				while (!q.isEmpty()) {

					Vertex u = q.poll();

					for (Edge e : u.Adj) {

						v = e.otherEnd(u);

						if (!v.seen) {

							v.parent = u;
							v.seen = true;

							if (v.mate == null) {
								// process augmenting path and return to outer while loop
								processAugPath(v);
								loop = true;
								q.clear();
								break;
							} else {

								Vertex x = v.mate;
								x.seen = true;
								x.parent = v;
								q.add(x);
							}
						}
					}
				}
			}
		}
	}

	/**
     * Helper function to increase size of matching using an augmenting path
	 *
	 * @param u : vertex to start processing augmenting path from
	 */
	private void processAugPath(Vertex u) {

		Vertex p =  u.parent;
		Vertex x =  p.parent;


		u.mate = p;
		p.mate = u;

		while (x != null) {

			Vertex nmx = x.parent;
			Vertex y = nmx.parent;

			if (nmx != null) {

				x.mate = nmx;
				nmx.mate = x;
			}

			x = y;
		}

		msize++;
	}


	/**
     * Iterator to process edges
	 */
	@Override
	public Iterator<Edge> iterator() {

		LinkedList<Edge> edges = new LinkedList<Edge>();

		for (Vertex u : g) {

			u.seen = false;
		}

		for (Vertex u : g) {

			u.seen = true;

			if (u.mate != null) {

				for (Edge e : u.Adj) {

					if (u.mate == e.otherEnd(u)) {

						edges.add(e);
						e.otherEnd(u).seen = true;
					}
				}
			}
		}

		return edges.iterator();
	}

	/**
     * Returns maximum matching value
     *
     * @return integer value : maximum matching size
	 */
	public Integer size() {

		return msize;
	}

	/**
     * For testing purposes only
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Graph g = Graph.readGraph(in, false);
		MaximumCardinalityMatching max = new MaximumCardinalityMatching(g);
		System.out.println(max.size());
	}
}
