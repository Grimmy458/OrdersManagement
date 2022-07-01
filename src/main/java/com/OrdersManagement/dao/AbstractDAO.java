package com.OrdersManagement.dao;

import java.util.ArrayList;

public abstract class AbstractDAO <T> {
    public abstract void create(T entity) throws DAOException;
    public abstract ArrayList<T> getAll() throws DAOException;
    public abstract T getById(long id) throws DAOException;
    public abstract void update(T entity) throws DAOException;
    public abstract void delete(T entity) throws DAOException;
    public abstract void load() throws DAOException;
    public abstract void save() throws DAOException;
}
