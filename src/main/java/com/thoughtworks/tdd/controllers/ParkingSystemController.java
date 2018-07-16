package com.thoughtworks.tdd.controllers;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.ParkingBoy;
import com.thoughtworks.tdd.core.Receipt;
import com.thoughtworks.tdd.core.Response;
import com.thoughtworks.tdd.core.exception.ParkingBoyFullException;
import com.thoughtworks.tdd.core.exception.ReceiptIsNotExistException;
import com.thoughtworks.tdd.views.ParkingSystemView;

public class ParkingSystemController {
    private final ParkingSystemView parkingSystemView;
    private ParkingBoy parkingBoy;
    private Response response;

    public ParkingSystemController(ParkingSystemView parkingSystemView, ParkingBoy parkingBoy, Response response) {
        this.parkingSystemView = parkingSystemView;
        this.parkingBoy = parkingBoy;
        this.response = response;

    }


    public void park(String command) {
        try {
            Receipt receipt = parkingBoy.park(new Car(command));
            response.send("停车成功，您的小票是：\n" + receipt.getId());
        } catch (ParkingBoyFullException exception) {
            response.send("非法操作");
        }
        mainPage();
    }


    public void unPark(String command) {
        try {
            Car car = parkingBoy.unPark(command);
            response.send("车已取出，您的车牌号是:" + car.getLisenceNum());
        } catch (ReceiptIsNotExistException exception) {
            response.send("非法小票，无法取出车，请查证后再输");
        }
        mainPage();

    }

    public void mainPage() {
        response.send("1. 停车\n2. 取车\n请输入您要进行的操作：");
    }

    public void dealInvalidPage() {
        response.send("非法指令，请查证后再输.");
        mainPage();
    }

    public String parkPage() {
        String command;
        if (parkingBoy.isParkingLotsFull()) {
            response.send("车已停满，请晚点再来");
            mainPage();
            command = "main";
        } else {
            response.send("请输入车牌号:");
            command = "park";
        }
        return command;
    }

    public void unParkPage() {
        response.send("欢迎来到取车界面\n请输入小票编号：");
    }
}
