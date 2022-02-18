package com.connection.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * Druid(德鲁伊)数据库链接池
 *
 * @author chenjianglin
 * @date 2021/8/14
 * @since 1.0.0
 **/
public class DruidTest {

    @Test
    public void testDruid() throws Exception {
        Properties properties = new Properties();
        properties.load(ClassLoader.getSystemClassLoader().getResourceAsStream("druid.properties"));
        DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
