SP0 - Ian Laurain - icl140030
CS6301

To Compile:

1. javac Search.java (SortingTest.java uses the timer method of this class)
2. javac MergeSort.java
3. javac SortingTest.java

To Run:

java SortingTest

Caveat:

I chose to just generate a large list of random numbers from within my
program to use in testing the PQ and MergeSort runtimes, rather than
use stdin. I felt this was more straightforward for testing.

Explanation:

This short project wanted the student to implement a version of mergesort
in java that uses generics. Once this implementation was complete, the
student was to test it against the java library version of PriorityQueue and
compare the runtimes of each method. Over several tests, the version of
adding all elements in a list into a PQ and popping them off, seem to beat
mergesort in runtime speed as well as memory usage. Some of the results from
running the testing file, are pasted below for your analysis.


Results:

1.)
Start removing items from PQ
Time: 582 msec.
Memory: 25 MB / 125 MB.

Starting mergesort
Time: 754 msec.
Memory: 71 MB / 369 MB.

2.)
Start removing items from PQ
Time: 582 msec.
Memory: 25 MB / 125 MB.

Starting mergesort
Time: 757 msec.
Memory: 70 MB / 369 MB.

3.)
Start removing items from PQ
Time: 593 msec.
Memory: 25 MB / 125 MB.

Starting mergesort
Time: 753 msec.
Memory: 68 MB / 369 MB

4.)

Start removing items from PQ
Time: 583 msec.
Memory: 25 MB / 125 MB.

Starting mergesort
Time: 752 msec.
Memory: 69 MB / 368 MB.

5.)
Start removing items from PQ
Time: 578 msec.
Memory: 25 MB / 125 MB.

Starting mergesort
Time: 747 msec.
Memory: 69 MB / 371 MB.
