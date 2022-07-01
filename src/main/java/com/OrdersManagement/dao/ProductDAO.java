package com.OrdersManagement.dao;

import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.OrdersManagement.entity.Product;
import com.OrdersManagement.utils.FileUtils;

import java.io.*;
import java.util.ArrayList;

public class ProductDAO extends AbstractDAO<Product> {

    private String fileName = "./data/products.json";
    private ArrayList<Product> products = new ArrayList<>();
    protected boolean loaded = false;
    public ProductDAO(String fileName) {
        super();
        if (fileName == null) {
            this.fileName = "./data/products.json";
        } else {
            this.fileName = fileName;
        }
    }
    public ProductDAO() {
        super();
    }

    public void create(Product entity) throws DAOException {
        if (getById(entity.getId()) != null) {
            throw new DAOException("Продукт з таким id("+ entity.getId()+") вже існує");
        }
        // створює новий продукт
        products.add(entity);
    }

    public void update(Product entity){
        // оновлює продукт за його id
        for (Product product : products) {
            if (product.getId() == entity.getId()) {
                product.setName(entity.getName());
                product.setPrice(entity.getPrice());
                break;
            }
        }

    }

    public void delete(Product entity) {
        // видаляє продукт
        products.remove(entity);
    }


    public Product getById(long id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public ArrayList<Product> getAll(){
        return products;
    }

    public boolean isLoaded() {
        return loaded;
    }
    public void load() throws DAOException{
//        Повертає всі елементи з бази даних
        try {

            this.products = FileUtils.ReadJsonAndCast(fileName, new TypeToken<ArrayList<Product>>() {});
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

    public void save() throws DAOException{
//        Зберігає всі елементи в базі даних
//        Зробити папки якщо їх немає
        try {
            FileUtils.WriteJson(fileName, products);
        } catch (NullPointerException e) {
            throw new DAOException("Файл не знайдено", e);
        } catch (IOException e) {
            throw new DAOException("Помилка вводу-виводу", e);
        } catch (JsonParseException e) {
            throw new DAOException("Помилка парсингу JSON", e);
        } catch (Exception e) {
            throw new DAOException("Помилка перетворення даних", e);
        }
    }

    public long getNextId() {
//        Повертає наступний ідентифікатор для нового продукту
        long id = 0;
        for (Product product : products) {
            if (product.getId() > id) {
                id = product.getId();
            }
        }
        return id + 1;
    }
}
