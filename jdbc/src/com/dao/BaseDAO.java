package com.dao;

import com.util.JDBCUtils;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 基础通用查询
 *
 * @author chenjianglin
 * @date 2021/8/14
 * @since 1.0.0
 **/
public abstract class BaseDAO<T> {
    private Class<T> t = null;

//    public BaseDAO() {
//        Type genericSuperclass = this.getClass().getGenericSuperclass();
//        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
//        this.t = (Class<T>) parameterizedType.getActualTypeArguments()[0];
//    }

    {// 这个是反射相关的知识 重要
        // 获取当前BaseDAO的子类，其继承的父类中的泛型 赋值给t
        Type genericSuperclass = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        t = (Class<T>) actualTypeArguments[0];
    }

    // 通用增删改操作 考虑事务
    public void update(Connection connection, String sql, Object... args) {
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

    /**
     * 针对不同表的通用查询 考虑事务
     *
     * @param sql  查询SQL
     * @param args 占位符参数
     * @return 返回对应的类型
     */
    public T selectSingle(Connection connection, String sql, Object... args) {
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
                T newInstance = (T) t.newInstance();
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

    /**
     * 查询多条记录
     *
     * @param connection 链接
     * @param sql        SQL
     * @param args       占位符
     * @return 返回查询到的数据类型的集合
     */
    public List<T> selectList(Connection connection, String sql, Object... args) {
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
            ArrayList<T> list = new ArrayList<>();
            while (resultSet.next()) {
                T newInstance = (T) t.newInstance();
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
            JDBCUtils.closeConnection(null, preparedStatement, resultSet);
        }
        return new ArrayList<>();
    }


    public <T> T SQLFunction(Connection connection, String sql, Object... args) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return (T) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(null, preparedStatement, resultSet);
        }
        return null;
    }
}
