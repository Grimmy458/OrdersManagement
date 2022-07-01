package com.OrdersManagement.dto;

import com.OrdersManagement.enums.DeliveryType;
import com.OrdersManagement.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
public class OrderDTO {
    private Long id;
    private OrderStatus status;
    private DeliveryInfoDTO deliveryInfo;
    private DeliveryType deliveryType;
    private ArrayList<OrderItemDTO> items = new ArrayList<>();
    private Date orderDate;

    private BigDecimal totalPrice;

    public OrderDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public DeliveryInfoDTO getDeliveryInfo() {
        return deliveryInfo;
    }

    public void setDeliveryInfo(DeliveryInfoDTO deliveryInfo) {
        this.deliveryInfo = deliveryInfo;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(DeliveryType deliveryType) {
        this.deliveryType = deliveryType;
    }

    public ArrayList<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderItemDTO> items) {
        this.items = items;
    }
    public void addItem(OrderItemDTO item) {
        items.add(item);
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
}
