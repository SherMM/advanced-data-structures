public class Node implements Index, Comparable<Node> {
private int value;
private int index;

public Node(int val, int idx) {
        value = val;
        index = idx;
}

public int getValue() {
        return this.value;
}

public void setValue(int val) {
        this.value = val;
}

public int getIndex() {
        return this.index;
}

public void putIndex(int idx) {
        this.index = idx;
}

public int compareTo(Node that) {
        return this.value - that.value;
}

public static void main(String[] args) {
        Node node1 = new Node(5, 0);
        Node node2 = new Node(10, 1);

        System.out.println("Node value: " + node1.getIndex());
        System.out.println(node1.compareTo(node2) < 0);
}
}
