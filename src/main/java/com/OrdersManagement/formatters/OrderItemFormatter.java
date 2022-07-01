package com.OrdersManagement.formatters;

import com.OrdersManagement.dto.OrderItemDTO;

public class OrderItemFormatter {
    public static String formatToConsole(OrderItemDTO orderItem) {
        StringBuilder sb = new StringBuilder();
//        sb.append("Id: ").append(orderItem.).append("\t");
        sb.append("Product: ").append(ProductFormatter.formatToConsole(orderItem.getProduct())).append("\t");
        sb.append("Quantity: ").append(orderItem.getQuantity());
        return sb.toString();
    }
}
