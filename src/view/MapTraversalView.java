package view;

import model.Node;
import java.util.List;

public class MapTraversalView {
    public void showTraversalStep(Node node) {
        System.out.println("\nVisited: " + node.getTitle() + " (" + node.getId() + ")");
        System.out.println(node.getContent());
    }

    public void showPath(List<Node> path) {
        System.out.println("Path:");
        for (Node n : path)
            System.out.print(n.getTitle() + " -> ");
        System.out.println("END");
    }
}