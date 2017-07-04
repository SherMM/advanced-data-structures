/******************************************************************************
 *  LP5
 *
 * to compile :
 * javac MaximumCardinalityMatching.java
 * javac LP5Lev1.java
 * (or) just compile the driver file, which should auto-compile the 
 * MaximumCardinalityMatching.java file
 * 
 * to run:
 * java LP5Lev1 < test-case-file
 * Group - G16 - Ian Laurain & Vasudev Ravindran
 ******************************************************************************/

Overview:

Implemented algorithms to find the maximum cardinality matching of bipartite graphs. 

Explanation:

Level-1 of this program implemented the algorithm to find the maximum cardinality matching of an unweighted bipartite graph. The implementation closely followed the pseudocode explained in class, where BFS is used to classify nodes as outer or inner nodes, and then proceed to process augmenting paths inn the graphs to increase the maximum matching size.

Testing:

Level-1 results:

bip1.txt:

35
Execution time : 0.012960662 seconds

bip2.txt:

994
Execution time : 0.023447522 seconds

bip3.txt

9924
Execution time : 0.168281586 seconds

bip4.txt

99238
Execution time : 1.034985949 seconds
