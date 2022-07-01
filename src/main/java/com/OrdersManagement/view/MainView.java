package com.OrdersManagement.view;

import com.OrdersManagement.controller.MainController;
import com.OrdersManagement.dto.DeliveryInfoDTO;
import com.OrdersManagement.dto.OrderDTO;
import com.OrdersManagement.dto.OrderItemDTO;
import com.OrdersManagement.dto.ProductDTO;
import com.OrdersManagement.enums.DeliveryType;
import com.OrdersManagement.enums.OrderStatus;
import com.OrdersManagement.exceptions.ValidationException;
import com.OrdersManagement.formatters.OrderFormatter;
import com.OrdersManagement.formatters.ProductFormatter;
import com.OrdersManagement.utils.UI.ActionView;
import com.OrdersManagement.utils.UI.MenuView;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Objects;
public class MainView {
    MainController controller;
    ActionView displayAllProductsAction;
    ActionView displayAllOrdersAction;
    ActionView createOrderAction;
    ActionView editProductAction;
    ActionView createProductAction;
    ActionView changeOrderStatusAction;
    ActionView downloadOrdersAction;
    public MainView() {
        //ініціаліалізція "команд" для відображення даних
        this.displayAllProductsAction = new ActionView("Display all products", "Display all products") {
            @Override
            public void executeAction() {
                try {
                    ArrayList<ProductDTO> products = controller.getAllProducts();
                    for (ProductDTO product :  products) {
                        this.println(ProductFormatter.formatToConsole(product));
                    }
                } catch (ValidationException e) {
                    this.println(e.getMessage());
                }
            }
        };
        this.displayAllOrdersAction = new ActionView("Display all orders", "Display all orders") {
            @Override
            public void executeAction() {
                ArrayList<OrderDTO> orders = controller.getAllOrders();
                if (orders == null) {
                    return;
                }
                if (orders.isEmpty()) {
                    this.println("No orders");
                    return;
                }
                this.println("========================================================================================");
                for (OrderDTO order : orders) {
                    this.println(OrderFormatter.formatToConsole(order));
                    this.println("========================================================================================");
                }
            }
        };
        this.createOrderAction = new ActionView("Create order", "Create order") {
            @Override
            public void executeAction() {
                OrderDTO orderDTO = new OrderDTO();
                while (true){
                    long productId = this.prompt("Enter product id: -1 to exit", Long.class);
                    if (productId == -1) {
                        return;
                    }
                    if (productId == 0){
                        break;
                    }
                    int quantity = this.prompt("Enter quantity: -1 to exit", Integer.class);
                    if (quantity == -1) {
                        return;
                    }
                    if (quantity == 0){
                        break;
                    }

                    ProductDTO product = controller.getProductById(productId);
                    if (product == null) {
                        this.println("Product not found");
                        continue;
                    }
                    orderDTO.addItem(new OrderItemDTO(product, quantity));
                    this.println("Product added");


                }

                for (int i = 0; i < DeliveryType.values().length; i++) {
                    this.println((i+1) + " - " + DeliveryType.values()[i].toString());
                }
                int deliveryType = this.prompt("Enter delivery type: -1 to exit", Integer.class);
                if (deliveryType == -1) {
                    return;
                }
                DeliveryType deliveryTypeEnum = DeliveryType.values()[deliveryType-1];
                orderDTO.setDeliveryType(deliveryTypeEnum);
                DeliveryInfoDTO deliveryInfo = null;
                if (deliveryTypeEnum == DeliveryType.DELIVERY) {

                    String deliveryAddress = this.prompt("Enter Address: -1 to exit", String.class);
                    if (Objects.equals(deliveryAddress, "-1")) {
                        return;
                    }
                    BigDecimal deliveryCost = this.prompt("Enter delivery cost: -1 to exit", BigDecimal.class);
                    if (deliveryCost.intValue() == -1) {
                        return;
                    }

                    deliveryInfo = new DeliveryInfoDTO(deliveryAddress, deliveryCost);
                    orderDTO.setDeliveryInfo(deliveryInfo);
                }
                try {
                    controller.createOrder(orderDTO);
                } catch (ValidationException e) {
                    this.println(e.getMessage());
                }
            }
        };
        this.createProductAction = new ActionView("Create product", "Create product") {
            @Override
            public void executeAction() {
                String name = this.prompt("Enter name: -1 to exit", String.class);
                if (Objects.equals(name, "-1")) {
                    return;
                }
                BigDecimal price = this.prompt("Enter price: -1 to exit", BigDecimal.class);
                if (price.intValue() == -1) {
                    return;
                }
                ProductDTO product = new ProductDTO(0, name, price);
                try {
                    long productId = controller.createProduct(product);
                    this.println("Product created with id: " + productId);
                } catch (ValidationException e) {
                    this.println(e.getMessage());
                }
                this.println("Product created");
            }
        };
        this.editProductAction = new ActionView("Edit product", "Edit product") {
            @Override
            public void executeAction() {
                long productId = this.prompt("Enter product id: -1 to exit", Long.class);
                if (productId == -1) {
                    return;
                }
                ProductDTO product = controller.getProductById(productId);
                if (product == null) {
                    this.println("Product not found");
                    return;
                }
//              Display product info
                this.println(ProductFormatter.formatToConsole(product));

                String name = this.prompt("Enter name: -1 to exit", String.class, product.getName());
                if (Objects.equals(name, "-1")) {
                    return;
                }
                BigDecimal price = this.prompt("Enter price: -1 to exit", BigDecimal.class, product.getPrice());
                if (price.intValue() == -1) {
                    return;
                }
                product.setName(name);
                product.setPrice(price);
                try {
                    controller.updateProduct(product);
                } catch (ValidationException e) {
                    this.println(e.getMessage());
                }
                this.println("Product updated");
            }
        };
        this.changeOrderStatusAction = new ActionView("Change order status", "Change order status") {
            @Override
            public void executeAction() {
                long orderId = this.prompt("Enter order id: -1 to exit", Long.class);
                if (orderId == -1) {
                    return;
                }
                OrderDTO order = controller.getOrderById(orderId);
                if (order == null) {
                    this.println("Order not found");
                    return;
                }
                for (int i = 0; i < OrderStatus.values().length; i++) {
                    this.println((i+1) + " - " + OrderStatus.values()[i].toString());
                }
                int orderStatus = this.prompt("Enter order status: -1 to exit", Integer.class);
                if (orderStatus == -1) {
                    return;
                }
                OrderStatus orderStatusEnum = OrderStatus.values()[orderStatus-1];
                order.setStatus(orderStatusEnum);
                try {
                    controller.updateOrder(order);
                } catch (ValidationException e) {
                    this.println(e.getMessage());
                }
            }
        };
        this.downloadOrdersAction = new ActionView("Download orders", "Download orders") {
            @Override
            public void executeAction() {
//                Asks for the file name to save the orders to
                String fileName = this.prompt("Enter file name: -1 to exit", String.class);
                if (Objects.equals(fileName, "-1")) {
                    return;
                }
                try {
                    controller.downloadOrders(fileName);
                } catch (IOException e) {
                    this.println(e.getMessage());
                    this.println("Could not download orders");
                }
            }
        };
    }
    public void show() {
        MenuView mainMenuView = new MenuView("Main menu", "Main menu");
        mainMenuView.addMenuView(displayAllProductsAction);
        mainMenuView.addMenuView(displayAllOrdersAction);
        mainMenuView.addMenuView(createOrderAction);
        mainMenuView.addMenuView(createProductAction);
        mainMenuView.addMenuView(editProductAction);
        mainMenuView.addMenuView(changeOrderStatusAction);
        mainMenuView.addMenuView(downloadOrdersAction);
        mainMenuView.show();
    }
    public void setController(MainController controller) {
        this.controller = controller;
    }
}
