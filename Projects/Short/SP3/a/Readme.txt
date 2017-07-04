Problem statement:
------------------

Compare the running times of the O(n) and O(log n) algorithms for computing f_n % p, where f_n is the nth Fibonacci number. 
Write the following functions and compare their running times for large values of n. Use p = 999953.
public static long linearFibonacci(long n, long p);
public static long logFibonacci(long n, long p);

Solution:
----------

The following class has been implemented for solving the above problem.

FibonacciGenerator.java

Input:
------

Input should be in the following format as command line argument:

For running linear version, the command line arguments should be:

Normal n p

For running log version, the command line arguments should be:

Fast n p

Note:  starting sequence of numbers are f(1) = 0 and f(2) = 1;
-----

Sample Output: 
--------------

(for n=1000, p=999953)

666971
Execution Time : 0.000182063 Seconds

Results:
--------

n	           Linear Version		    Log Version	
----------- ----------------------  ----------------------
	        Result	Execution Time	Result	Execution Time
10	            21	 0.000155659	    21	 0.000469736
100	        787163	 0.000161570    787163	 0.000478013
1000	    666971	 0.000199796	666971	 0.000488652
10000	    639797	 0.000406685	639797	 0.000491805
100000	    805918	 0.002323856	805918   0.000504809
1000000	    558273	 0.014131896	558273	 0.000516631
10000000	340244	 0.129390726	340244	 0.000529636
100000000	271744	 1.258012219	271744	 0.000542246
1000000000	944023	12.959811130	944023	 0.000553280
10000000000	 88639 129.077051000	 88639	 0.000583624

From the above test results, we can see that Linear version increases drastically when compared to the logarithmic version whenever n increases 
therey proving the expected time complexity difference between both the versions.

