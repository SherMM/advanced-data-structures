/******************************************************************************
 *  To Compile:  javac BST.java
 *  To Execute:  java BST < testcase.txt
 *  Dependencies: java.util.*, BTreePrinter.java (for visualizing and debugging)
 *
 *  -----------------------------------NOTE-------------------------------------
 *  BTreePrinter.java wasn't written by the students. The code
 *  and source link were obtained on the elearning website class forum for 6301.
 *  For further details about this code and its source, please see
 *  BTreePrinter.java file.
 *
 *  Also, test1.txt was obtained from elearning website from a post by
 *  fellow student Himanshu Parashar.
 *
 *  -----------------------------------NOTE-------------------------------------
 *
 *  Implements algorithm to replace removed node (when two children) with left
 *  subtree max, instead of right subtree min.
 *
 *  Group - G16 - Ian Laurain & Vasudev Ravindran
 ******************************************************************************/

 Overview:

 Implements algorithm to replace removed node (when two children) with left
 subtree max, instead of right subtree min. Actually the code will, using a
 boolean instance variable for the BST class, alternate between replacing the
 removed node with the right subtree min or the left subtree max.

 Explanation:

 When a node that has two children is a removed from a BST, we have two options
 to replace it. We can either replace it with the left subtree max item, or the
 right subtree min item. This code seeks to show and implement this behavior,
 alternating between each method to prove that no matter which method is chosen
 the output BST is still a correct BST.

 Testing:

 after running java BST < test1.txt:

 input: 4 2 1 3 5 8 7 9 10 -2 -8

 output (using BTreePrinter.java):

 4
/ \
/   \
/     \
/       \
/         \
/           \
/             \
/               \
2               5
/ \               \
/   \               \
/     \               \
/       \               \
1       3               8
/                       / \
/                       /   \
-2                       7   9
/                             \
-8                             10 

Right min removal
4
/ \
/   \
/     \
/       \
/         \
/           \
/             \
/               \
3               5
/                 \
/                   \
/                     \
/                       \
1                       8
/                       / \
/                       /   \
-2                       7   9
/                             \
-8                             10

Left max removal
4
/ \
/   \
/     \
/       \
/         \
/           \
/             \
/               \
3               5
/                 \
/                   \
/                     \
/                       \
1                       7
/                         \
/                           \
-2                           9
/                             \
-8                             10
