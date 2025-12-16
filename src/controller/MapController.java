package controller;

import view.ConsoleView;
import view.MapTraversalView;
import db.NodeDAO;
import db.LinkDAO;
import model.Node;
import model.Link;
import utils.PathFindingHelper;

import java.util.*;

public class MapController {
    private ConsoleView view;
    private MapTraversalView traversalView;
    private NodeDAO nodeDAO;
    private LinkDAO linkDAO;

    public MapController(ConsoleView view) {
        this.view = view;
        this.traversalView = new MapTraversalView();
        this.nodeDAO = new NodeDAO();
        this.linkDAO = new LinkDAO();
    }

    public void traverseMapFlow() {
        try {
            String topic = view.readLine("Enter topic to traverse:");
            List<Node> nodes = nodeDAO.findByTopic(topic);
            if (nodes.isEmpty()) {
                view.showMessage("No nodes for this topic.");
                return;
            }
            // simple traversal: start with first node and BFS using links
            List<Link> allLinks = linkDAO.findAll();
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (Link l : allLinks)
                adj.computeIfAbsent(l.getFromNodeId(), k -> new ArrayList<>()).add(l.getToNodeId());
            // BFS by node IDs
            for (Node n : nodes) {
                traversalView.showTraversalStep(n);
                List<Integer> neighbors = adj.getOrDefault(n.getId(), List.of());
                for (int nid : neighbors) {
                    Node child = nodeDAO.findById(nid);
                    if (child != null)
                        traversalView.showTraversalStep(child);
                }
            }
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    public void searchFlow() {
        try {
            String topic = view.readLine("Enter topic to search:");
            var nodes = nodeDAO.findByTopic(topic);
            view.showNodeList(nodes);
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    public void pathFindingFlow() {
        try {
            int from = view.readNodeId("From Node ID:");
            int to = view.readNodeId("To Node ID:");
            List<Link> allLinks = linkDAO.findAll();
            Map<Integer, List<Integer>> adj = new HashMap<>();
            for (Link l : allLinks)
                adj.computeIfAbsent(l.getFromNodeId(), k -> new ArrayList<>()).add(l.getToNodeId());
            List<Integer> pathIds = PathFindingHelper.bfsFindPath(from, to, adj);
            if (pathIds == null) {
                view.showMessage("No path found.");
                return;
            }
            List<Node> pathNodes = new ArrayList<>();
            for (int id : pathIds)
                pathNodes.add(nodeDAO.findById(id));
            traversalView.showPath(pathNodes);
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }
}