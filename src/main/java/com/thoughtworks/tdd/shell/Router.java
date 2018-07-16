package com.thoughtworks.tdd.shell;

import com.thoughtworks.tdd.shell.controllers.ParkingSystemController;
import com.thoughtworks.tdd.shell.io.Request;

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
            case"park_and_unpark":
                handlePark_and_unparkPage(request.getCommand());
                break;
            case"check_and_delete_parkingLot":
                handlePark_and_check_and_delete_parkingLotPage(request.getCommand());
                break;
            case "park":
                handleParkPage(request.getCommand());
                break;
            case "unPark":
                handleUnPark(request.getCommand());
                break;
            case"check_parking_lot":
                handleCheckParkingLot();
                break;
            case"add_parking_lot":
                handleAddParkingLot(request.getCommand());
                break;
            case"delete_parking_lot":
                handleDeleteParkingLot(request.getCommand());
                break;
            default:
                handleInvalidPage();
        }

    }




    private void handlePark_and_check_and_delete_parkingLotPage(String command) {
        switch (command) {
            case "1":
                currentPage = controller.checkPakingLotPage();
                break;
            case "2":
                currentPage = controller.addParkingLotPage();
                break;
            case"3":
                currentPage = controller.deleteParkingLotPage();
                break;
            default:
                controller.dealInvalidPage();
        }
    }

    private void handlePark_and_unparkPage(String command) {
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
        }

    }

    private void handleInvalidPage() {
        controller.dealInvalidPage();
        currentPage = "main";
    }

    private void handleParkPage(String command) {
        controller.park(command);
        currentPage = "main";

    }
    private void handleCheckParkingLot() {
        controller.checkPakingLotPage();
        currentPage = "main";
    }
    private void handleAddParkingLot(String command) {
        controller.addParkingLot(command);
        currentPage = "main";

    }
    private void handleDeleteParkingLot(String command) {
        controller.delteParkingLot(command);
        currentPage = "main";
    }
    private void handleMainPage(String command) {
        switch (command) {
            case "1":
                currentPage = controller.parkAndUnParkPage();
                break;
            case "2":
                currentPage = controller.check_and_delete_parkingLot();
                break;
            default:
                controller.dealInvalidPage();
        }

    }

    private void handleUnPark(String command) {
        controller.unPark(command);
        currentPage = "main";
    }
}
