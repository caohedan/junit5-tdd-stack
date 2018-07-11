package com.thoughtworks.tdd;

public class ParkSpace {
    private int total;
    private int full;

    public ParkSpace(int total) {
        this.total = total;
        this.full = 0;
    }

    public String addCar(Car car) throws Exception {
        if(full>total)
            throw  new Exception("停车位不够，异常操作!");
        if ((this.total - this.full) > 0) {
            this.full++;
            return car.displayInfo(true);
        }
        return car.displayInfo(false);
    }

    public int getNumofVacancy() {
        return total - full;
    }

    public void pickUpCar(Car car) {
        this.full--;
    }
}
