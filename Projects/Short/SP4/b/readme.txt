/******************************************************************************
 *  To Compile:  javac BST.java
 *  To Execute:  java BST
 *  Dependencies: java.util.*, BTreePrinter.java (for visualizing and debugging)
 *
 *  -----------------------------------NOTE-------------------------------------
 *    BTreePrinter.java wasn't written by the students. The code
 *  and source link were obtained on the elearning website class forum for 6301.
 *  For further details about this code and its source, please see
 *  BTreePrinter.java file.
 *  -----------------------------------NOTE-------------------------------------
 *
 *  Implements algorithm to insert elements from a sorted array into BST in such
 *  a way that it creates a balanced BST.
 *
 *  Group - G16 - Ian Laurain & Vasudev Ravindran
 ******************************************************************************/

 Overview:

 Implements an algorithm to insert elements in a sorted array into a BST in such
 a way that a balance BST results.

 Explanation:

Since the array is sorted, we can find the mid element of the array, add it to
the BST, then recursively proceed on the left and right subarrays. For this
problem the students had trouble making a method with generic types that could
immediately add the mid element into the BST, so a rough workaround was used.
Knowing the length of the array and that it was sorted, the student recursively
obtained the indexes of each array's and subarray's midpoint and added it to an
array of indexes. This array was used to determined which index in the original
array of elements of type T should be added in which order.

 Testing:

 to see results of test, run the BST.java file as explained above. Simply:

 java BST

 input: 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17;

 output (using BTreePrinter.java):


               9
              / \
             /   \
            /     \
           /       \
          /         \
         /           \
        /             \
       /               \
       4               13
      / \             / \
     /   \           /   \
    /     \         /     \
   /       \       /       \
   2       6       11       15
  / \     / \     / \     / \
 /   \   /   \   /   \   /   \
 1   3   5   7   10   12   14   16
              \               \
              8               17
