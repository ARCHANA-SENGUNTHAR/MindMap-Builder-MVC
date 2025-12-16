package controller;

import view.ConsoleView;
import db.NodeDAO;
import model.Node;
import utils.DateHelper;

import java.time.LocalDate;
import java.util.List;

public class StudyModeController {
    private ConsoleView view;
    private NodeDAO nodeDAO;

    public StudyModeController(ConsoleView view) {
        this.view = view;
        this.nodeDAO = new NodeDAO();
    }

    public void studyModeFlow() {
        try {
            String topic = view.readLine("Enter topic to study:");
            List<Node> nodes = nodeDAO.findByTopic(topic);
            if (nodes.isEmpty()) {
                view.showMessage("No nodes.");
                return;
            }
            for (Node n : nodes) {
                view.showNode(n);
                String feedback = view.readLine("Did you recall this well? (y/n):");
                LocalDate next = DateHelper.calculateNextReview(n.getNextReviewDate(), feedback.equalsIgnoreCase("y"));
                nodeDAO.updateNextReview(n.getId(), next);
                view.showMessage("Next review set to: " + next);
            }
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }

    public void showDueReviews() {
        try {
            List<Node> all = nodeDAO.findAll();
            LocalDate today = LocalDate.now();
            for (Node n : all) {
                if (n.getNextReviewDate() == null)
                    continue;
                if (!n.getNextReviewDate().isAfter(today))
                    view.showNode(n);
            }
        } catch (Exception e) {
            view.showMessage("Error: " + e.getMessage());
        }
    }
}