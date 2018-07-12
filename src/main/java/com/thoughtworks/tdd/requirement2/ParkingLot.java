package com.thoughtworks.tdd.requirement2;

import java.util.HashMap;

public class ParkingLot {
    private int size ;
    private HashMap<Car,Receipt> receiptsMap;
    public ParkingLot(int size) {
        this.size = size ;
        receiptsMap = new HashMap<Car,Receipt>();
    }

    public Receipt park(Car car) {
      if(isFull())
      {

          return  null;
      }
        Receipt receipt = new Receipt(car,this);
        receiptsMap.put(car,receipt);
         return receipt;
    }

    public Car unPark(Receipt receipt) {
        receiptsMap.remove(receipt.getCar(),receipt);
       return receipt.getCar();
    }

    public boolean isFull() {
        return this.size == receiptsMap.size();
    }
}
