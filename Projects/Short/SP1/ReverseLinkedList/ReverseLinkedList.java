import java.util.ArrayDeque;
import java.util.Random;

/**
 * Classs that implements recursive and iterative methods on
 * a singly-linked list in order to reverse it or print out its
 * elements in reverse order
 *
 * @author G16
 */
public class ReverseLinkedList<T> extends SinglyLinkedList<T> {

// Empty constructor
ReverseLinkedList() {

}

/**
 * Reverses a singly-linked list iteratively
 */
void reverseIter() {
        // if list size is > 1, otherwise do nothing
        if (this.header.next != null && this.size > 1) {
                Entry<T> temp1 = this.header.next;
                Entry<T> temp2 = temp1.next;
                Entry<T> temp3 = temp2.next;

                temp1.next = null;
                this.tail = temp1;
                temp2.next = temp1;
                temp1 = temp3;

                // iterate through remainder of list
                while (temp1 != null) {
                        Entry<T> nextNode = temp1.next;
                        temp1.next = temp2;
                        temp2 = temp1;
                        temp1 = nextNode;
                }
                this.header.next = temp2;
        }
}

/**
 * Reverses a singly-linked list recursively
 *
 * @param node Generic item storing a value and next ptr
 */
void reverseRec(Entry<T> node) {
        // if list is empty
        if (node == null) {
                return;
        }

        // if at end of list
        if (node.next == null) {
                this.tail = this.header.next;
                this.header.next = node;
                return;
        }

        // list still more nodes to handle
        reverseRec(node.next);
        node.next.next = node;
        node.next = null;
}

/**
 * Prints out items in a singly-linked list iteratively
 */
void printReverseIter() {
        Entry<T> x = this.header.next;
        ArrayDeque<T> stack = new ArrayDeque<T>();
        while (x != null) {
                stack.push(x.element);
                x = x.next;
        }

        while (!stack.isEmpty()) {
                System.out.print(stack.pop() + " ");
        }
        System.out.println();
}

/**
 * Prints out items in a singly-linked list recursively
 */
void printReverseRec(Entry<T> node) {
        if (node == null) {
                return;
        }

        printReverseRec(node.next);
        // check for header element
        if (node.element != null) {
                System.out.print(node.element + " ");
        } else {
                System.out.println();
        }
}

/**
 * Main method used for testing purposes and accepting input for stdin
 * and outputting results to stdout
 */
public static void main(String[] args) {
        // make 2 lists, one for reversing iteratively, and the other
        // for reversing recursively
        ReverseLinkedList<Integer> list1 = new ReverseLinkedList<Integer>();
        ReverseLinkedList<Integer> list2 = new ReverseLinkedList<Integer>();

        // list size variable
        // This number was thoroughly changed
        int n = 25;

        // variables that control range
        // to randomly select integers from
        int max = 750;
        int min = 0;

        // add elements to the lists
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
                list1.add(rand.nextInt(max - min + 1));
        }

        for (int i = 0; i < n; i++) {
                list2.add(rand.nextInt(max - min + 1));
        }

        // iterative tests
        System.out.println("ITERATIVE TESTS");
        System.out.println("---------------");
        System.out.println();
        // print the original list state
        System.out.println("Original order of list1: ");
        list1.printList();
        System.out.println();

        // print the original list state in reverse order
        System.out.println("Printing out reverse order of list1 iteratively: ");
        list1.printReverseIter();
        System.out.println();

        // reverse the list
        System.out.println("Reversing list1 order..........");
        list1.reverseIter();
        System.out.println();

        // print new order of list (reversed)
        System.out.println("New order of list1: ");
        list1.printList();
        System.out.println();

        // recursive tests
        System.out.println("RECURSIVE TESTS");
        System.out.println("---------------");
        System.out.println();
        // print the original list state
        System.out.println("Original order of list2: ");
        list2.printList();
        System.out.println();

        // print the original list state in reverse order
        System.out.println("Printing out reverse order of list2 recursively: ");
        list2.printReverseRec(list2.header.next);
        System.out.println();

        // reverse the list
        System.out.println();
        System.out.println("Reversing list2 order..........");
        list2.reverseRec(list2.header.next);
        System.out.println();

        // print new order of list (reversed)
        System.out.println("New order of list2: ");
        list2.printList();
        System.out.println();
}
}
