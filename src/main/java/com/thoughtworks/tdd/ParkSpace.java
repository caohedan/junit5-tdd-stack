package com.thoughtworks.tdd;

public class ParkSpace {
    private int total;
    private int full;

    public ParkSpace(int total) {
        this.total = total;
        this.full = 0;
    }

    public String addCar(Car car) {
        if ((this.total - this.full) > 0) {
            this.total--;
            this.full++;
            return car.displayInfo(true);
        }
            return car.displayInfo(false);

    }
}
