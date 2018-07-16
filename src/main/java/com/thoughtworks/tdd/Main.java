package com.thoughtworks.tdd;

import com.thoughtworks.tdd.controllers.ParkingSystemController;
import com.thoughtworks.tdd.core.*;
import com.thoughtworks.tdd.views.ParkingSystemView;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(new ParkingLot("西南停车场",3));
        parkingLots.add(new ParkingLot("西南停车场",4));
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Response response = new Response();
        Request request = new Request();
        ParkingSystemController controller = new ParkingSystemController(new ParkingSystemView(), parkingBoy, response);
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
