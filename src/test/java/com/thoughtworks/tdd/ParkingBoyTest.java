package com.thoughtworks.tdd;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class ParkingBoyTest {

    @Test
    public void should_park_successfully_given_parking_lot_is__not_full_by_one_parkinglot() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = new Car();
        parkingBoy.addParkingLot(parkingLot);
        when(parkingLot.isFull()).thenReturn(false);
        //when
       try {
           parkingBoy.park(car);
       }catch (ParkingLotFullException exception){
           fail("should park successfully");
       }
        //then
        Mockito.verify(parkingLot).park(car);


    }
    @Test
    public void should_park_successfully_given_parking_by_two_parkinglots() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        ParkingLot parkingLot_1 = mock(ParkingLot.class);
        ParkingLot parkingLot_2 = mock(ParkingLot.class);
        parkingLots.add(parkingLot_1);
        parkingLots.add(parkingLot_2);
        parkingBoy.addParkingLot(parkingLots);
        Car car = new Car();
        when(parkingLot_1.isFull()).thenReturn(true);
        when(parkingLot_2.isFull()).thenReturn(false);
        //when
        parkingBoy.park(car);
        //then
        Mockito.verify(parkingLot_2).park(car);

    }

    @Test
    public void should_park_failed_given_parking_when_given_one_full_parkingLot(){
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot = mock(ParkingLot.class);
        parkingBoy.addParkingLot(parkingLot);
        when(parkingLot.isFull()).thenReturn(true);

        try {
            //when
            parkingBoy.park(new Car());
            //then
            fail("should park successfully");
        } catch (ParkingLotFullException exception) {

        }


    }
    @Test
    public void should_park_failed_given_parking_when_given_multiple_full_parkingLot(){
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        ParkingLot parkingLot_1 = mock(ParkingLot.class);
        ParkingLot parkingLot_2 = mock(ParkingLot.class);
        parkingLots.add(parkingLot_1);
        parkingLots.add(parkingLot_2);
        parkingBoy.addParkingLot(parkingLots);
        when(parkingLot_1.isFull()).thenReturn(true);
        when(parkingLot_2.isFull()).thenReturn(true);
        try {
            //when
            parkingBoy.park(new Car());
            //then
            fail("should park successfully");
        } catch (ParkingLotFullException exception) {

        }


    }
    @Test
    public  void should_return_the_verify_success_in_order_when_park_two_cars(){
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot_1 = new ParkingLot(1);
        ParkingLot parkingLot_2 = new ParkingLot(1);;
        parkingBoy.addParkingLot(parkingLot_1);
        parkingBoy.addParkingLot(parkingLot_2);
        Car car_1 = new Car();
        Car car_2 = new Car();
        //when
        Receipt receipt_1 = parkingBoy.park(car_1);
        Receipt receipt_2 = parkingBoy.park(car_2);

        //then
        assertThat(parkingLot_1.unPark(receipt_1),is(car_1));
        assertThat(parkingLot_2.unPark(receipt_2),is(car_2));


    }

    @Test
    public void should_get_specific_car_when_call_unPark_given_receipt_is_right(){
        //given
        ParkingBoy parkingLots = new ParkingBoy();
        parkingLots.addParkingLot(new ParkingLot(1));
        Car theCar = new Car();
       //when
        Receipt receipt = parkingLots.park(theCar);
        //then
        assertThat(parkingLots.unPark(receipt), is(theCar));

    }

    @Test
    public void should_not_get_specific_car_when_call_unPark_given_receipt_is_wrong(){
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(new ParkingLot(2));
        //when
        Car theCar = new Car();
        Receipt receipt = parkingBoy.park(theCar);
        Receipt anotherReceipt = parkingBoy.park(new Car());
        //then
        assertThat(parkingBoy.unPark(anotherReceipt), CoreMatchers.not(theCar));
    }


    @Test
    public void should_be_true_when_call_isFull_given_parking_lot_is_full(){
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        //when
        parkingBoy.addParkingLot(new ParkingLot(0));
        //then
        assertThat(parkingBoy.isParkingLotsFull(), is(true));
    }

    @Test
    public void should_be_false_when_call_isFull_given_parking_lot_is_not_full(){
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(new ParkingLot(1));

        assertThat(parkingBoy.isParkingLotsFull(), is(false));
    }

    @Test
    public void should_be_false_when_call_isFull_given_a_full_parking_lot_take_out_a_car(){
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(new ParkingLot(1));
        Car theCar = new Car();
        Receipt receipt = parkingBoy.park(theCar);
        parkingBoy.unPark(receipt);
        assertThat(parkingBoy.isParkingLotsFull(), is(false));
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
