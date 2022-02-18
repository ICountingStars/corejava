package com.connection.datasource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;

/**
 * TODO
 *
 * @author chenjianglin
 * @date 2021/8/14
 * @since 1.0.0
 **/
public class C3P0Test {

    @Test
    public void testC3P0() throws Exception {
        // 获取C3P0数据库链接池
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("com.mysql.cj.jdbc.Driver"); //loads the jdbc driver
        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
        cpds.setUser("root");
        cpds.setPassword("");
        // 初始化的数据库链接池
        cpds.setInitialPoolSize(10);
        Connection connection = cpds.getConnection();
        System.out.println(connection);

        cpds.close();
    }

    // 使用配置文件
    @Test
    public void testC3P02() throws Exception {
        // 获取C3P0数据库链接池
        ComboPooledDataSource cpds = new ComboPooledDataSource("myc3p0");
        Connection connection = cpds.getConnection();
        System.out.println(connection);
    }
}
