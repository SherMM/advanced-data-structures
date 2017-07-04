/******************************************************************************
 *  SP0-PQ
 *
 *
 *  Group - G16
 ******************************************************************************/

Overview:

Implements binary and indexed heap, as well as two version of Prim's
algorithm for finding the minimum spanning tree in an undirected graph.


Explanation:

The first version, since it stores all edges in a priority queue where order is
based on edge weight, leads to a runtime of O(ElogE), where E is the number of
edges. This is because all edges are initially placed int priority queue and, as
the algorithm progresses, each edges is removed from the PQ and all of it's
adjacent edges must be examined. This continues until the priority queue is empty.

The second versions initially stores all vertexes in a priority queue where order
in the heap is based on each vertexes d attribute, which is the minimum edge weight
that will connect that vertex to another vertex int he minimum spanning tree. Each
vertex is removed from the PQ as the algorithm progresses, and all of the adjacent
edges for this vertex are examined. This leads to a runtime of O(VlogE), which is
significantly better for undirected graphs where the number of vertexes is much
lower than the number of edges. If the number of edges and vertexes are the same, The
runtimes will be similar.

Testing:

From running the following tests, we can see that version 2 of prim's algorithm
(the one that stores vertexes in a PQ rather than edges) outperforms version 1
of prim's algorithm on small and large graphs. This is very obvious for the very
large graph in the final test case. The large performance gain isn't really
obvious until we use it on a very large graph, where version 2 is about 15 times
faster than version 1.

****NOTE****
Only small test cases have been submitted - (sp0pq-big.txt was left out of submission)

prim1.txt

Version 1 of Prim's Algorithm:
Weight of minimum spanning tree: 84950
Time: 4 msec.
Memory: 2 MB / 128 MB.

Version 2 of Prim's Algorithm:
Weight of minimum spanning tree: 84950
Time: 2 msec.
Memory: 2 MB / 128 MB.

prim2.txt

Version 1 of Prim's Algorithm:
Weight of minimum spanning tree: 110419
Time: 6 msec.
Memory: 3 MB / 128 MB.

Version 2 of Prim's Algorithm:
Weight of minimum spanning tree: 110419
Time: 4 msec.
Memory: 3 MB / 128 MB.

prim3.txt

Version 1 of Prim's Algorithm:
Weight of minimum spanning tree: 153534
Time: 8 msec.
Memory: 4 MB / 128 MB.

Version 2 of Prim's Algorithm:
Weight of minimum spanning tree: 153534
Time: 4 msec.
Memory: 4 MB / 128 MB.

sp0pq-big.txt

Version 1 of Prim's Algorithm:
Weight of minimum spanning tree: 9999
Time: 9297 msec.
Memory: 439 MB / 1004 MB.

Version 2 of Prim's Algorithm:
Weight of minimum spanning tree: 9999
Time: 589 msec.
Memory: 439 MB / 1004 MB.
