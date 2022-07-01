package com.OrdersManagement.enums;

public enum OrderStatus {
    NEW("NEW"),
    COOKING("COOKING"),
    WAIT_RELEASE("WAIT RELEASE"),
    DELIVERY("DELIVERY"),
    FINISHED("FINISHED"),
    CANCELED("CANCELED");

    private final String name;
    OrderStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
