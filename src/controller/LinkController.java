package controller;

import view.ConsoleView;
import db.LinkDAO;
import model.Link;

public class LinkController {
    private ConsoleView view;
    private LinkDAO linkDAO;

    public LinkController(ConsoleView view) {
        this.view = view;
        this.linkDAO = new LinkDAO();
    }

    public void createLinkFlow() {
        try {
            int from = view.readNodeId("From Node ID:");
            int to = view.readNodeId("To Node ID:");
            String rel = view.readLine("Relation/label (optional):");
            Link l = new Link();
            l.setFromNodeId(from);
            l.setToNodeId(to);
            l.setRelation(rel);
            int id = linkDAO.create(l);
            if (id > 0)
                view.showMessage("Link created: " + id);
            else
                view.showMessage("Failed to create link");
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }
}