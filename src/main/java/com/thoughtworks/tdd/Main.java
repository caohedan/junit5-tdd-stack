package com.thoughtworks.tdd;

import com.thoughtworks.tdd.controllers.ParkingSystemController;
import com.thoughtworks.tdd.core.*;

public class Main {

    public static void main(String[] args) throws Exception {


        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot("西南停车场",3);
        parkingBoy.addParkingLot("西南停车场",4);
        Response response = new Response();
        Request request = new Request();
        ParkingSystemController controller = new ParkingSystemController(parkingBoy, response);
        Input cli = new Input();
        String currentPage = "main";
        Router router = new Router(currentPage, controller);
        while (true) {
            String command = cli.input();
            request.setCommand(command);
            router.handleRequest(request);
        }

    }
}
