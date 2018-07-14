package com.thoughtworks.tdd.controllers;

import com.google.inject.Inject;
import com.thoughtworks.tdd.views.ParkingSystemView;

import java.io.IOException;

public class ParkingSystemController {
    private final ParkingSystemView parkingSystemView;
    @Inject
    public ParkingSystemController(ParkingSystemView parkingSystemView) {
        this.parkingSystemView = parkingSystemView;
    }

    public void begin() throws IOException {
        parkingSystemView.showBegin();

    }

    public void perform() {
    }
}
