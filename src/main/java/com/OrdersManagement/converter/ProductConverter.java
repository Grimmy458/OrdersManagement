package com.OrdersManagement.converter;

import com.OrdersManagement.dto.ProductDTO;
import com.OrdersManagement.entity.Product;

import java.util.ArrayList;

public class ProductConverter {
    public static ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

    public static ArrayList<ProductDTO> toDTO(ArrayList<Product> products) {
        ArrayList<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(toDTO(product));
        }
        return productDTOs;
    }

    public static Product toEntity(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice());
    }
}
