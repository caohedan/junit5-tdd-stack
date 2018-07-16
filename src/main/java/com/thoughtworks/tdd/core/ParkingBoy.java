package com.thoughtworks.tdd.core;


import com.thoughtworks.tdd.core.exception.ParkingBoyFullException;
import com.thoughtworks.tdd.core.exception.ParkingLotFullException;
import com.thoughtworks.tdd.core.exception.ReceiptIsNotExistException;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;
    private int parkingLotNum ;
    public ParkingBoy() {
        this.parkingLots = new ArrayList<ParkingLot>();
        this.parkingLotNum = 0;
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }

//    public void addParkingLot(ParkingLot parkingLot) {
//        this.parkingLots.add(new ParkingLot(convertNumToString(),parkingLot.getName(),parkingLot.getSize()));
//
//    }

    public void addParkingLot(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public void addParkingLot(List<ParkingLot> parkingLots) {
        parkingLots.stream().forEach(parkingLot -> this.parkingLots.add(parkingLot));
    }
    public void addParkingLot(String name, int size) {
        this.parkingLots.add(new ParkingLot(convertNumToString(),name,size));
    }

    public Receipt park(Car car) {
        if (isParkingLotsFull()) {
            throw new ParkingBoyFullException();
        }
        Receipt receipt = null;
        for (ParkingLot parkingLot : this.parkingLots) {
            try {
                receipt = parkingLot.park(car);
                break;
            } catch (ParkingLotFullException exception) {

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

    public Car unPark(String receiptId) {
        ParkingLot theParkingLot = null;
        theParkingLot = getTheParkingLot(receiptId, theParkingLot);
        if (theParkingLot == null) {
            throw new ReceiptIsNotExistException();
        }
        return theParkingLot.unPark(receiptId);
    }

    private ParkingLot getTheParkingLot(String receiptId, ParkingLot theParkingLot) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.isFindReceipt(receiptId))
                theParkingLot = parkingLot;
        }
        return theParkingLot;
    }
    private String convertNumToString(){
        ++this.parkingLotNum;
        return String.format("%03d", this.parkingLotNum);
    }


    public ParkingLot findParingLot(String command) {
        for(ParkingLot p:this.parkingLots){
            if(p.getId().equals(command))
                return p;
        }
        return  null;
    }

    public void removeParkingLot(ParkingLot parkingLot) {
        this.parkingLots.remove(parkingLot);
    }
}
