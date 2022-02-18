package com.dbutils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.bean.Customer;
import com.util.JDBCUtils2;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/8/14
 * @since 1.0.0
 **/
public class QueryRunnerTest {

    @Test
    public void testConnect() {
        Connection connection = null;
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties"));
            DataSource druidDataSource = DruidDataSourceFactory.createDataSource(properties);
            connection = druidDataSource.getConnection();
            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils2.closeConnection(connection, null);
        }
    }

    @Test
    public void testInsert() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils2.getConnection3();
            String sql = "insert into customers(name,email,birth)values(?,?,?)";
            int insertCount = queryRunner.update(connection, sql, "张哲瀚", "zzh@qq.com", "1997-08-09");
            System.out.println("添加了" + insertCount + "条记录");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils2.closeConnection(connection, null);
        }
    }

    @Test
    public void testSelect1() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils2.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";
            BeanHandler<Customer> customerBeanHandler = new BeanHandler<>(Customer.class);
            Customer customer = queryRunner.query(connection, sql, customerBeanHandler, 23);
            System.out.println(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils2.closeConnection(connection, null);
        }
    }

    @Test
    public void testSelect2() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils2.getConnection3();
            String sql = "select id,name,email,birth from customers ";
            BeanListHandler<Customer> customerBeanHandler = new BeanListHandler<>(Customer.class);
            List<Customer> customerList = queryRunner.query(connection, sql, customerBeanHandler);
            customerList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils2.closeConnection(connection, null);
        }
    }

    @Test
    public void testSelect3() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils2.getConnection3();
            String sql = "select id,name,email,birth from customers where id = ?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = queryRunner.query(connection, sql, handler, 23);
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils2.closeConnection(connection, null);
        }
    }
    @Test
    public void testSelect4() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils2.getConnection3();
            String sql = "select id,name,email,birth from customers where id < ?";
            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> mapList = queryRunner.query(connection, sql, handler, 23);
            mapList.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils2.closeConnection(connection, null);
        }
    }

    @Test
    public void testSelect5() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils2.getConnection3();
            String sql = "select count(*) from customers ";
            ScalarHandler handler = new ScalarHandler<>();
            Object query = queryRunner.query(connection, sql, handler);
            System.out.println(query);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils2.closeConnection(connection, null);
        }
    }

    @Test
    public void testSelect6() {
        Connection connection = null;
        try {
            QueryRunner queryRunner = new QueryRunner();
            connection = JDBCUtils2.getConnection3();
            String sql = "select max(birth) from customers ";
            ScalarHandler handler = new ScalarHandler<>();
            Object query = queryRunner.query(connection, sql, handler);
            System.out.println(query);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils2.closeConnection(connection, null);
        }
    }
}
