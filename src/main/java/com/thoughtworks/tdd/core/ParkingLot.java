package com.thoughtworks.tdd.core;

import com.thoughtworks.tdd.core.exception.ParkingLotFullException;

import java.util.HashMap;


public class ParkingLot {
    private int size;
    private HashMap<String, Car> receiptsMap;

    public ParkingLot(int size) {
        this.size = size;
        receiptsMap = new HashMap<String, Car>();
    }

    public Receipt park(Car car) {
        if (isFull()) {
            throw new ParkingLotFullException();
        }
        Receipt receipt = new Receipt();
        receiptsMap.put(receipt.getId(), car);
        return receipt;
    }

    public Car unPark(String receiptId) {
        Car car = receiptsMap.get(receiptId);
        receiptsMap.remove(receiptId);
        return car;
    }

    public boolean isFindReceipt(String receiptId) {
        if (this.receiptsMap.get(receiptId) != null)
            return true;
        return false;
    }

    public boolean isFull() {
        return this.size == receiptsMap.size();
    }

}
