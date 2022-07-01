package com.OrdersManagement.enums;

public enum DeliveryType {
    SELF_PICKUP("Self Pickup"),
    DELIVERY("Delivery"),
    INSIDE("Inside");

    private final String name;
    DeliveryType(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return name;
    }
}
