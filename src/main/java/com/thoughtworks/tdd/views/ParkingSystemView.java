package com.thoughtworks.tdd.views;

import com.thoughtworks.tdd.core.Car;
import com.thoughtworks.tdd.core.Receipt;

public class ParkingSystemView {

        public void showBegin() {
            System.out.println("1. 停车\n2. 取车\n 请输入您要进行的操作：");
    }


    public void showUnpark() {
        System.out.println("欢迎来到取车界面\n请输入小票编号：");

    }

    public void dealIllegalInput() {
        System.out.println("非法指令，请查证后再输");
    }

    public void showStop() {
        System.out.println("欢迎来到停车界面");
    }

    public void showParkingLotIsFull() {
            System.out.println("车已停满，请晚点再来");
    }

    public void dealParkingLotIsNotFull() {
        System.out.println("请输入车牌号:");
    }

    public void showReceipt(Receipt receipt) {
        System.out.println("停车成功，您的小票是：\n" +receipt.getId());
    }

    public void showWrongReceipt() {
        System.out.println("非法小票，无法取出车，请查证后再输");
    }

    public void showTheCar(Car car) {
            System.out.println("车已取出，您的车牌号是:"+car.getLisenceNum());
    }
}
