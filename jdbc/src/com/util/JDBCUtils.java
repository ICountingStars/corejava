package com.util;

import java.sql.*;
import java.util.Properties;

/**
 * 操作数据库的工具类
 *
 * @author chenjianglin
 * @date 2021/8/13
 * @since 1.0.0
 **/
public class JDBCUtils {
    /**
     * 获取数据库链接
     * @return 数据库链接
     * @throws Exception 异常
     */
    public static Connection  getConnection() throws Exception {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("jdbc.properties"));
        Class.forName(properties.getProperty("driverClass"));
        return DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
    }

    /**
     * 关闭资源
     * @param connection 数据库链接
     * @param statement SQL声明
     */
    public static void closeConnection(Connection connection, Statement statement) {
        try {
            // 关闭SQL
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭资源
     * @param connection 链接
     * @param statement 声明
     * @param resultSet 结果集
     */
    public static void closeConnection(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            // 关闭SQL
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet!=null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
