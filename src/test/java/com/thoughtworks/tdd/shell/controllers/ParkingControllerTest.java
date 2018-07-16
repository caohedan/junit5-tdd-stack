package com.thoughtworks.tdd.shell.controllers;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.shell.io.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ParkingControllerTest {
    private ParkingBoy parkingBoy;
    private Response response;
    private ParkingSystemController controller;
    private Car car_1;
    @BeforeEach
    public void setUp() {
       parkingBoy = mock(ParkingBoy.class);
       response = mock(Response.class);
       controller = new ParkingSystemController(parkingBoy,response);
       car_1 = mock(Car.class);

    }
    @Test
    void should_return_park_successfully_when_ParkingBoy_is_park_successfully(){
        String command = "粤L-999999";
        Receipt receipt = mock(Receipt.class);
        when(receipt.getId()).thenReturn("22-33-44");
        when(parkingBoy.park(any(Car.class))).thenReturn(receipt);

        controller.park(command);
        Mockito.verify(response).send("停车成功，您的小票是：\n" + receipt.getId());
    }
}
