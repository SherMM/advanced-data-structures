/**
 * Simple node class implemented to test indexed heap. It is not used
 * in any of the Shortest Path problems.
 *
 * @author G16 - Vasudev Ravindran & Ian Laurain
 */
public class Node implements Index, Comparable<Node> {
private int value;
private int index;

/**
 * Constructor for Node
 *
 * @param val - integer value held by node
 * @param idx - index value of node for finding in Indexed Heap
 */
public Node(int val, int idx) {
        value = val;
        index = idx;
}

/**
 * Returns integer value stored in a node
 *
 * @return val : integer value stored in node
 */
public int getValue() {
        return this.value;
}

/**
 * Sets the integer value of a node
 *
 * @param val : intger value to set in a node
 */
public void setValue(int val) {
        this.value = val;
}

/**
 * Returns the index of a node which is its position in an
 * Indexed Heap
 *
 * @return index : index of node in Indexed Heap
 */
public int getIndex() {
        return this.index;
}

/**
 * Sets the index of a node to indicate its position in Indexed Heap
 *
 * @param idx : index value to set
 */
public void putIndex(int idx) {
        this.index = idx;
}

/**
 * Determines if two Nodes are equal, less than, or greater than
 *
 * @param that : another node
 *
 * @return integer : indicates ==, <, >
 */
public int compareTo(Node that) {
        return this.value - that.value;
}

/**
 * For testing purposes
 */
public static void main(String[] args) {
        Node node1 = new Node(5, 0);
        Node node2 = new Node(10, 1);

        System.out.println("Node value: " + node1.getIndex());
        System.out.println(node1.compareTo(node2) < 0);
}
}
