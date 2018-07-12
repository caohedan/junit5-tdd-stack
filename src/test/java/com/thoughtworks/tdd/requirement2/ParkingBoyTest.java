package com.thoughtworks.tdd.requirement2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;


public class ParkingBoyTest {

    @Test
    public void should_park_successfully_given_parking_lot_is_one_and_not_full() {
        ParkingBoy parkingLots = new ParkingBoy();
        parkingLots.addParkingLot(new ParkingLot(1));
        try {
            parkingLots.park(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }
    @Test
    public void should_park_successfully_given_parking_lot_is_two_and_one_is_full_and_another_is_not_full() {
        ParkingBoy parkingBoy = new ParkingBoy();
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(new ParkingLot(0));
        parkingLots.add(new ParkingLot(1));
        parkingBoy.addParkingLot(parkingLots);
        try {
            parkingBoy.park(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }

    }

    @Test
    public void should_park_failed_given_parking_lot_is_two_and_parking_is_full(){
        ParkingBoy parkingLots = new ParkingBoy();
        parkingLots.addParkingLot(new ParkingLot(0));
        try {
            parkingLots.park(new Car());
            fail("should park successfully");
        } catch (ParkingLotFullException exception) {

        }
    }


    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right(){
        ParkingBoy parkingLots = new ParkingBoy();
        parkingLots.addParkingLot(new ParkingLot(1));
        Car theCar = new Car();
        Receipt receipt = parkingLots.park(theCar);

        assertThat(parkingLots.unPark(receipt), is(theCar));

    }

    @Test
    public void should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong(){
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(new ParkingLot(2));

        Car theCar = new Car();
        Receipt receipt = parkingBoy.park(theCar);

        Receipt anotherReceipt = parkingBoy.park(new Car());

        assertThat(parkingBoy.unPark(anotherReceipt), not(theCar));
    }


    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full(){
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(new ParkingLot(0));

        assertThat(parkingBoy.isParkingLotFull(), is(true));
    }

    @Test
    public void should_be_false_when_call_isFull_given_parking_lot_is_not_full(){
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(new ParkingLot(1));

        assertThat(parkingBoy.isParkingLotFull(), is(false));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car(){
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(new ParkingLot(1));
        Car theCar = new Car();
        Receipt receipt = parkingBoy.park(theCar);
        parkingBoy.unPark(receipt);
        assertThat(parkingBoy.isParkingLotFull(), is(false));
    }

    @Test
    public void should_park_successfullly_when_call_park_again_given_a_full_parking_lot_take_out_a_car(){
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(new ParkingLot(1));

        Car theCar = new Car();
        Receipt receipt = parkingBoy.park(theCar);
        parkingBoy.unPark(receipt);

        try {
            parkingBoy.park(new Car());
        } catch (ParkingLotFullException exception) {
            fail("should park successfully");
        }
    }


}
