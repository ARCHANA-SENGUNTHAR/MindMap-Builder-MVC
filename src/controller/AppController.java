package controller;

import view.ConsoleView;

public class AppController {
    private ConsoleView view;
    private NodeController nodeController;
    private LinkController linkController;
    private MapController mapController;
    private StudyModeController studyController;

    public AppController(ConsoleView view) {
        this.view = view;
        this.nodeController = new NodeController(view);
        this.linkController = new LinkController(view);
        this.mapController = new MapController(view);
        this.studyController = new StudyModeController(view);
    }

    public void start() {
        boolean running = true;
        while (running) {
            int choice = view.showMainMenu();
            switch (choice) {
                case 1 -> nodeController.createNodeFlow();
                case 2 -> linkController.createLinkFlow();
                case 3 -> mapController.traverseMapFlow();
                case 4 -> mapController.searchFlow();
                case 5 -> mapController.pathFindingFlow();
                case 6 -> studyController.studyModeFlow();
                case 7 -> studyController.showDueReviews();
                case 0 -> running = false;
                default -> view.showMessage("Invalid choice. Try again.");
            }
        }
    }
}