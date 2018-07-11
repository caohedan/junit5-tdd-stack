package com.thoughtworks.tdd;

public class Car {
    int id;
    String userName;

    public Car(int id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String displayInfo(boolean flag) {
        if (flag){
            return this.getUserName() + ",Welcome to spark!";
        }
        return "Sorry,There is no vacant parking space here!";
    }
}
