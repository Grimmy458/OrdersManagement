package com.OrdersManagement.formatters;

import com.OrdersManagement.dto.ProductDTO;

public class ProductFormatter {
    public static String formatToConsole(ProductDTO productDTO) {
        String sb = "Id: " + productDTO.getId() + "\t" +
                "Name: " + productDTO.getName() + "\t" +
                "Price: " + productDTO.getPrice();
        return sb;
    }
}
