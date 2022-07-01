package com.OrdersManagement.entity;

import java.math.BigDecimal;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

    public String toString() {
        return product.getName() + "x" + quantity + " = " + getTotalPrice();
    }
}
