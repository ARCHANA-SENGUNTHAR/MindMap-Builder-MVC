package view;

import utils.InputHelper;
import model.Node;
import java.util.List;

public class ConsoleView {
    public int showMainMenu() {
        System.out.println("\n=== Knowledge Map Builder ===");
        System.out.println("1. Create Node");
        System.out.println("2. Create Link");
        System.out.println("3. Traverse Map (by topic)");
        System.out.println("4. Search Nodes");
        System.out.println("5. Find Path between Nodes");
        System.out.println("6. Study Mode");
        System.out.println("7. Show Due Reviews");
        System.out.println("0. Exit");
        return InputHelper.readInt("Choose option:");
    }

    public Node readNodeDetails() {
        String title = InputHelper.readLine("Node Title:");
        String topic = InputHelper.readLine("Topic (e.g., Data Structures):");
        String content = InputHelper.readLine("Short Content / Summary:");
        Node n = new Node();
        n.setTitle(title);
        n.setTopic(topic);
        n.setContent(content);
        return n;
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public int readNodeId(String prompt) {
        return InputHelper.readInt(prompt);
    }

    public String readLine(String prompt) {
        return InputHelper.readLine(prompt);
    }

    public void showNode(Node n) {
        System.out.println("--- Node " + n.getId() + " ---");
        System.out.println("Title: " + n.getTitle());
        System.out.println("Topic: " + n.getTopic());
        System.out.println("Content: " + n.getContent());
        System.out.println("Next Review: " + n.getNextReviewDate());
    }

    public void showNodeList(List<Node> nodes) {
        if (nodes.isEmpty()) {
            System.out.println("No nodes found.");
            return;
        }
        for (Node n : nodes)
            showNode(n);
    }
}