// Ver 1.0:  Wec, Feb 3.  Initial description.

import java.util.Comparator;
import java.util.Random;
import java.util.Arrays;

/**
 * Implements a binary heap.
 *
 * @author G16
 */
public class BinaryHeap<T> implements PQ<T> {
private T[] pq; // the priority queue
Comparator<T> c;
protected int size; // num elements in queue

/** Build a priority queue with a given array q */
BinaryHeap(T[] q, Comparator<T> comp) {
        pq = q;
        c = comp;
        size = q.length-1;
        buildHeap();
}

/** Create an empty priority queue of given maximum size */
@SuppressWarnings("unchecked")
BinaryHeap(int n, Comparator<T> comp) {
        pq = (T[]) new Object[n+1];
        c = comp;
        size = 0;
}
 /**
  * Adds an item to the priority queue
  *
  * @param x : the item to add
  */
public void insert(T x) {
        add(x);
}

/**
 * Removes and returns the minimum item from the pq
 *
 * @return the minimum item in the queue
 */
public T deleteMin() {
        return remove();
}

/**
 * Returns the minium item in the pq but doesn't remove it
 *
 * @return the minium item in the queue
 */
public T min() {
        return peek();
}

/**
 * Adds an item to the pq
 *
 * @param x : the item to add
 */
public void add(T x) {
        // Heap is full
        if (size >= pq.length-1) {
                pq = this.resize();
        }
        pq[++size] = x;
        percolateUp(size);
}

/**
 * Removes and returns the item with highest/lowest priority in pq
 *
 * @return the item with highest/lowest priority in queue
 */
public T remove() {
        T item = pq[1];
        pq[1] = pq[size--];
        percolateDown(1);
        return item;
}

/**
 * Returns the item with highest/lowest priority in the pq
 * but doesn't remove it
 *
 * @return the item with highest/lowest priority in the queue
 */
public T peek() {
        T item = pq[1];
        return item;
}

/**
 * Returns number of items in queue
 *
 * @return the number of items in the queue
 */
public int size() {
        return size;
}


/**
 * Returns true is pq is empty, and false otherwise
 *
 * @return boolean indicating if queue is empty or not
 */
public boolean isEmpty() {
        return size == 0;
}

/**
 * Assigns element at index j to space at index i
 *
 * @param i : index i
 * @param j : index j
 */
public void assign(int i, int j) {
    
	pq[i] = pq[j];
}

/**
 * Assigns element at index j to space at index i
 *
 * @param i : index i
 * @param j : index j
 */
public void assign(int i, T x) {
    
	pq[i] = x;
}

/**
 * Returns item at index i
 *
 * @param i : the index where the item is
 *
 * @return the item at index i
 */
public T getItem(int i) {
        return pq[i];
}

/**
 * Sets space at index i to the item
 *
 * @param item : the item to place at index i
 * @param i : index i
 */
public void setItem(T item, int i) {
        pq[i] = item;
}

/**
 * Moves item up to a higher index in PQ if it violates heap properties
 *
 * @param i : the index where item to percolate up is
 */
void percolateUp(int i) {
        pq[0] = pq[i];
        while (c.compare(pq[i/2], pq[0]) > 0) {
                pq[i] = pq[i/2];
                i = i/2;
        }
        pq[i] = pq[0];
}

/**
 * Moves item down to a lower index in PQ if it violates heap properties
 *
 * @param i : the index where the item to percolate down is
 */
void percolateDown(int i) {

        int k;
        T x = pq[i];
        while (2*i <= size) {
                k = 2*i;
                if (k != size && c.compare(pq[k+1], pq[k]) < 0) {
                        k++;
                }
                if (c.compare(pq[k], x) < 0) {
                        pq[i] = pq[k];
                } else {
                        break;
                }
                i = k;
        }
        pq[i] = x;
}

/**
 * Increases the capacity of the PQ
 *
 * @return an array that is double the size
 */
protected T[] resize() {
        return Arrays.copyOf(pq, pq.length*2);
}


/** Create a heap.  Precondition: none. */
void buildHeap() {
        for (int i = size/2; i > 0; i--) {
                percolateDown(i);
        }
}

/**
 * Prints the items in the PQ to see them visually
 */
public void print() {
        for (int i = 1; i <= size; i++) {
                System.out.println(pq[i]);
        }
}

/* sort array A[1..n].  A[0] is not used.
   Sorted order depends on comparator used to buid heap.
   min heap ==> descending order
   max heap ==> ascending order
 */
public static<T> void heapSort(T[] A, Comparator<T> comp) {
        BinaryHeap<T> heap = new BinaryHeap<T>(A, comp);
        for (int i = heap.size(); i > 0; i--) {
                T x = heap.remove();
                A[i] = x;
        }
}

/**
 * For Testing purposes
 */
public static void main(String[] args) {

        Comparator<Integer> maxh = new Comparator<Integer>() {
                public int compare(Integer num1, Integer num2) {
                        return num2.compareTo(num1);
                }
        };

        Comparator<Integer> minh = new Comparator<Integer>() {
                public int compare(Integer num1, Integer num2) {
                        return num1.compareTo(num2);
                }
        };

        BinaryHeap<Integer> bh = new BinaryHeap<Integer>(5, maxh);

        int min = 0;
        int max = 101;
        Random rand = new Random();

        // Test adding elements to empty heap and removing them
        for (int i = 0; i < 5; i++) {
                bh.add(rand.nextInt(max - min + 1));
        }

        while (!bh.isEmpty()) {
                System.out.println(bh.peek());
                bh.remove();
        }
        System.out.println("PQ is empty");
        System.out.println();

        Integer[] array = new Integer[6];
        for (int i = 1; i < array.length; i++) {
                array[i] = rand.nextInt(max - min + 1);
        }

        // Test buildHeap from array
        BinaryHeap<Integer> bc = new BinaryHeap<Integer>(array, maxh);
        while (!bc.isEmpty()) {
                System.out.println(bc.peek());
                bc.remove();
        }
        System.out.println("PQ is empty");
        System.out.println();

        // Test heapsort
        int n = 26;
        Integer[] nums = new Integer[n];

        for (int i = 1; i < nums.length; i++) {
                nums[i] = rand.nextInt(max - min + 1);
        }

        for (int i = 1; i < nums.length; i++) {
                System.out.print(nums[i] + " ");
        }
        System.out.println();

        heapSort(nums, maxh);

        for (int i = 1; i < nums.length; i++) {
                System.out.print(nums[i] + " ");
        }
        System.out.println();
        System.out.println();

        // Test priority queue - empty and add elements
        int m = 50;
        PQ<Integer> priq = new BinaryHeap<Integer>(m, minh);

        for (int i = 0; i < m; i++) {
                priq.add(rand.nextInt(max - min + 1));
        }
        System.out.println(priq.size());

        int extra = 15;
        for (int i = 0; i < extra; i++) {
                priq.add(rand.nextInt(max - min + 1));
        }
        System.out.println(priq.size());

        while (!priq.isEmpty()) {
                System.out.println(priq.deleteMin());
        }
}
}
