Problem statement:
------------------

Implement an O(n log k) algorithm for the kth quantile problem (Ex. 9.3-6, p. 223 in Cormen et al's book, 3rd ed.).
public static<T extends Comparable<? super T>> void kthQuantiles(T[] A, T[] result, int k)
// The kth quantiles are returned in the array "result", of size k-1, supplied by the caller.

Solution:
----------

The following class has been implemented for solving the above problem.

KthQuantiles.java

Input:
------

Sample input is an array generated in main function. k is also specified in main function

Sample output:
--------------

If the input array is: (length: 32)

32 31 30 29 28 27 26 25 24 23 22 21 20 19 18 17 16 15 14 13 12 11 10 9 8 7 6 5 4 3 2 1

for k = 16:

the result will be displayed as:
 
Kth Quantile is: 2 4 6 8 10 12 14 16 18 20 22 24 26 28 30 

for k = 8:

the result will be displayed as:

Kth Quantile is: 4 8 12 16 20 24 28 


