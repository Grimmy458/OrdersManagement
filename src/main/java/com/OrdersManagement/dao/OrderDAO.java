package com.OrdersManagement.dao;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.OrdersManagement.entity.Order;
import com.OrdersManagement.utils.FileUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class OrderDAO extends AbstractDAO<Order> {
    private ArrayList<Order> orders = new ArrayList<>();
    private boolean loaded = false;

    private  String fileName;
    public OrderDAO() {
        super();
        fileName = "./data/orders.json";
    }

    public OrderDAO(String fileName) {
        super();
        this.fileName = fileName;
    }

    public void create(Order entity) throws DAOException {
        if (getById(entity.getId()) != null) {
            throw new DAOException("Замовлення з таким id("+ entity.getId()+") вже існує");
        }
        // створює нове замовлення
        orders.add(entity);
    }

    public void update(Order entity) {
        // оновлює замовлення за його id
        for (Order order : orders) {
            if (order.getId() == entity.getId()) {
                order.setItems(entity.getItems());
                order.setDeliveryInfo(entity.getDeliveryInfo());
                order.setOrderDate(entity.getOrderDate());
                order.setStatus(entity.getStatus());
                break;
            }
        }
    }

    public void delete(Order entity) {
        // видаляє замовлення
        orders.remove(entity);
    }

    public Order getById(long id) {
        // повертає замовлення за його id
        for (Order order : orders) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }
    public long getNextId() {
        // повертає наступний id
        if (orders.size() == 0) {
            return 0;
        }
        long maxId = 0;
        for (Order order : orders) {
            if (order.getId() > maxId) {
                maxId = order.getId();
            }
        }
        return maxId + 1;
    }

    public ArrayList<Order> getAll() {
        // повертає всі замовлення
        return orders;
    }

    public boolean isLoaded() {
        return loaded;
    }
    public void load() throws DAOException {
        try {
            this.orders = FileUtils.ReadJsonAndCast(this.fileName, new TypeToken<ArrayList<Order>>() {});
            this.loaded = true;
        } catch (FileNotFoundException e) {
            throw new DAOFileNotFoundException("Файл не знайдено", e);
        } catch (IOException e) {
            throw new DAOException("Помилка вводу-виводу", e);
        } catch (JsonParseException e) {
            throw new DAOException("Помилка парсингу JSON", e);
        } catch (Exception e) {
            throw new DAOException("Помилка перетворення даних", e);
        }
    }

    public void save() throws DAOException {
        try {
            FileUtils.WriteJson(fileName, orders);
        } catch (FileNotFoundException e) {
            throw new DAOException("Файл не знайдено", e);
        } catch (IOException e) {
            throw new DAOException("Помилка вводу-виводу", e);
        } catch (JsonParseException e) {
            throw new DAOException("Помилка парсингу JSON", e);
        } catch (Exception e) {
            throw new DAOException("Помилка перетворення даних", e);
        }
    }
}
