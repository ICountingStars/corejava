package com.dao;

import com.bean.Customer;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

/**
 * @author chenjianglin
 * @date 2021/8/14
 * @since 1.0.0
 **/
public interface CustomersDAO {
    /**
     * 插入一条数据到数据库
     *
     * @param connection 数据库链接
     * @param customer   消费者
     */
    void insert(Connection connection, Customer customer);

    /**
     * 根据ID更新一条数据
     *
     * @param connection 数据库链接
     * @param customer   消费者更新的记录
     */
    void update(Connection connection,Customer customer);

    /**
     * 根据ID删除一条数据
     *
     * @param connection 数据库链接
     * @param id         数据库主键
     */
    void deleteById(Connection connection, int id);

    /**
     * 根据ID查询一条记录
     *
     * @param connection 数据库链接
     * @param id         数据库主键
     * @return 对应的记录
     */
    Customer selectById(Connection connection, int id);

    /**
     * 查询数据库表中的所有记录
     *
     * @param connection 数据库链接
     * @return customer表中的所有记录
     */
    List<Customer> selectList(Connection connection);

    /**
     * 返回表中数据的条目数
     *
     * @param connection 数据库链接
     * @return 返回的条数
     */
    long executeFunction(Connection connection);

    /**
     * 返回数据库表中最大的生日
     *
     * @return 最大的出生日期
     */
    Date getMaxBirth(Connection connection);
}
