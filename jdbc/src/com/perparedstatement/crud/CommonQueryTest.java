package com.perparedstatement.crud;

import com.bean.Customer;
import com.bean.Order;
import com.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenjianglin
 * @date 2021/8/13
 * @since 1.0.0
 **/
public class CommonQueryTest {

    @Test
    public void testCommand() {
        String sql = "select `order_id` orderId,`order_name` orderName,`order_date` orderDate from  `order` where order_id=?";
        Object o = commonQuery(sql, Order.class, 1);
        System.out.println(o);
        sql = "select `id`,`name`,`email`,`birth` from `customers` ";
        Object o1 = commonQuery(sql, Customer.class);
        System.out.println(o1);
    }


    /**
     * 针对不同表的通用查询
     *
     * @param sql  查询SQL
     * @param t    泛型类
     * @param args 占位符参数
     * @param <T>  返回的类型值
     * @return 返回对应的类型
     */
    public <T> List<T> commonQuery(String sql, Class<T> t, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()) {
                T newInstance = t.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    Field declaredField = t.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(newInstance, columnValue);
                }
                list.add(newInstance);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, resultSet);
        }
        return new ArrayList<>();
    }
}
