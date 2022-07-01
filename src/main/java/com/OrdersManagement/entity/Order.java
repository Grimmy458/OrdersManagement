package com.OrdersManagement.entity;

import com.OrdersManagement.enums.DeliveryType;
import com.OrdersManagement.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Order {
    private long id;
    private OrderStatus status;
    private ArrayList<OrderItem> items;
    private Date orderDate;
    private DeliveryInfo deliveryInfo;
    private DeliveryType deliveryType;

    public Order(long id) {
        this.id = id;
        this.status = OrderStatus.NEW;
        this.items = new ArrayList<>();
        this.orderDate = new Date();
        this.deliveryType = null;
        this.deliveryInfo = null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public ArrayList<OrderItem> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public DeliveryInfo getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return getId() == order.getId() && getStatus() == order.getStatus() && getItems().equals(order.getItems()) && Objects.equals(getDeliveryInfo(), order.getDeliveryInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStatus(), getItems(), getDeliveryInfo());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", items=" + items +
                ", orderDate=" + orderDate +
                ", deliveryInfo=" + deliveryInfo +
                ", price=" + getTotalPrice() +
                '}';
    }

    public BigDecimal getTotalPrice() {
        // повертає загальну ціну замовлення
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItem item : items) {
            totalPrice = totalPrice.add(item.getTotalPrice());
        }
        if(this.deliveryInfo != null){
            totalPrice = totalPrice.add(deliveryInfo.getPrice());
        }
        return totalPrice;
    }

}
