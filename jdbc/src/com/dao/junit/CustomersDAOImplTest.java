package com.dao.junit;

import com.bean.Customer;
import com.dao.impl.CustomersDAOImpl;
import com.util.JDBCUtils;
import com.util.JDBCUtils2;
import org.junit.Test;

import java.sql.Connection;
import java.util.Date;
import java.util.List;


/**
 * @author chenjianglin
 * @date 2021/8/14
 * @since 1.0.0
 **/
public class CustomersDAOImplTest {

    private CustomersDAOImpl dao = new CustomersDAOImpl();

    @Test
    public void insert() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            dao.insert(connection, new Customer(1, "cjl", "cjl@gamil.com", new Date()));
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void updateById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            dao.update(connection, new Customer(26, "cjl1", "cjl@qq.com", new Date()));
            System.out.println("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void deleteById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            dao.deleteById(connection, 26);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void selectById() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Customer customer = dao.selectById(connection, 1);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void selectList() {
        Connection connection = null;
        try {
//            connection = JDBCUtils2.getConnection();
//            connection = JDBCUtils2.getConnection2();
            connection = JDBCUtils2.getConnection3();
            List<Customer> customers = dao.selectList(connection);
            customers.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void executeFunction() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            long l = dao.executeFunction(connection);
            System.out.println("记录总数为:" + l);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void getMaxBirth() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            Date maxBirth = dao.getMaxBirth(connection);
            System.out.println("记录中最大的生日为:" + maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }
}