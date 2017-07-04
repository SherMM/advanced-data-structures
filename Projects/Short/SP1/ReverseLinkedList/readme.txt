/******************************************************************************
 *  To Compile:  javac ReverseLinkedList.java
 *  To Execute:  java ReverseLinkedList
 *  Dependencies: ArrayDeque, Random
 *
 *  Implements methods to reverse a sinlgy-linked list iteratively
 *  and recursively
 *
 ******************************************************************************/

Overview:
This program implements methods to reverse a singly-linked list iteratively and
recursively. These reversing operations mutate the order of the original linked
list. This program also implements methods to print the elements of a
singly-linked list in reverse order. These methods do not mutate the original
order of the linked list.

Instead of using input files, a random list of n numbers is generated using the
Random Java library. This was done to keep project submission size down, and also
make it easier for grading purposes.

Explanation:
Reversing a singly-linked list iteratively just involves reversing next node
pointers as we traverse the list in a single pass. To do this, we just need to
maintain three holding variables that keep track of the current, previous, and
next nodes. We continue reversing these pointers until we have reaching the end
of the list (when the current node is node).

To reverse recursively, we make our base case the point where the end of the list
is reached. We also need to watch for the position when we are at the tail node.
Here, we can switch it's place with the head node. When returning from a recursive
call, we switch the pointers of two adjacent nodes.

To print iteratively, we traverse the list, adding each node element to a stack.
We think pop and print items from the stack until it is empty

To print recursively, we make our base case the point where the current node is
null and return. Once returned from a recursive call, we print the current node's
element. We continue is this fashion recursively.
