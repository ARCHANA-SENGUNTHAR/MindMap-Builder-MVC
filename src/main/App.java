package main;

import controller.*;
import db.DBConnection;
import view.ConsoleView;

public class App {
    public static void main(String[] args) {
        // Initialize DB (reads config from resources/config.properties)
        DBConnection.init();

        ConsoleView view = new ConsoleView();
        AppController appController = new AppController(view);
        appController.start();

        DBConnection.close();
        System.out.println("Goodbye!");
    }
}