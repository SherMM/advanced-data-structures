import java.util.*;

/**
 * This Class was obtained from the elearning CS6301 forum in a post by a fellow
 * classmate named Dhruv Arora. This student found this code on stackoverflow at
 * the following link:
 *
 * http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
 *
 * This code was used for debugging and making sure that a the tree was indeed
 * balanced afer added all elements to it. It was very helpful in this regard.
 */
public class BTreePrinter {

public static <T extends Comparable<?> > void printNode(Entry<T> root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
}

private static <T extends Comparable<?> > void printNodeInternal(List<Entry<T> > nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
                return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<Entry<T> > newNodes = new ArrayList<Entry<T> >();
        for (Entry<T> node : nodes) {
                if (node != null) {
                        System.out.print(node.element);
                        newNodes.add(node.left);
                        newNodes.add(node.right);
                } else {
                        newNodes.add(null);
                        newNodes.add(null);
                        System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                        BTreePrinter.printWhitespaces(firstSpaces - i);
                        if (nodes.get(j) == null) {
                                BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                                continue;
                        }

                        if (nodes.get(j).left != null)
                                System.out.print("/");
                        else
                                BTreePrinter.printWhitespaces(1);

                        BTreePrinter.printWhitespaces(i + i - 1);

                        if (nodes.get(j).right != null)
                                System.out.print("\\");
                        else
                                BTreePrinter.printWhitespaces(1);

                        BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
}

private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
                System.out.print(" ");
}

private static <T extends Comparable<?> > int maxLevel(Entry<T> node) {
        if (node == null)
                return 0;

        return Math.max(BTreePrinter.maxLevel(node.left), BTreePrinter.maxLevel(node.right)) + 1;
}

private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
                if (object != null)
                        return false;
        }

        return true;
}
}
