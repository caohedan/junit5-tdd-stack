package com.thoughtworks.tdd.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingLot {
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
        Receipt receipt = new  Receipt();
        receiptsMap.put(receipt,car);
      return receipt;
    }

    public Car unPark(Receipt receipt) {
        Car car = receiptsMap.get(receipt);
        receiptsMap.remove(receipt);
       return car;
    }

    public boolean isFull() {
        return this.size == receiptsMap.size();
    }
}
