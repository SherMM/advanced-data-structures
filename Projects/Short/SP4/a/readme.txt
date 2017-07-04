/******************************************************************************
 *  To Compile:  javac BST.java
 *  To Execute:  java BST < testcase.txt
 *  Dependencies: java.util.*
 *
 *  Implements level order traversal of a binary search tree. Accomplishes
 *  this with the use of a queue data structure.
 *
 *  Group - G16 - Ian Laurain & Vasudev Ravindran
 ******************************************************************************/

 Overview:

 Implements a level order traversal algorithm for a binary search tree. Relies
 on similar usage for Breadth First Search, which (in a non-recursive version)
 can utilize a queue to give the correct order of traversal.

 Explanation:

The algorithm enqueues each newly encountered node in each (left and right)
subtree onto the queue. Since queues exhibit FIFO behavior, as each element
is dequeued from the queue, the level order traversal of the BST is the output.
We take care not to enqueue null leaves in the BST, as this would output the
improper order or cause an error.

 Testing:

While loop in main() method stops iteration when encountering zero, so these
(zeroes) aren't expected in final output here. 

test1.txt

input: 25 2 3 16 89 75 23 54 31 11 10 32 4 8 12 7 0

output: 25 2 89 3 75 16 54 11 23 31 10 12 32 4 8 7

test2.txt

input: 5 7 6 8 3 2 4 0

output: 5 3 7 2 4 6
