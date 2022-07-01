package com.OrdersManagement.dao;

import com.OrdersManagement.entity.Order;
import com.OrdersManagement.utils.FileUtils;
import org.junit.Test;

public class OrderDAOTest {
    private final String TEST_FILE = "./data/test/orders.json";
    private final OrderDAO orderDAO = new OrderDAO(TEST_FILE);

    @Test()
    public void test() throws DAOException {
//        OrderDAO orderDAO = new OrderDAO("./data/test/orders.json");
        FileUtils.deleteFile(TEST_FILE);
        try {
            orderDAO.load();
        } catch (DAOFileNotFoundException e) {
            orderDAO.save();
            orderDAO.load();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }
        assert orderDAO.isLoaded();
        Order order = orderDAO.getById(-1);
        if (order != null) {
            orderDAO.delete(order);
        }
        assert orderDAO.getById(-1) == null;
        order = new Order(-1);
        orderDAO.create(order);
        assert orderDAO.getById(-1) == order;
        orderDAO.save();
        orderDAO.delete(order);
        assert orderDAO.getById(-1) == null;
        orderDAO.load();
        assert orderDAO.getById(-1) != null;
        Order order2 = orderDAO.getById(-1);
        assert order2.equals(order);
        FileUtils.deleteFile(TEST_FILE);
    }

}
