import java.util.*;

/**
 *
 * @author : rbk & G16 - Ian Laurain & Vasudev Ravindran
 */
public class BST<T extends Comparable<? super T> > {

Entry<T> root;
int size;
boolean removeMethod; // determines which removal method used when node has 2 child

BST() {
        root = null;
        size = 0;
        removeMethod = true;
}

// build a BST from a sorted array
BST(T[] arr) {
        BST<T> bst = new BST<T>();
        int[] indexes = new int[arr.length];
        int[] idx = new int[1];
        // will get order of indexes in arr array to add
        // so that tree will be balanced after all elements are added
        getIndexAddOrder(0, arr.length-1, indexes, idx);
        for (int i = 0; i < indexes.length; i++) {
                bst.add(arr[indexes[i]]);
        }
        root = bst.root;
        size = bst.size;
        removeMethod = true;
}

public static void getIndexAddOrder(int start, int stop, int[] indexes, int[] idx) {
        if ((stop-start) == 1) {
                // 2 elements remaining
                indexes[idx[0]++] = start;
                indexes[idx[0]++] = stop;
                return;
        }

        if ((stop-start) == 0) {
                // 1 element remaining
                indexes[idx[0]++] = start;
                return;
        }

        // get index to split array in two at
        int mid = (start + stop) / 2;
        // mid-index will be a subtree split point in BST
        indexes[idx[0]++] = mid;
        // handle left subtree indexes
        getIndexAddOrder(start, mid-1, indexes, idx);
        // handle right subtree indexes
        getIndexAddOrder(mid+1, stop, indexes, idx);
}

// Find x in subtree rooted at node t.  Returns node where search ends.
Entry<T> find(Entry<T> t, T x) {
        Entry<T> pre = t;
        while(t != null) {
                pre = t;
                int cmp = x.compareTo(t.element);
                if(cmp == 0) {
                        return t;
                } else if(cmp < 0) {
                        t = t.left;
                } else {
                        t = t.right;
                }
        }
        return pre;
}

// Is x contained in tree?
public boolean contains(T x) {
        Entry<T> node = find(root, x);
        return node == null ? false : x.equals(node.element);
}

// Add x to tree.  If tree contains a node with same key, replace element by x.
// Returns true if x is a new element added to tree.
public boolean add(T x) {
        if(size == 0) {
                root = new Entry<>(x, null, null, null);
        } else {
                Entry<T> node = find(root, x);
                int cmp = x.compareTo(node.element);
                if(cmp == 0) {
                        node.element = x;
                        return false;
                }
                Entry<T> newNode = new Entry<>(x, null, null, node);
                if(cmp < 0) {
                        node.left = newNode;
                } else {
                        node.right = newNode;
                }
        }
        size++;
        return true;
}

// Remove x from tree.  Return x if found, otherwise return null
public T remove(T x) {
        T rv = null;
        if(size > 0) {
                Entry<T> node = find(root, x);
                if(x.equals(node.element)) {
                        rv = node.element;
                        remove(node);
                        size--;
                }
        }
        return rv;
}

// Called when node has at most one child.  Returns that child.
Entry<T> oneChild(Entry<T> node) {
        return node.left == null ? node.right : node.left;
}

// Remove a node from tree
void remove(Entry<T> node) {
        if(node.left != null && node.right != null) {
                // alternate between removal methods
                if (removeMethod) {
                        System.out.println("Right min removal");
                        removeTwo(node);
                        removeMethod = !removeMethod;
                } else {
                        System.out.println("Left max removal");
                        removeTwoLeftMax(node);
                        removeMethod = !removeMethod;
                }
        } else {
                removeOne(node);
        }
}

// remove node that has at most one child
void removeOne(Entry<T> node) {
        if(node == root) {
                Entry<T> nc = oneChild(root);
                root = nc;
                root.parent = null;
        } else {
                Entry<T> p = node.parent;
                Entry<T> nc = oneChild(node);
                if(p.left == node) {
                        p.left = nc;
                } else {
                        p.right = nc;
                }
                if(nc != null) nc.parent = p;
        }
}

// remove node that has two children
void removeTwo(Entry<T> node) {
        Entry<T> minRight = node.right;
        while(minRight.left != null) {
                minRight = minRight.left;
        }
        node.element = minRight.element;
        removeOne(minRight);
}

// remove node that has two children
void removeTwoLeftMax(Entry<T> node) {
        Entry<T> maxLeft = node.left;
        while(maxLeft.right != null) {
                maxLeft = maxLeft.right;
        }
        node.element = maxLeft.element;
        removeOne(maxLeft);
}

// Create an array with the elements using in-order traversal of tree
public Comparable[] toArray() {
        Comparable[] arr = new Comparable[size];
        inOrder(root, arr, 0);
        return arr;
}

// Recursive in-order traversal of tree rooted at "node".
// "index" is next element of array to be written.
// Returns index of next entry of arr to be written.
int inOrder(Entry<T> node, Comparable[] arr, int index) {
        if(node != null) {
                index = inOrder(node.left, arr, index);
                arr[index++] = node.element;
                index = inOrder(node.right, arr, index);
        }
        return index;
}

public Comparable[] levelOrder() {
        Comparable[] arr = new Comparable[size];
        levelOrder(root, arr, 0);
        return arr;
}

private int levelOrder(Entry<T> node, Comparable[] arr, int index) {
        Queue<Entry<T> > queue = new ArrayDeque<Entry<T> >();
        queue.add(node);
        while (!queue.isEmpty()) {
                Entry<T> curr = queue.remove();
                arr[index++] = curr.element;
                // don't add null leafs
                if (curr.left != null) {
                        queue.add(curr.left);
                }
                if (curr.right != null) {
                        queue.add(curr.right);
                }
        }
        return index;
}

public void printTree() {
        System.out.print("[" + size + "]");
        printTree(root);
        System.out.println();
}

// Inorder traversal of tree
void printTree(Entry<T> node) {
        if(node != null) {
                printTree(node.left);
                System.out.print(" " + node.element);
                printTree(node.right);
        }
}

public static void main(String[] args) {
        BST<Integer> t = new BST<>();
        Scanner in = new Scanner(System.in);
        while(in.hasNext()) {
          	    int x = in.nextInt();
                t.add(x);
        }
        BTreePrinter.printNode(t.root);
        t.remove(2);
        BTreePrinter.printNode(t.root);
        t.remove(8);
        BTreePrinter.printNode(t.root);

}
}
/*
   Sample input:
   1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

   Output:
   Add 1 : [1] 1
   Add 3 : [2] 1 3
   Add 5 : [3] 1 3 5
   Add 7 : [4] 1 3 5 7
   Add 9 : [5] 1 3 5 7 9
   Add 2 : [6] 1 2 3 5 7 9
   Add 4 : [7] 1 2 3 4 5 7 9
   Add 6 : [8] 1 2 3 4 5 6 7 9
   Add 8 : [9] 1 2 3 4 5 6 7 8 9
   Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
   Remove -3 : [9] 1 2 4 5 6 7 8 9 10
   Remove -6 : [8] 1 2 4 5 7 8 9 10
   Remove -3 : [8] 1 2 4 5 7 8 9 10
   Final: 1 2 4 5 7 8 9 10

   Extending to AVL tree:

    class AVLEntry<T> extends Entry<T> {
   int height;
   AVLEntry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
      super(x,l,r,p);
      height = 0;
   }
    }

   Extending to Red-Black tree:

    private static final boolean RED   = false;
    private static final boolean BLACK = true;

    class RBEntry<T> extends Entry<T> {
   boolean color;
   RBEntry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
      super(x,l,r,p);
      color = RED;
   }
    }

 */
