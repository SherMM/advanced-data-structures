// Ver 1.0:  Wed, Feb 3.  Initial description.
// Ver 1.1:  Thu, Feb 11.  Simplified Index interface

import java.util.Comparator;
import java.util.Random;

/**
 * Implements a indexed heap heap.
 *
 * @author G16
 */
public class IndexedHeap<T extends Index> extends BinaryHeap<T> {
/** Build a priority queue with a given array q */
IndexedHeap(T[] q, Comparator<T> comp) {
        super(q, comp);
}

/** Create an empty priority queue of given maximum size */
IndexedHeap(int n, Comparator<T> comp) {
        super(n, comp);
}

/**
 * Moves item up to a higher index in PQ if it violates heap properties
 * changes indexes for items as necessary
 *
 * @param i : the index where item to percolate up is
 */
void percolateUp(int i) {
        super.assign(0, i);
        while (c.compare(super.getItem(i/2), super.getItem(0)) > 0) {
                super.assign(i, i/2);
                super.getItem(i).putIndex(i);
                i = i/2;
        }
        super.assign(i, 0);
        super.getItem(i).putIndex(i);
}

/**
 * Moves item down to a lower index in PQ if it violates heap properties
 * changes indexes for items as necessary
 *
 * @param i : the index where the item to percolate down is
 */
void percolateDown(int i) {

        int k;
        T x = super.getItem(i);
        while (2*i <= size) {
                k = 2*i;
                if (k != super.size() && c.compare(super.getItem(k+1), super.getItem(k)) < 0) {
                        k++;
                }
                if (c.compare(super.getItem(k), x) < 0) {
                        super.assign(i, k);
                        super.getItem(i).putIndex(i);
                } else {
                        break;
                }
                i = k;
        }
        super.setItem(x, i);
        super.getItem(i).putIndex(i);
}

/** restore heap order property after the priority of x has decreased */
public void decreaseKey(T x) {
        percolateUp(x.getIndex());
}

/**
 * For testing purposes
 */
public static void main(String[] args) {

  Comparator<Node> maxh = new Comparator<Node>() {
          public int compare(Node node1, Node node2) {
                  return node2.compareTo(node1);
          }
  };

  int n = 10;
  int min = 0;
  int max = 101;
  Random rand = new Random();
  IndexedHeap<Node> bh = new IndexedHeap<Node>(n, maxh);

  // Test adding elements to empty heap and removing them
  for (int i = 0; i < n; i++) {
          int val = rand.nextInt(max - min + 1);
          Node node = new Node(val, 0);
          bh.add(node);
  }

  // check that indexes are correct
  for (int i = 1; i <= bh.size(); i++) {
        Node curr = bh.getItem(i);
        System.out.println("Value: " + curr.getValue() + " i: " + i + " Index: " + curr.getIndex());
  }

  System.out.println();

  // check items are removed in correct order
  while (!bh.isEmpty()) {
        Node rm = bh.remove();
        int val = rm.getValue();
        int idx = rm.getIndex();
        System.out.println("Value: " + val + " Index: " + idx);
  }

}
}
