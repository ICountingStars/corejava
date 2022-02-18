package com.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 使用数据库链接池来管理、释放数据库的链接
 *
 * @author chenjianglin
 * @date 2021/8/14
 * @since 1.0.0
 **/
public class JDBCUtils2 {
    // 提供一个C3P0数据库链接池
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("myc3p0");
    // 提供一个DBCP数据库链接池
    private static DataSource c3p0DataSource;
    // 提示一个Druid数据库链接池
    private static DataSource druidDataSource;
    static {
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties"));
            c3p0DataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    static {
        try {
            Properties properties = new Properties();
            properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties"));
            druidDataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * C3P0的数据库链接池技术
     *
     * @return 数据库链接
     * @throws Exception 异常
     */
    public static Connection getConnection() throws Exception {
        return cpds.getConnection();
    }

    /**
     * DBCP的数据库链接池技术
     *
     * @return 数据库链接
     * @throws Exception 异常
     */
    public static Connection getConnection2() throws Exception {
        return c3p0DataSource.getConnection();
    }

    /**
     * Druid的数据库链接池技术
     *
     * @return 数据库链接
     * @throws Exception 异常
     */
    public static Connection getConnection3() throws Exception {
        return druidDataSource.getConnection();
    }

    /**
     * 关闭资源
     *
     * @param connection 数据库链接
     * @param statement  SQL声明
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
     *
     * @param connection 链接
     * @param statement  声明
     * @param resultSet  结果集
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
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
