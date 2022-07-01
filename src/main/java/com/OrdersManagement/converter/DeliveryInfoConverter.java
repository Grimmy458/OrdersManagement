package com.OrdersManagement.converter;

import com.OrdersManagement.dto.DeliveryInfoDTO;
import com.OrdersManagement.entity.DeliveryInfo;

public class DeliveryInfoConverter {

    public static DeliveryInfoDTO toDTO(DeliveryInfo deliveryInfo) {
        DeliveryInfoDTO deliveryInfoDTO = new DeliveryInfoDTO();
        deliveryInfoDTO.setAddress(deliveryInfo.getAddress());
        deliveryInfoDTO.setPrice(deliveryInfo.getPrice());
        return deliveryInfoDTO;
    }

    public static DeliveryInfo toEntity(DeliveryInfoDTO deliveryInfoDTO) {
        return new DeliveryInfo(deliveryInfoDTO.getAddress(), deliveryInfoDTO.getPrice());
    }

}
