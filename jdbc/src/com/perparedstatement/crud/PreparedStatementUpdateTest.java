package com.perparedstatement.crud;

import com.util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

/**
 * PreparedStatement 实现CRUD
 *
 * @author chenjianglin
 * @date 2021/8/13
 * @since 1.0.0
 **/
public class PreparedStatementUpdateTest {

    // 添加一条记录
    @Test
    public void testInsert() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();

            String insertSQL = "insert into customers(name,email,birth)values(?,?,?)";// 占位符
            preparedStatement = connection.prepareStatement(insertSQL);
            // 填充占位符
            preparedStatement.setString(1, "郑爽");
            preparedStatement.setString(2, "zs@gmail.com");
            preparedStatement.setDate(3, new Date(new SimpleDateFormat("yyyy-MM-dd").parse("2008-09-09").getTime()));

            // 执行SQL
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement);
        }
    }

    // 修改一条记录
    @Test
    public void testUpdate() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            // 获取数据库链接
            connection = JDBCUtils.getConnection();
            // 获取preparedStatement，预编译SQL
            String updateSQL = "update customers set name = ? where id = ?";
            preparedStatement = connection.prepareStatement(updateSQL);
            // 填充占位符并执行SQL
            preparedStatement.setString(1, "爽子");
            preparedStatement.setInt(2, 22);

            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            JDBCUtils.closeConnection(connection, preparedStatement);
        }
    }

    // 删除一条记录
    @Test
    public void testDelete() {
        String sql = "delete from customers where id = ?";
        update(sql, 22);
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
}
