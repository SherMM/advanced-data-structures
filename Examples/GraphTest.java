import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Exception;

public class GraphTest {
public static void main(String[] args) throws FileNotFoundException {
        Scanner in;
        if (args.length > 0) {
                File inputFile = new File(args[0]);
                in = new Scanner(inputFile);
        } else {
                in = new Scanner(System.in);
        }
        Graph g = Graph.readGraph(in, true);

        for (Vertex u : g) {
          System.out.println(u.name);
        }
}
}
