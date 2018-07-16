package com.thoughtworks.tdd.core;

import com.thoughtworks.tdd.controllers.ParkingSystemController;

public class Router {
    private ParkingSystemController controller;
    private String currentPage;

    public Router(String currentPage, ParkingSystemController controller) {
        this.currentPage = currentPage;
        this.controller = controller;
        showForwordPage();
    }

    private void showForwordPage() {
        controller.mainPage();
    }

    public void handleRequest(Request request) {
        switch (currentPage) {
            case "main":
                handleMainPage(request.getCommand());
                break;
            case "park":
                handleParkPage(request.getCommand());
                break;
            case "unPark":
                handleUnParkRequest(request.getCommand());
                break;
            default:
                handleInvalidPage(request.getCommand());
        }

    }

    private void handleInvalidPage(String command) {
        controller.dealInvalidPage();
        currentPage = "main";
    }

    private void handleParkPage(String command) {
        controller.park(command);
        currentPage = "main";

    }

    private void handleMainPage(String command) {
        switch (command) {
            case "1":
                currentPage = controller.parkPage();
                break;
            case "2":
                controller.unParkPage();
                currentPage = "unPark";
                break;
            default:
                controller.dealInvalidPage();
                currentPage = "invalid";
        }

    }

    private void handleUnParkRequest(String command) {
        controller.unPark(command);
        currentPage = "main";
    }
}
