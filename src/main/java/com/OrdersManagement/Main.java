package com.OrdersManagement;

import com.OrdersManagement.dao.DAOException;
import com.OrdersManagement.dao.OrderDAO;
import com.OrdersManagement.dao.ProductDAO;
import com.OrdersManagement.services.OrderService;
import com.OrdersManagement.controller.MainController;
import com.OrdersManagement.view.MainView;
import com.OrdersManagement.services.ProductService;

public class Main {

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO("./data/orders.json");
        try {
            orderDAO.load();
        } catch (DAOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        ProductDAO productDAO = new ProductDAO("./data/products.json");
        try {
            productDAO.load();
        } catch (DAOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        ProductService productService = new ProductService(productDAO);
        OrderService orderService = new OrderService(orderDAO, productService);

        MainView view = new MainView();
        MainController controller = new MainController(view, productService, orderService);
        view.setController(controller);
        view.show();
    }
}