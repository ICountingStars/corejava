package com.connection.datasource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbcp2.BasicDataSourceFactory;
import org.junit.Test;

import java.sql.Connection;
import java.util.Properties;

/**
 * DBCP的数据库链接池的配置方式
 * @author chenjianglin
 * @date 2021/8/14
 * @since 1.0.0
 **/
public class DBCPTest {

    // 方式一
    @Test
    public void testDBCP() throws Exception {
        BasicDataSource dataSource = new BasicDataSource();
        // 基本信息
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test?useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("");

        // 数据库链接池基本信息
        dataSource.setInitialSize(10);

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }

    // 方式二
    @Test
    public void testDBCP2() throws Exception {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties"));
        BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
