package com.thoughtworks.tdd;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.ParkingLot;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.core.exception.ParkingBoyFullException;
import com.thoughtworks.tdd.core.exception.ParkingLotFullException;
import com.thoughtworks.tdd.core.exception.ReceiptIsNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;


public class ParkingBoyTest {
    private Car car_1;
    private Car car_2;
    private ParkingLot parkingLot_1;
    private ParkingLot parkingLot_2;

    @BeforeEach
    public void setUp() {
        car_1 = mock(Car.class);
        car_2 = mock(Car.class);
        parkingLot_1 = mock(ParkingLot.class);
        parkingLot_2 = mock(ParkingLot.class);
    }

    @Test
    public void should_park_successfully_given_parking_lot_is__not_full_by_one_parkinglot() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot_1);
        when(parkingLot_1.isFull()).thenReturn(false);
        //when
        try {
            parkingBoy.park(car_1);
        } catch (ParkingBoyFullException exception) {
            fail("should park successfully");
        }
        //then
        Mockito.verify(parkingLot_1).park(car_1);


    }

    @Test
    public void should_park_successfully_given_parking_by_two_parkinglots() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLot_1);
        parkingLots.add(parkingLot_2);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        when(parkingLot_1.isFull()).thenReturn(true);
        when(parkingLot_1.park(car_1)).thenThrow(new ParkingLotFullException());
        when(parkingLot_2.isFull()).thenReturn(false,true);
        when(parkingLot_2.park(car_1)).thenReturn(new Receipt());
        //when
        parkingBoy.park(car_1);
        //then
        Mockito.verify(parkingLot_2).park(car_1);

    }

    @Test
    public void should_park_failed_given_parking_when_given_one_full_parkingLot() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot_1);
        when(parkingLot_1.isFull()).thenReturn(false, true);

        doThrow(new ParkingLotFullException()).when(parkingLot_1).park(car_1);
        //when
        try {
            parkingBoy.park(car_1);
            parkingBoy.park(car_2);
            //then
            fail("should park successfully");
        } catch (ParkingBoyFullException exception) {

        }


    }

    @Test
    public void should_park_failed_given_parking_when_given_multiply_full_parkingLot() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot_1);
        parkingBoy.addParkingLot(parkingLot_2);
        when(parkingLot_1.isFull()).thenReturn(true);
        when(parkingLot_2.isFull()).thenReturn(true);

        //when
        try {
            parkingBoy.park(car_1);
            parkingBoy.park(car_2);
            //then
            fail("should park successfully");
        } catch (ParkingBoyFullException exception) {

        }


    }

    @Test
    public void should_unpark_successfully_when_given_right_receipt() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot_1);
        Receipt receipt = mock(Receipt.class);
        when(parkingLot_1.park(car_1)).thenReturn(receipt);
        parkingBoy.park(car_1);
        when(parkingLot_1.isFindReceipt(receipt.getId())).thenReturn(true);
        //when
        parkingBoy.unPark(receipt.getId());

        Mockito.verify(parkingLot_1).unPark(receipt.getId());


    }

    @Test
    public void should_unpark__given_parking_when_given_multiple_full_parkingLot() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLot_1);
        parkingLots.add(parkingLot_2);
        parkingBoy.addParkingLot(parkingLots);
        when(parkingLot_1.isFull()).thenReturn(true);
        when(parkingLot_2.isFull()).thenReturn(true);
        try {
            //when
            parkingBoy.park(car_1);
            //then
            fail("should park successfully");
        } catch (ParkingBoyFullException exception) {

        }


    }

    @Test
    public void should_return_the_verify_success_in_order_when_park_two_cars() {
        //given
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
        parkingLots.add(parkingLot_1);
        parkingLots.add(parkingLot_2);
        parkingBoy.addParkingLot(parkingLots);
        when(parkingLot_1.isFull()).thenReturn(false, true);
        when(parkingLot_2.isFull()).thenReturn(false);
        //when
        when(parkingLot_1.park(car_1)).thenReturn(new Receipt());
        when(parkingLot_1.park(car_2)).thenThrow(new ParkingLotFullException());
        parkingBoy.park(car_1);
        when(parkingLot_2.park(car_2)).thenReturn(new Receipt());
        parkingBoy.park(car_2);
        //then
        InOrder inOrder = inOrder(parkingLot_1, parkingLot_2);
        inOrder.verify(parkingLot_1).park(car_1);
        inOrder.verify(parkingLot_2).park(car_2);

    }

    @Test
    public void should_catch_exception_when_call_unPark_given_receipt_is_not_exist() {
        //given
        ParkingBoy parkingBoy = new ParkingBoy();
        parkingBoy.addParkingLot(parkingLot_1);
        String receiptId = new Receipt().getId();
        when(parkingLot_1.isFindReceipt(receiptId)).thenReturn(false);

        try {
            //when
            parkingBoy.unPark(receiptId);
            //then
            fail("should receipt is correct");
        } catch (ReceiptIsNotExistException exception) {

        }
    }


}
