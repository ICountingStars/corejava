package com.transaction;

import com.bean.User;
import com.util.JDBCUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库事务
 *
 * @author chenjianglin
 * @date 2021/8/14
 * @since 1.0.0
 **/
public class TransactionTest {

    // 事务要么一起成功要么一起失败
    @Test
    public void testTransactionTest() {
        String sql1 = "update user_table set balance = balance - 100 where user = ?";
        update(sql1, "AA");

//        // 模拟异常
//        System.out.println(10 / 0);

        String sql2 = "update user_table set balance = balance + 100 where user = ?";
        update(sql2, "BB");

        System.out.println("转账成功");
    }

    // 保证链接在执行完成后关闭
    @Test
    public void transactionTest() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            // 1.取消自动提交功能
            connection.setAutoCommit(false);

            String sql1 = "update user_table set balance = balance - 100 where user = ?";
            transactionUpdate(connection, sql1, "AA");

            // 模拟异常
            System.out.println(10 / 0);

            String sql2 = "update user_table set balance = balance + 100 where user = ?";
            transactionUpdate(connection, sql2, "BB");
            System.out.println("转账成功");

            // 2.都执行完后提交事务
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // 3.有异常时，回滚
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                if (connection != null) {
                    connection.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            JDBCUtils.closeConnection(connection, null);
        }
    }

    // 通用查询操作 测试
    @Test
    public void transactionSelectTest() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);// 设置数据库隔离级别
            String sql = "select user,password,balance from user_table where user = ？";
            User cc = commonQuery(connection, sql, User.class, "CC");
            System.out.println(cc);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }

    @Test
    public void transactionUpdateTest() {
        Connection connection = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "update user_table set balance = balance + 1000 where user = ?";
            transactionUpdate(connection, sql, "CC");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, null);
        }
    }


    // 通用增删改操作 考虑事务
    public void transactionUpdate(Connection connection, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(null, preparedStatement);
        }
    }


    // 通用增删改操作
    public void update(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement);
        }
    }

    // 通用查询操作

    /**
     * 针对不同表的通用查询 考虑事务
     *
     * @param sql  查询SQL
     * @param t    泛型类
     * @param args 占位符参数
     * @param <T>  返回的类型值
     * @return 返回对应的类型
     */
    public <T> T commonQuery(Connection connection, String sql, Class<T> t, Object... args) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            if (resultSet.next()) {
                T newInstance = t.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = resultSet.getObject(i + 1);
                    String columnLabel = rsmd.getColumnLabel(i + 1);

                    Field declaredField = t.getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(newInstance, columnValue);
                }
                return newInstance;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(null, preparedStatement, resultSet);
        }
        return null;
    }


}
