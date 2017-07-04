/******************************************************************************
 *  To Compile:  javac RemoveDuplicates.java
 *  To Execute:  java RemoveDuplicates
 *  Dependencies: HashSet, Random, Timer.java
 *
 *  Implements a program for moving duplicate items in an array to the front
 *  of the array.
 *
 *  Group - G16 - Vasudev Ravindran & Ian Laurain
 ******************************************************************************/

 Overview:

 Implements a algorithm, with the use of a HashSet, to find the k distinct items
 in an array (i.e. discount duplicates - once item found it is found and duplicates
 are ignored), and move these k items to the front of the array (array[0..k-1]).
 It then returns what the number k is.

 Explanation:

 Putting the items into the HashSet takes O(n) time, where n is the number of
 elements in the array (including duplicates). Adding the items in the
 HashSet to the front of the array takes O(k) time, where k is the number of
 distinct elements found. So, runtime for this algorithm is O(n+k) or, more generally,
 O(n).


 Testing:

 Ran some small testing on the algorithm that can be seen in the main() method of
 the RemoveDuplicates.java file.
