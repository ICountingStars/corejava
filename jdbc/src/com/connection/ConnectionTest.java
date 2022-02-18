package com.connection;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC链接方式
 *
 * @author chenjianglin
 * @date 2021/8/13
 * @since 1.0.0
 **/
public class ConnectionTest {

    // 方式一
    @Test
    public void testConnection1() throws SQLException {
        Driver driver = new com.mysql.jdbc.Driver();
        String URL = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "");
        Connection connect = driver.connect(URL, info);
        System.out.println(connect);
    }

    // 方式二 反射实现
    @Test
    public void testConnection2() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        // Driver实现类对象,使用反射
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        String URL = "jdbc:mysql://localhost:3306/test";
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "");

        Connection connect = driver.connect(URL, info);

        System.out.println(connect);
    }

    // 方式三 DriverManager方式
    @Test
    public void testConnection3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        Class clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        // 获取到Driver的实现类对象：使用反射
        String URL = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "";
        DriverManager.registerDriver(driver);
        // 获取链接
        Connection connection = DriverManager.getConnection(URL, user, password);
        System.out.println(connection);
    }

    // 方式四 ： 优化方式三
    @Test
    public void testConnection4() throws ClassNotFoundException, SQLException {
        // 链接信息
        String URL = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "";

        // 相较于方式三 可以省略书写注册驱动，原因是
//        mysql Driver的 源码里 有个静态方法类启动是注册驱动
        /**
         * public class Driver extends NonRegisteringDriver implements java.sql.Driver {
         *     //
         *     // Register ourselves with the DriverManager
         *     //
         *     static {
         *         try {
         *             java.sql.DriverManager.registerDriver(new Driver());
         *         } catch (SQLException E) {
         *             throw new RuntimeException("Can't register driver!");
         *         }
         *     }
         *     ...
         * }
         */
        Class.forName("com.mysql.jdbc.Driver");
        // 获取驱动
        Connection connection = DriverManager.getConnection(URL, user, password);
        System.out.println(connection);
    }

    // 最终版 读取配置方式  **重点**
    @Test
    public void testConnection() throws IOException, ClassNotFoundException, SQLException {
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
        Class.forName(driverClass);
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("select * from t_mdc_brand");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            System.out.println(resultSet.getObject(1));
        }

        System.out.println(connection);
    }
}
