/******************************************************************************
 *  To Compile:  javac MostFrequent.java
 *  To Execute:  java MostFrequent
 *  Dependencies: HashMap, Map, Random, Arrays, Timer.java
 *
 *  Implements a program for find most frequent item in an array
 *
 *  Group - G16 - Vasudev Ravindran & Ian Laurain
 ******************************************************************************/

 Overview:

 Implements a algorithm, with the use of a HashMap, to find the most frequently
 occurring element in an array

 Explanation:

 Putting the items into the HashMap takes O(n) time, where n is the number of
 elements in the array (including duplicates). Find the most frequently occuring
 element in the HashMap takes O(k) time, where k is the number of distinct
 items/keys in  the HashMap. So, the algorithm takes O(n+k) or, more generally,
 O(n) time.

 Using the method where we first sort the Array, then iterate over the array to
 find the most frequent element takes O(nlogn + n) time. This is because sorting
 the array (with Java's Arrays.sort()) takes O(nlogn) time. Then, we have to make
 a single pass over the, now sorted, array to find the most frequent element, which
 takes O(n) time. So, we expect this method to take longer than the method using
 the HashMap.


 Testing:

 Empirical results from testing each method:

 Using HashMap method:
----------------------
Finding most frequent integer out of 10000000 integers
Range of input integers: 0 to 750
Most frequent item: 621
Time: 454 msec.
Memory: 69 MB / 219 MB.
Using sorting method:
----------------------
Finding most frequent integer out of 10000000 integers
Range of input integers: 0 to 750
Most frequent item: 621
Time: 475 msec.
Memory: 69 MB / 219 MB.

Using HashMap method:
----------------------
Finding most frequent integer out of 10000000 integers
Range of input integers: 0 to 100
Most frequent item: 58
Time: 270 msec.
Memory: 69 MB / 158 MB.
Using sorting method:
----------------------
Finding most frequent integer out of 10000000 integers
Range of input integers: 0 to 100
Most frequent item: 58
Time: 395 msec.
Memory: 69 MB / 158 MB.

As can be seen, the HashMap method outperforms the sorting method.
