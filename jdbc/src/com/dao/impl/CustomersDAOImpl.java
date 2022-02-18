package com.dao.impl;

import com.bean.Customer;
import com.dao.BaseDAO;
import com.dao.CustomersDAO;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @author chenjianglin
 * @date 2021/8/14
 * @since 1.0.0
 **/
public class CustomersDAOImpl extends BaseDAO<Customer> implements CustomersDAO {
    @Override
    public void insert(Connection connection, Customer customer) {
        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth());
    }

    @Override
    public void update(Connection connection, Customer customer) {
        String sql = "update customers set name=?,email=?,birth=? where id =?";
        update(connection, sql, customer.getName(), customer.getEmail(), customer.getBirth(), customer.getId());
    }

    @Override
    public void deleteById(Connection connection, int id) {
        String sql = "delete from customers where id = ?";
        update(connection, sql, id);
    }

    @Override
    public Customer selectById(Connection connection, int id) {
        String sql = "select `id` id,`name` name, `email` email,`birth` birth from customers where id = ?";
        return selectSingle(connection, sql, id);
    }

    @Override
    public List<Customer> selectList(Connection connection) {
        String sql = "select `id` id,`name` name, `email` email,`birth` from customers";
        return selectList(connection, sql);
    }

    @Override
    public long executeFunction(Connection connection) {
        String sql = "select count(*) from customers";
        return SQLFunction(connection, sql);
    }

    @Override
    public Date getMaxBirth(Connection connection) {
        String sql = "select max(birth) from customers";
        return SQLFunction(connection, sql);
    }
}
