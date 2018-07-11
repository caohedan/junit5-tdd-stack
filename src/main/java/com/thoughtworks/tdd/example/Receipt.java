package com.thoughtworks.tdd.example;

import java.util.HashMap;

public class Receipt {
    private  Car car;
    private  ParkingLot parkingLot;
    public Receipt(Car car, ParkingLot parkingLot) {
        this.car = car;
        this.parkingLot = parkingLot;
    }

    public Car getCar() {
        return car;
    }

    public Receipt() {

    }
}
