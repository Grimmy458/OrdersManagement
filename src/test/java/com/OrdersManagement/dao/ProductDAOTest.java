package com.OrdersManagement.dao;

import com.OrdersManagement.entity.Product;
import com.OrdersManagement.utils.FileUtils;
import org.junit.Test;

import java.math.BigDecimal;

public class ProductDAOTest {
    private final String TEST_FILE = "./data/test/products.json";
    private final ProductDAO productDAO = new ProductDAO(TEST_FILE);
    @Test
    public void test() throws DAOException {
        FileUtils.deleteFile(TEST_FILE);
        try {
            productDAO.load();
        } catch (DAOFileNotFoundException e) {
            productDAO.save();
            productDAO.load();
        } catch (DAOException e) {
            throw new RuntimeException(e);
        }

        assert productDAO.isLoaded();
        Product product = productDAO.getById(-1);
        if (product != null) {
            productDAO.delete(product);
        }
        assert productDAO.getById(-1) == null;
        product = new Product(-1, "test", new BigDecimal(10));
        productDAO.create(product);
        assert productDAO.getById(-1) == product;
        productDAO.save();
        productDAO.delete(product);
        assert productDAO.getById(-1) == null;
        productDAO.load();
        assert productDAO.getById(-1) != null;
        Product product2 = productDAO.getById(-1);
        assert product2.equals(product);
        FileUtils.deleteFile(TEST_FILE);
    }
}
