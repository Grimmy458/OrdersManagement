package com.OrdersManagement.formatters;

import com.OrdersManagement.dto.DeliveryInfoDTO;

public class DeliveryInfoFormatter {
    public static String formatToConsole(DeliveryInfoDTO deliveryInfoDTO) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("\t").append("Adress: ").append(deliveryInfoDTO.getAddress()).append("\n");
        sb.append("\t").append("Price: ").append(deliveryInfoDTO.getPrice());
        return sb.toString();
    }
}
