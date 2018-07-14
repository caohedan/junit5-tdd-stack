package com.thoughtworks.tdd;

import com.google.inject.Injector;
import com.thoughtworks.tdd.controllers.ParkingSystemController;

import static com.google.inject.Guice.createInjector;

public class Main {

    public static void main(String[] args) throws Exception {

        Injector injector = createInjector(new ParkingLotModule());
        ParkingSystemController gameController = injector.getInstance(ParkingSystemController.class);

        gameController.begin();
        gameController.perform();
        //test  1234

    }
}
