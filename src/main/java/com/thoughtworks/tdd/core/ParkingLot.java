package com.thoughtworks.tdd.core;

import com.thoughtworks.tdd.core.exception.ParkingLotFullException;

import java.util.HashMap;


public class ParkingLot {
    private int size;
    private String name;
    private String id;
    private HashMap<String, Car> receiptsMap;

    public ParkingLot(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public ParkingLot(String id,String name, int size) {
        this.id = id;
        this.size = size;
        this.name = name;
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
    public int getParkNum()
    {
        return receiptsMap.size();
    }

    public boolean isFull() {
        return this.size == receiptsMap.size();
    }

}
