package com.OrdersManagement.dto;

import java.math.BigDecimal;

public class DeliveryInfoDTO {
    private String address;
    private BigDecimal price;

    public DeliveryInfoDTO() {
    }

    public DeliveryInfoDTO(String address, BigDecimal price) {
        this.address = address;
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
