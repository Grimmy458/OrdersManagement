package com.OrdersManagement.converter;

import com.OrdersManagement.dto.OrderItemDTO;
import com.OrdersManagement.entity.OrderItem;

public class OrderItemConverter {
    public static OrderItemDTO toDTO(OrderItem orderItem) {
        OrderItemDTO orderItemDTO = new OrderItemDTO();
        orderItemDTO.setProduct(ProductConverter.toDTO(orderItem.getProduct()));
        orderItemDTO.setQuantity(orderItem.getQuantity());
        return orderItemDTO;
    }
    public static OrderItem toEntity(OrderItemDTO orderItemDTO) {
        return new OrderItem(
                ProductConverter.toEntity(orderItemDTO.getProduct()),
                orderItemDTO.getQuantity()
        );
    }
}
