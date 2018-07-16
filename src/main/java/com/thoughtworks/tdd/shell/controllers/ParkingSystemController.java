package com.thoughtworks.tdd.shell.controllers;

import com.thoughtworks.tdd.core.*;
import com.thoughtworks.tdd.core.exception.ParkingBoyFullException;
import com.thoughtworks.tdd.core.exception.ReceiptIsNotExistException;
import com.thoughtworks.tdd.shell.io.Response;

import java.util.List;

public class ParkingSystemController {
    private ParkingBoy parkingBoy;
    private Response response;

    public ParkingSystemController(ParkingBoy parkingBoy, Response response) {
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
        response.send("1.停车服务\n" + "2.停车场管理\n" + "请输入您要进入的页面：");
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

    public String parkAndUnParkPage() {
        response.send("1. 停车\n2. 取车\n请输入您要进行的操作：");
        return "park_and_unpark";
    }

    public String check_and_delete_parkingLot() {
        response.send("1.查看停车场详情\n" + "2.添加停车场\n" + "3.删除停车场" + "请输入您要进入的页面：");
        return "check_and_delete_parkingLot";
    }

    public String checkPakingLotPage() {
        String strInfo = "进入停车场详情：\n" + "|停车场ID|名称|车位|已停车辆|剩余车位|\n" + "======================================\n";
        List<ParkingLot> list = parkingBoy.getParkingLots();
        int total = 0;
        int parkNum = 0;
        for (ParkingLot p : list) {
            total += p.getSize();
            parkNum += p.getParkNum();
            strInfo += "|" + p.getId() + "|" + p.getName() + "|" + p.getSize() + "(个)|" + p.getParkNum() + "(辆)|" + (p.getSize() - p.getParkNum()) + "(个)|\n";
        }
        strInfo += "\n总车位：" + total + "(个)\n" + "停车总量：" + parkNum + "（辆）\n" + "总剩余车位：" + (total - parkNum) + "（个）";
        response.send(strInfo);
        mainPage();
        return "main";
    }


    public String addParkingLotPage() {
        response.send("请输入你套添加的停车场信息（格式为：名称，车位）：");
        return "add_parking_lot";
    }

    public void addParkingLot(String command) {
        try {
            String[] strList = command.split(",");
            String name = strList[0];
            int size = Integer.parseInt(strList[1]);
            parkingBoy.addParkingLot(name, size);
            response.send("停车场添加成功！");
        } catch (ArrayIndexOutOfBoundsException exception) {
            response.send("输入格式不正确");
        } finally {
            mainPage();
        }
    }

    public String deleteParkingLotPage() {
        response.send("请输入需要删除的被管理停车场ID:");
        return "delete_parking_lot";
    }

    public void delteParkingLot(String command) {
        if (parkingBoy.findParingLot(command) == null) {
            response.send("此停车场不存在！");
        } else {
            ParkingLot parkingLot = parkingBoy.findParingLot(command);
            if (parkingLot.getParkNum()> 0) {
                response.send("此停车场中，依然停有汽车，无法删除！");
            } else {
                parkingBoy.removeParkingLot(parkingLot);
                response.send("删除成功");
            }
        }
        mainPage();
    }
}
