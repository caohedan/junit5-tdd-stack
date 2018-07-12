package com.thoughtworks.tdd.requirement2;

public class Receipt {
    private Car car;
    private ParkingLot parkingLot;
    public Receipt(Car car, ParkingLot parkingLot) {
        this.car = car;
        this.parkingLot = parkingLot;
    }

    public Car getCar() {
        return car;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }

    public Receipt() {
    }
}
