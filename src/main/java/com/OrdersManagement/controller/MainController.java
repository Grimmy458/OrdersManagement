package com.OrdersManagement.controller;

import com.OrdersManagement.converter.OrderConverter;
import com.OrdersManagement.converter.ProductConverter;
import com.OrdersManagement.dto.OrderDTO;
import com.OrdersManagement.dto.ProductDTO;
import com.OrdersManagement.entity.Order;
import com.OrdersManagement.entity.Product;
import com.OrdersManagement.exceptions.ValidationException;
import com.OrdersManagement.services.OrderService;
import com.OrdersManagement.services.ProductService;
import com.OrdersManagement.view.MainView;

import java.io.IOException;
import java.util.ArrayList;

public class MainController {
//    private OrderDAO orderDAO;
//    private ProductDAO productDAO;
    private MainView view;
    private ProductService productService;
    private OrderService orderService;

    public MainController(MainView view, ProductService productService, OrderService orderService) {
        this.view = view;
        this.productService = productService;
        this.orderService = orderService;
    }

    public ArrayList<ProductDTO> getAllProducts() throws ValidationException {
        ArrayList<Product> products = productService.getAllProducts();
        if (products == null) {
            throw new ValidationException("Не знайдено продуктів");
        }

        return ProductConverter.toDTO(products);
    }

    public ArrayList<OrderDTO> getAllOrders() {
        ArrayList<Order> orders = orderService.getAllOrders();
        return OrderConverter.toDTO(orders);
    }
    public ProductDTO getProductById(long id) {
        Product product = productService.getById(id);
        if (product == null)
            return null;
        return ProductConverter.toDTO(product);
    }

    public OrderDTO getOrderById(long id) {
        Order order = orderService.getById(id);
        if (order == null)
            return null;
        return OrderConverter.toDTO(order);
    }

    public long createOrder(OrderDTO orderDTO) throws ValidationException {
        Order order = OrderConverter.toEntity(orderDTO);
        return orderService.createOrder(order);
    }

    public long createProduct(ProductDTO productDTO) throws ValidationException {
        Product product = ProductConverter.toEntity(productDTO);
        return productService.createProduct(product);
    }

    public void updateProduct(ProductDTO productDTO) throws ValidationException {
        Product product = ProductConverter.toEntity(productDTO);
        productService.updateProduct(product);
    }

    public void updateOrder(OrderDTO orderDTO) throws ValidationException {
        Order order = OrderConverter.toEntity(orderDTO);
        orderService.updateOrder(order);
    }

    public void downloadOrders(String fileName) throws IOException {
        orderService.downloadOrders(fileName);
    }

}
