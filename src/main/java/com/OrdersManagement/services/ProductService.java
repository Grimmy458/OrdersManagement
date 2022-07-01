package com.OrdersManagement.services;

import com.OrdersManagement.dao.DAOException;
import com.OrdersManagement.dao.ProductDAO;
import com.OrdersManagement.entity.Product;
import com.OrdersManagement.exceptions.ValidationException;

import java.util.ArrayList;

public class ProductService {
    ProductDAO productDAO;

    public ProductService( ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public Product getById(long id)  {
        return productDAO.getById(id);
    }

    public ArrayList<Product> getAllProducts() {
        return productDAO.getAll();
    }

    public long  createProduct(Product product) throws ValidationException {
        try {
            long nextId = productDAO.getNextId();
            product.setId(nextId);
            productDAO.create(product);
            productDAO.save();
            return nextId;
        } catch (DAOException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    public void updateProduct(Product product) throws ValidationException {
        try {
            productDAO.update(product);
            productDAO.save();
        } catch (DAOException e) {
            throw new ValidationException(e.getMessage());
        }
    }

}
