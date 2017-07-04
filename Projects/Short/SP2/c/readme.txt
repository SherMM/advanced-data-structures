/******************************************************************************
 *  To Compile:  javac SCC.java
 *  To Execute:  java SCC < input.file (or) java TopoSort input.file
 *  Dependencies: Graph.java, Vertex.java, Edge.java, DFS.java,
 *                ArrayDeque, ArrayList, List, Deque, Scanner,
 *                java.io, java.lang
 *
 *  Implements methods to find the strongly connnected components of a directed
 *  graph.
 *
 *  Group - G16
 ******************************************************************************/

Overview:

This program implements the well-known algorithm for finding the strongly
connected components and number of them in a digraph.

Explanation:

First, DFS is performed on the digraph.
Next, DFS is performed on the original graph with its edges reversed and each
vertex is examined in the latest-earliest finishing order found in step 1.
While examining the vertexes of the reverse graph, each vertex is added to a
specific component based on when calls to DFS (labeled componentSearch in this
program) terminate.

Testing:

Executed in main() method of class. Results will look like the following:

Number of Strongly Connected Components: 4

Components and their vertexes:
-------------------------------
Component: 0

1

Component: 1

2 4 5

Component: 2

3 6

Component: 3

7 8 9 10 11 12
