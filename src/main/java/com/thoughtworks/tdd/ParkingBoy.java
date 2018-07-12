package com.thoughtworks.tdd;


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
        if (isParkingLotsFull()) {
            throw new ParkingLotFullException();
        }
        Receipt receipt = null;
        for (ParkingLot parkingLot : this.parkingLots) {
            try {
                receipt = parkingLot.park(car);
                if (receipt != null)
                    break;
            }
          catch (ParkingLotFullException exception){
                System.out.print("Force it into the full garage");
          }
        }

        return receipt;
    }

    public boolean isParkingLotsFull() {
        for (ParkingLot parkingLot : this.parkingLots) {
            if (!parkingLot.isFull()) {
                return false;
            }
        }
        return true;
    }

    public Car unPark(Receipt receipt) {
        ParkingLot theParkingLot = null;
       for(ParkingLot parkingLot:parkingLots)
       {
           if(parkingLot.isFindReceipt(receipt))
           theParkingLot = parkingLot;
       }
       System.out.print(theParkingLot);
        return theParkingLot.unPark(receipt);
    }
}
