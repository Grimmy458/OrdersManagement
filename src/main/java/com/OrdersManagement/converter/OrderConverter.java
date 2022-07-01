package com.OrdersManagement.converter;

import com.OrdersManagement.dto.OrderDTO;
import com.OrdersManagement.dto.OrderItemDTO;
import com.OrdersManagement.entity.Order;
import com.OrdersManagement.entity.OrderItem;

import java.util.ArrayList;

public class OrderConverter {

    public static OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setStatus(order.getStatus());
        if (order.getDeliveryInfo() != null) {
            orderDTO.setDeliveryInfo(DeliveryInfoConverter.toDTO(order.getDeliveryInfo()));
        }
        orderDTO.setDeliveryType(order.getDeliveryType());
        for (OrderItem item : order.getItems()) {
            orderDTO.addItem(OrderItemConverter.toDTO(item));
        }
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setTotalPrice(order.getTotalPrice());
        return orderDTO;
    }

    public static ArrayList<OrderDTO> toDTO(ArrayList<Order> orders) {
        ArrayList<OrderDTO> orderDTOs = new ArrayList<>();
        for (Order order : orders) {
            orderDTOs.add(toDTO(order));
        }
        return orderDTOs;
    }

    public static Order toEntity(OrderDTO orderDTO) {
        Order order = null;
        if (orderDTO.getId() != null) {
            order = new Order(orderDTO.getId());
        } else {
            order = new Order(0);
        }
        order.setStatus(orderDTO.getStatus());
        if (orderDTO.getDeliveryInfo() != null) {
            order.setDeliveryInfo(DeliveryInfoConverter.toEntity(orderDTO.getDeliveryInfo()));
        }
        order.setDeliveryType(orderDTO.getDeliveryType());
        for (OrderItemDTO item : orderDTO.getItems()) {
            order.addItem(OrderItemConverter.toEntity(item));
        }
        order.setOrderDate(orderDTO.getOrderDate());
        return order;
    }

}
