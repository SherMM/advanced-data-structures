/******************************************************************************
 *  To Compile:  javac MergeSort.java or DualQuickSort.java
 *  To Execute:  java MergeSort or java DualQuickSort
 *  Dependencies: Arrays, Random, Timer.java, Shuffle.java
 *
 *  Implements best versions of MergeSort and QuickSort from class
 *  and compare their performance.
 *
 *  Group - G16
 ******************************************************************************/

Overview:

Implementations of mergesort using optimizations discussed in class, and dual pivot
version of quicksort.

Explanation:

Mergesort can and quicksort both can be improved by using insertion sort
on smaller subarrays. Mergesort can be specifically improved by intelligently
copying elements from auxiliary array and array being sorting, instead of at
each call to merge. Dual pivot quicksort improves quicksort performance on
arrays where standard quicksort performance isn't so good (i.e. few unqiue keys,
already sorted, etc.)


Testing:

MergeSort:

Sorting a random array of length 4194304
Time: 1410 msec.
Memory: 107 MB / 222 MB.
Array is sorted: true

Sorting a unique array of length 4194304
Time: 1483 msec.
Memory: 119 MB / 323 MB.
Array is sorted: true

Sorting an array (with duplicates) of length 4194304
Time: 1559 msec.
Memory: 185 MB / 323 MB.
Array is sorted: true

Dual QuickSort:

Sorting a random array of length 4194304
Time: 1071 msec.
Memory: 100 MB / 223 MB.
Array is sorted: true

Sorting a unique array of length 4194304
Time: 1099 msec.
Memory: 110 MB / 318 MB.
Array is sorted: true

Sorting an array (with duplicates) of length 4194304
Time: 897 msec.
Memory: 179 MB / 318 MB.
Array is sorted: true

As can be seen from the empirical data, DualQuickSort outperforms
MergeSort on all three types of tests. 
