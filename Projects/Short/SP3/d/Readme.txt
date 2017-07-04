Problem statement:
------------------

Implement generics version of the selection problem (k largest elements of an unsorted array). Compare the performance of the O(n) algorithm with the heap-based algorithm discussed in class for the external version of the problem.
public static<T extends Comparable<? super T>> int select(T[] arr, int p, int r, int k)
// Find the k largest elements of arr[p..r].  Returns index q.
// The k largest elements are found in arr[q..r].

Solution:
----------

The following class has been implemented for solving the above problem.

Selection.java

Input: 
------

Input should be in the following format as command line argument:

For running External version, the command line arguments should be:

External n k

For running Internal version, the command line arguments should be:

Internal n k

For the given value n, the program generates n random numbers in an array as input for the algorithm.

Sample Output: 
--------------

(for n=1000000, k=100, External Version)

Execution Time : 0.940914481 Seconds

Results:
--------

n	        k	               Execution Time (Seconds)	
		                 External version	Internal Version
10000000	100	             0.50266808    	   0.089390222
10000000	1000	         0.725117069	   0.085439627
10000000	10000	         0.940914481	   0.065962115
10000000	100000	         1.283008741	   0.094239699
10000000	1000000	         1.468121562	   0.068322619
10000000	10000000	     0.122357289	   0.098806231


From the above test results, we can see that external version's execution time increase gradually while that of internal versions' remains the same 
per 10 fold increase of K values.

