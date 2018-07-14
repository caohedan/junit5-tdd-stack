package com.thoughtworks.tdd.core;

import java.util.UUID;

public class Receipt {
    private String id;

    public Receipt() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        this.id = randomUUIDString;
    }
}
