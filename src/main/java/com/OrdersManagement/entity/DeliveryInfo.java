package com.OrdersManagement.entity;


import java.math.BigDecimal;

public class DeliveryInfo {
    private String address;
    private BigDecimal price;


    public DeliveryInfo(String address, BigDecimal price) {
        this.address = address;
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getPrice() {
        return price;
    }


}
