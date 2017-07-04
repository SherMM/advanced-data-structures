/******************************************************************************
 *  To Compile:  javac TopoSort.java
 *  To Execute:  java TopoSort < input.file (or) java TopoSort input.file
 *  Dependencies: Graph.java, Vertex.java, Edge.java, DFS.java,
 *                HasDirectedCycle.java, ArrayDeque, ArrayList, List, Queue,
 *                Deque, Scanner, java.io, java.lang
 *
 *  Implements methods to find a topological sorting of a directed acyclic graph
 *
 *  Group - G16
 ******************************************************************************/


Overview:

This progam implements two different methods for finding a topological sorting
of a DAG.

Explanation:

1st method - keeps queue of all in-degree == 0 vertex and removes them one by one
from digraph and places them into the queue. As each vertex is removed from
the queue, it's adjacent vertexes in-degree values are reduced by 1. This continues
until no more vertexes with in-degree == 0 are left. A check is made upon termination
if the number of vertexes in the ordering is the same as the number of vertexes
in the original graph. If not, then a cycle is present. Null is returned if a
cycle is present.

2nd method - uses recursive Depth First Search to find a topological ordering. Null is
returned if a cycle is present. A cycle is found by using DFS. This cycle check was
separated out into a separate class, for modularity reasons.

Testing:

Executed in main() method of class. Results will look like the following:

Results for sorting by in-degree:
----------------------------------
[1, 2, 6, 13, 14, 15, 16, 8, 3, 5, 9, 17, 12, 4, 10, 7, 11, 18]

Results for sorting by DFS:
----------------------------------
[16, 15, 14, 13, 17, 6, 9, 10, 11, 18, 2, 5, 1, 8, 12, 3, 4, 7]

(OR)

Results for sorting by in-degree:
----------------------------------
The Graph has a cycle

Results for sorting by DFS:
----------------------------------
The Graph has a cycle
