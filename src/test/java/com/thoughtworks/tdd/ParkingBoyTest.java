package com.thoughtworks.tdd;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.core.exception.ParkingLotFullException;
import com.thoughtworks.tdd.core.exception.ReceiptIsNotExistException;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;


public class ParkingBoyTest {

    @Test
    public void should_park_successfully_given_parking_lot_is__not_full_by_one_parkinglot() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot = mock(ParkingLot.class);
        Car car = new Car("湘L-554466");
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
        Car car = new Car("湘L-554466");
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
            parkingBoy.park(new Car("湘L-554466"));
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
            parkingBoy.park(new Car("湘L-554466"));
            //then
            fail("should park successfully");
        } catch (ParkingLotFullException exception) {

        }


    }
    @Test
    public  void should_return_the_verify_success_in_order_when_park_two_cars(){
        //given
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        ParkingLot parkingLot_1 = mock(ParkingLot.class);
        ParkingLot parkingLot_2 = mock(ParkingLot.class);
        parkingLots.add(parkingLot_1);
        parkingLots.add(parkingLot_2);
        parkingBoy.addParkingLot(parkingLots);
        Car car_1 = new Car("湘L-554466");
        Car car_2 = new Car("湘L-778899");
        when(parkingLot_1.isFull()).thenReturn(false,true);
        when(parkingLot_2.isFull()).thenReturn(false);
        //when
        parkingBoy.park(car_1);
        parkingBoy.park(car_2);

        //then
        InOrder inOrder = inOrder(parkingLot_1, parkingLot_2);
        inOrder.verify(parkingLot_1).park(car_1);
        inOrder.verify(parkingLot_2).park(car_2);

    }

    @Test
    public void should_catch_exception_when_call_unPark_given_receipt_is_not_exist(){
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        ParkingLot parkingLot = mock(ParkingLot.class);
        parkingBoy.addParkingLot(parkingLot);
        Receipt receipt = new Receipt();
        when(parkingLot.isFindReceipt(receipt)).thenReturn(false);

        try {
            //when
           parkingBoy.unPark(receipt);
            //then
            fail("should receipt is correct");
        } catch (ReceiptIsNotExistException exception) {

        }
    }



}
