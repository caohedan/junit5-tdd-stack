package com.thoughtworks.tdd;

import java.util.HashMap;


public class ParkingLot{
    private int size ;
    private HashMap<Receipt,Car> receiptsMap;
    public ParkingLot(int size) {
        this.size = size ;
        receiptsMap = new HashMap<Receipt,Car>();
    }

    public Receipt park(Car car) {
        if(isFull())
        {
            throw new ParkingLotFullException();
        }
        Receipt receipt = new Receipt();
        receiptsMap.put(receipt,car);
        return receipt;
    }

    public Car unPark(Receipt receipt) {
        Car car = receiptsMap.get(receipt);
        receiptsMap.remove(receipt);
        return car;
    }
    public boolean isFindReceipt(Receipt receipt)
    {
        if(this.receiptsMap.get(receipt) != null)
            return  true;
        return false;
    }

    public boolean isFull() {
        return this.size == receiptsMap.size();
    }

}
