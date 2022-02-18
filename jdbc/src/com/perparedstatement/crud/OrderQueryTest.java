package com.perparedstatement.crud;

import com.bean.Order;
import com.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

/**
 * @author chenjianglin
 * @date 2021/8/13
 * @since 1.0.0
 **/
public class OrderQueryTest {

    /**
     * order通用查询
     * @param sql
     * @param args
     * @return
     */
    public Order testQueryOrder(String sql, Object... args) {
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

            if (resultSet.next()) {
                Order order = new Order();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    //                String columnName = rsmd.getColumnName(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    // 巧妙的使用列的别名作为字段名的结果集
                    Field declaredField = Order.class.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(order, columnValue);
                }
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, resultSet);
        }
        return new Order();
    }

    @Test
    public void testQuery() {
        String sql = "select `order_id` orderId,`order_name` orderName,`order_date` orderDate from  `order` where order_id=?";
        Order order = testQueryOrder(sql, 1);
        System.out.println(order);
    }
}
