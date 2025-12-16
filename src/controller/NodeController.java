package controller;

import view.ConsoleView;
import db.NodeDAO;
import model.Node;

public class NodeController {
    private ConsoleView view;
    private NodeDAO nodeDAO;

    public NodeController(ConsoleView view) {
        this.view = view;
        this.nodeDAO = new NodeDAO();
    }

    public void createNodeFlow() {
        try {
            Node n = view.readNodeDetails();
            int id = nodeDAO.create(n);
            if (id > 0) {
                n.setId(id);
                view.showMessage("Node created with id: " + id);
            } else
                view.showMessage("Failed to create node.");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }
}