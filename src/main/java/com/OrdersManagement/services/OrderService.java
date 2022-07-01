package com.OrdersManagement.services;

import com.OrdersManagement.exceptions.ValidationException;
import com.OrdersManagement.utils.print.CSVPrinter;
import com.OrdersManagement.dao.DAOException;
import com.OrdersManagement.dao.OrderDAO;
import com.OrdersManagement.entity.Order;
import com.OrdersManagement.enums.OrderStatus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class OrderService {
    private OrderDAO orderDAO;
    private ProductService productService;

    public OrderService(OrderDAO orderDAO, ProductService productService) {
        this.orderDAO = orderDAO;
        this.productService = productService;
    }

    public long createOrder(Order order) throws ValidationException {
        try {
            long nextId = orderDAO.getNextId();
            order.setId(nextId);
            order.setStatus(OrderStatus.NEW);
            order.setOrderDate(new Date());
            orderDAO.create(order);
            orderDAO.save();
            return nextId;
        } catch (DAOException e) {
            throw new ValidationException(e.getMessage());
        }
    }
    public ArrayList<Order> getAllOrders() {
        return orderDAO.getAll();
    }

    public Order getById(long id) {
        return orderDAO.getById(id);
    }

    public void updateOrder(Order order) throws ValidationException {
        try {
            orderDAO.update(order);
            orderDAO.save();
        } catch (DAOException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    public void downloadOrders(String fileName) throws IOException {
        ArrayList<Order> orders = orderDAO.getAll();
        ArrayList<ArrayList<String>> ordersData = new ArrayList<>();
        if (orders != null) {
            ordersData = new ArrayList<>();
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                ArrayList<String> orderData = new ArrayList<>();
                orderData.add(order.getId() + "");
                orderData.add(order.getStatus().toString());
                orderData.add(order.getOrderDate().toString());
                orderData.add(order.getDeliveryType().toString());

                orderData.add(order.getDeliveryInfo().getAddress());
                orderData.add(order.getDeliveryInfo().getPrice().toString());
                orderData.add(order.getTotalPrice().toString());
                ordersData.add(orderData);
            }
        }

        CSVPrinter.print(fileName, new String[]{
                "orderId",
                "status",
                "orderDate",
                "deliveryType",
                "deliveryAdress",
                "deliveryPrice", "totalPrice"},
                ordersData);
    }

}
