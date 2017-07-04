/******************************************************************************
 *  To Compile:  javac QuickSortStack.java
 *  To Execute:  java QuickSortStack
 *  Dependencies: ArrayDeque, Random, ArrayList, List, Collection
 *
 *  Implements methods to sort an array/list with the quicksort algorithm
 *  by simulating the recursive behavior of quicksort with a stack
 *
 ******************************************************************************/

Overview:
This program implements methods (quicksort and partition) that sort a list
with the quicksort algorithm by mimicing its recursive behavior with a
stack.

For testing, a list of random numbers is generated for each run of the program
and the list is then sorted. This was done, rather that use input files, to keep
grading simpler and submission size smaller. If you so desire, the length
of the list (and thus number of elements in that list) can be decreased or increased
simply by change the value for the variable n in the main method of the class.


Explanation:
Quicksort uses recursion to sort a list by continually partitioning sublists of a
list around pivot elements (the index for such can be randomly chosen or also
chosen by using the median of the middle, first, and last elements in a sublist).
For this program we wanted to simulate this recursion with a stack data structure.
To do this, we simply have to see what is getting changed with the recursive calls
to quicksort. In the textbook version of quicksort using recursion, after a pivot
index is chosen and the list is partitioned around it, we make two recursive calls in
the quicksort method on the lower and upper halves of the list (excluding the pivot
element). What changes during these recursive calls, are the lower and upper indexes
for these left and right sublists of the list, and the pivot index they are
divided around. So, we add the lower and upper indexes for the left and right
sublist to the stack, pop them off as we iterate, choose a new pivot, and then add
new lower and upper bounds of left and right sublists to the stack based on this new
pivot index. Thus, we are able to sort the list using a stack.  
