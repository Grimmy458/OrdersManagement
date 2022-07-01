package com.OrdersManagement.formatters;

import com.OrdersManagement.dto.OrderDTO;
import com.OrdersManagement.dto.OrderItemDTO;

public class OrderFormatter {
    public static String formatToConsole(OrderDTO orderDTO) {
        StringBuilder sb = new StringBuilder();
        sb.append("Id: ").append(orderDTO.getId()).append("\t");
        sb.append("Status: ").append(orderDTO.getStatus()).append("\t");
        sb.append("Order date: ").append(orderDTO.getOrderDate()).append("\n");
        sb.append("Items: ").append("\n");
        for (OrderItemDTO item : orderDTO.getItems()) {
            sb.append("\t").append(OrderItemFormatter.formatToConsole(item)).append("\n");
        }
        if (orderDTO.getDeliveryInfo() != null) {
            sb.append("deliveryInfo: ").append(DeliveryInfoFormatter.formatToConsole(orderDTO.getDeliveryInfo())).append("\n");
        }else {
//            sb.append("deliveryInfo: ");
        }
        if (orderDTO.getDeliveryType() != null)
            sb.append("Delivery Type: ").append(orderDTO.getDeliveryType()).append("\n");
        sb.append("Total price: ").append(orderDTO.getTotalPrice());
        return sb.toString();
    }
}
