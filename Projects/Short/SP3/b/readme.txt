/******************************************************************************
 *  To Compile:  javac QuickSort.java or QuickSortThreeWay.java
 *  To Execute:  java QuickSort or java QuickSortThreeWay
 *  Dependencies: Arrays, Random, Timer.java, Shuffle.java
 *
 *  Implements standard and 3-way partitioning versions of QuickSort
 *
 *  Group - G16
 ******************************************************************************/

Overview:

Implementations of quicksort using standard and three-way partitioning

Explanation:

Standard quicksort partitioning can lead to poor performance in the
worst case (O(n^2), best casee is O(nlogn)) and it also doesn't always handle well cases where array has
few unique items. Three-way quicksort attempts to solve
this duplicate item issue and we compare the performance below.


Testing:

Empirical testing results:

Standard QuickSort
-------------------------------------------
Sorting a random array of length 4194304
Time: 28407 msec.
Memory: 125 MB / 253 MB.
Array is sorted: true

Sorting a unique array of length 4194304
Time: 1410 msec.
Memory: 208 MB / 310 MB.
Array is sorted: true

Sorting an array (with duplicates) of length 4194304
Time: 7280 msec.
Memory: 229 MB / 370 MB.
Array is sorted: true


3-pivot QuickSort
-------------------------------------------
Sorting a random array of length 4194304
Time: 1057 msec.
Memory: 90 MB / 223 MB.
Array is sorted: true

Sorting a unique array of length 4194304
Time: 4126 msec.
Memory: 109 MB / 337 MB.
Array is sorted: true

Sorting an array (with duplicates) of length 4194304
Time: 1320 msec.
Memory: 186 MB / 343 MB.
Array is sorted: true



As can be seen, 3-pivot quicksort conistently outperforms
standard quicksort that is using Hoare partitioning. Performanc
improvement seems very noticeable when there are few unique
items in the array.
