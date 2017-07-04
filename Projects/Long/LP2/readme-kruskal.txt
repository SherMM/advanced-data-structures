/******************************************************************************
 *  LP2-Prim
 *
 * to run:
 * javac Kruskal.java
 * java Kruskal < testcase
 *  Group - G16 - Ian Laurain & Vasudev Ravindran
 ******************************************************************************/

Overview:

Implements kruskal's algorithm for finding the MST of an undirected graph

Explanation:

Finds the MST of an undirected graph by continually performing set unions
of vertex sets over and over again. Each vertex set maintains a representative
vertex that signals whether a vertex has membership or not, and should be merged. Although
this implementation doesn't utilize it, a union find implementation with path
compression could considerably improve the running time of this algorithm. Without
these improvements, Kruskal is exptected to run int O(ElogE) time, where E is the
number of edges in the undirected graph.

Testing:

a Kruskal < TestCases/prim0.txt
Running Kruskal's MST algorithm
MST Weight: 181
Time: 3 msec.
Memory: 1 MB / 125 MB.


a Kruskal < TestCases/prim1.txt
Running Kruskal's MST algorithm
MST Weight: 84950
Time: 7 msec.
Memory: 1 MB / 125 MB.


a Kruskal < TestCases/prim2.txt
Running Kruskal's MST algorithm
MST Weight: 110419
Time: 11 msec.
Memory: 2 MB / 125 MB.

a Kruskal < TestCases/prim3.txt
Running Kruskal's MST algorithm
MST Weight: 153534
Time: 23 msec.
Memory: 3 MB / 125 MB.

a Kruskal < TestCases/sp0pq-big.txt
Running Kruskal's MST algorithm
MST Weight: 9999
Time: 11659 msec.
Memory: 844 MB / 1172 MB.


As can be seen, Kruskal doesn't perform as well on the larger data set
as the indexed heap version of prim's algorithm. It performs about
2 seconds slower than the runtime of the BinaryHeap version of Prim's
algorithm as well. However, it's implementation is quite concise and clean.
