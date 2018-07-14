package com.thoughtworks.tdd.controllers;

import com.google.inject.Inject;
import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.core.exception.ReceiptIsNotExistException;
import com.thoughtworks.tdd.validator.InputSelectionValidator;
import com.thoughtworks.tdd.views.ParkingSystemView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ParkingSystemController {
    private final ParkingSystemView parkingSystemView;
    private BufferedReader bufferedReader;
    private ParkingBoy parkingBoy;

    {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Inject
    public ParkingSystemController(ParkingSystemView parkingSystemView) {
        this.parkingSystemView = parkingSystemView;
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        ParkingLot parkingLot_1 = new ParkingLot(0);
        ParkingLot parkingLot_2 = new ParkingLot(1);
        parkingLots.add(parkingLot_1);
        parkingLots.add(parkingLot_2);
        parkingBoy = new ParkingBoy(parkingLots);
    }

    public void begin() throws IOException {
        parkingSystemView.showBegin();
        inputSelection();

    }

    public void perform(String num) throws IOException {
        if (num.equals("1")) {
            parkingSystemView.showStop();
            if (parkingBoy.isParkingLotsFull()) {
                parkingSystemView.showParkingLotIsFull();
                begin();
            } else {
                parkingSystemView.dealParkingLotIsNotFull();
                Receipt receipt = parkingBoy.park(new Car(inputLicensNumber()));
                parkingSystemView.showReceipt(receipt);
                begin();

            }

        } else {
            parkingSystemView.showUnpark();
            try {
                Car car = parkingBoy.unPark(inputReceipt());
                parkingSystemView.showTheCar(car);
            } catch (ReceiptIsNotExistException exception) {
                parkingSystemView.showWrongReceipt();
            } finally {
                begin();
            }

        }

    }

    private String inputReceipt() throws IOException {
        String input = bufferedReader.readLine();
        return input;
    }

    private String inputLicensNumber() throws IOException {
        String input = bufferedReader.readLine();
        return input;

    }

    public void inputSelection() throws IOException {
        String input = bufferedReader.readLine();
        if (new InputSelectionValidator().validate(input)) {

            perform(input);
        } else {
            parkingSystemView.dealIllegalInput();
            inputSelection();

        }
    }

}
