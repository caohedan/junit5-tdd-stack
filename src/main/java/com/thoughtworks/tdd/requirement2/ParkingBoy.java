package com.thoughtworks.tdd.requirement2;


import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy() {
        this.parkingLots = new ArrayList<ParkingLot>();
    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }
    public void addParkingLot(List<ParkingLot> parkingLots) {
        parkingLots.stream().forEach(parkingLot -> this.parkingLots.add(parkingLot));
    }
    public Receipt park(Car car) {
        System.out.print(isParkingLotFull());
        if (isParkingLotFull()) {
            throw new ParkingLotFullException();
        }
        Receipt receipt = null;
        for (ParkingLot parkingLot : this.parkingLots) {
            receipt = parkingLot.park(car);
            if (receipt != null)
                break;
        }

        return receipt;
    }

    public boolean isParkingLotFull() {
        for (ParkingLot parkingLot : this.parkingLots) {
            if (!parkingLot.isFull()) {
                return false;
            }
        }
        return true;
    }

    public Car unPark(Receipt receipt) {
        ParkingLot theParkingLot = receipt.getParkingLot();
        theParkingLot.unPark(receipt);
        return receipt.getCar();
    }
}
