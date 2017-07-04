/******************************************************************************
 *  To Compile:  javac SP5ProblemADriver.java, javac Combination.java
 *  To Execute:  java SP5ProblemADriver n , java Combination n k
 *  Dependencies: Timer.java
 *
 *  Runs running time tests on the implementations of the Take Two and Heap's
 *  algorithms for finding the permutations of numbers 1 to N. Also contains
 *  output for running tests on the code written for finding combinations
 *
 *  Group - G16 - Ian Laurain & Vasudev Ravindran
 ******************************************************************************/

Overview:

Algorithms for finding combinations and permutations were implemented.
Recursion was relied upon heavily to complete these algorithms. For finding
combinations, only one algorithm was implemented. Three different algorithms
for finding permutations were implemented (Take One, Take Two, & Heap's),
but only the running times for two of them were tests (Take Two & Heap's).

Explanation:

The algorithmic complexity for the combination algorithm written is
O(nCk*time to visit a combination). The algorithmic complexity for the Take One
algorithm is O(n*n!*time to visit a permutation). The algorithmic complexity
for the Take Two algorithm is O(n!*time to visit one permutation). The algorithmic
complexity of heap's algorithm is O(n!*time to visit a permutation), (sources:
https://en.wikipedia.org/wiki/Heap%27s_algorithm,
https://www.cs.princeton.edu/~rs/talks/perms.pdf)

Testing (for permutations):

Results for Take Two Algorithm
------------------------------
For n = 8, runtime = 19 msec
Number of permutations = 5040

For n = 9, runtime = 1 msec
Number of permutations = 40320

For n = 10, runtime = 6 msec
Number of permutations = 362880

For n = 11, runtime = 70 msec
Number of permutations = 3628800

For n = 12, runtime = 725 msec
Number of permutations = 39916800

For n = 13, runtime = 9891 msec
Number of permutations = 479001600

For n = 14, runtime = 115127 msec
Number of permutations = 1932053504

Results for Heap's Algorithm
------------------------------

For n = 8, runtime = 5 msec
Number of permutations = 5040

For n = 9, runtime = 22 msec
Number of permutations = 40320

For n = 10, runtime = 7 msec
Number of permutations = 362880

For n = 11, runtime = 60 msec
Number of permutations = 3628800

For n = 12, runtime = 713 msec
Number of permutations = 39916800

For n = 13, runtime = 8889 msec
Number of permutations = 479001600

For n = 14, runtime = 109724 msec
Number of permutations = 1932053504


Analysis:

As we can see from the runtimes above, the Take Two and Heap's algorithms
seem to perform (at least very nearly) the same. Heap's tends to have
quicker run times for the larger test cases. For n = 13 and n = 14, heap's
outperforms the Take Two algorithm by about 10 seconds and 6 second, respectively.


Results (combinations):

nCk = 6C3

1 2 3
1 2 4
1 3 4
2 3 4
1 2 5
1 3 5
2 3 5
1 4 5
2 4 5
3 4 5
1 2 6
1 3 6
2 3 6
1 4 6
2 4 6
3 4 6
1 5 6
2 5 6
3 5 6
4 5 6
Total combinations: 20

nCk = 8C4

java Combination 8 4
1 2 3 4
1 2 3 5
1 2 4 5
1 3 4 5
2 3 4 5
1 2 3 6
1 2 4 6
1 3 4 6
2 3 4 6
1 2 5 6
1 3 5 6
2 3 5 6
1 4 5 6
2 4 5 6
3 4 5 6
1 2 3 7
1 2 4 7
1 3 4 7
2 3 4 7
1 2 5 7
1 3 5 7
2 3 5 7
1 4 5 7
2 4 5 7
3 4 5 7
1 2 6 7
1 3 6 7
2 3 6 7
1 4 6 7
2 4 6 7
3 4 6 7
1 5 6 7
2 5 6 7
3 5 6 7
4 5 6 7
1 2 3 8
1 2 4 8
1 3 4 8
2 3 4 8
1 2 5 8
1 3 5 8
2 3 5 8
1 4 5 8
2 4 5 8
3 4 5 8
1 2 6 8
1 3 6 8
2 3 6 8
1 4 6 8
2 4 6 8
3 4 6 8
1 5 6 8
2 5 6 8
3 5 6 8
4 5 6 8
1 2 7 8
1 3 7 8
2 3 7 8
1 4 7 8
2 4 7 8
3 4 7 8
1 5 7 8
2 5 7 8
3 5 7 8
4 5 7 8
1 6 7 8
2 6 7 8
3 6 7 8
4 6 7 8
5 6 7 8
Total combinations: 70
