package com.perparedstatement.crud;

import com.bean.Customer;
import com.util.JDBCUtils;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.*;

/**
 * @author chenjianglin
 * @date 2021/8/13
 * @since 1.0.0
 **/
public class CustomerQueryTest {

    /**
     * customer通用查询操作
     *
     * @param sql
     * @param args
     * @return
     */
    public Customer queryCustomer(String sql, Object... args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            // 设置占位符
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }

            resultSet = preparedStatement.executeQuery();
            // 获取到结果集的元数据
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int columnCount = rsmd.getColumnCount();

            if (resultSet.next()) {
                Customer customer = new Customer();
                for (int i = 0; i < columnCount; i++) {
                    // 获取列值与列名
                    Object columnVale = resultSet.getObject(i + 1);
                    String columnName = rsmd.getColumnName(i + 1);
                    // 反射动态获取字段并设置对应字段的值
                    Field declaredField = Customer.class.getDeclaredField(columnName);
                    declaredField.setAccessible(true);
                    declaredField.set(customer, columnVale);
                }
                return customer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, resultSet);
        }
        return new Customer();
    }

    @Test
    public void testQuery() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = JDBCUtils.getConnection();
            String querySQL = "select `id`,`name`,`email`,`birth` from `customers` where id = ?";
            preparedStatement = connection.prepareStatement(querySQL);

            preparedStatement.setInt(1, 1);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Customer customer = new Customer();
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setEmail(resultSet.getString(3));
                customer.setBirth(resultSet.getDate(4));
                System.out.println(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Test
    public void testQueryCustomer() {
        String querySQL = "select `id`,`name`,`email`,`birth` from `customers` where id = ?";
        Customer customer = queryCustomer(querySQL, 2);
        System.out.println(customer);
        querySQL = "select `name`,`email` from `customers` where name = ?";
        Customer customer1 = queryCustomer(querySQL, "周杰伦");
        System.out.println(customer1);
    }

    @Test
    public void testQueryCustomerHasBlob() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();

            String queryBlob = "insert into customers(name,email,birth,photo)values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(queryBlob);

            preparedStatement.setString(1, "playgirl");
            preparedStatement.setString(2, "bg@gmail.com");
            preparedStatement.setObject(3, "1997-07-28");
            FileInputStream is = new FileInputStream(new File("playgirl.jpg"));
            preparedStatement.setBlob(4, is);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement);
        }
    }

    @Test
    public void testQueryBlob() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        InputStream is = null;
        FileOutputStream os = null;
        try {
            connection = JDBCUtils.getConnection();
            String sql = "select `id`,`name`,`email`,`birth`,`photo` from customers where id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, 16);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate("birth");
                Blob photo = resultSet.getBlob("photo");
                Customer customer = new Customer(id, name, email, birth);
                is = photo.getBinaryStream();
                os = new FileOutputStream("xiao.jpg");
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    os.write(bytes, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement);
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void insertBigBlob() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = JDBCUtils.getConnection();

            String sql = "insert into customers(`name`,`email`,`birth`,`photo`)values(?,?,?,?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setObject(1, "girl");
            preparedStatement.setObject(2, "g@gmail.com");
            preparedStatement.setObject(3, "1997-07-28");
            FileInputStream is = new FileInputStream(new File("girl.jpg"));
            preparedStatement.setBlob(4, is);
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement);
        }
    }

    // 批量操作
    @Test
    public void batchInsertCustomers() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            long start = System.currentTimeMillis();
            connection = JDBCUtils.getConnection();

            String batchSQL = "insert into goods(name)values (?)";
            preparedStatement = connection.prepareStatement(batchSQL);
            for (int i = 20001; i <= 40000; i++) {
                preparedStatement.setString(1,"name"+i);
                preparedStatement.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("花费的时间为:"+(end-start));//花费的时间为:6914
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement);
        }
    }


    /**
     * addBatch()、executeBatch()、cleanBatch()
     * mysql默认不支持批处理,需要设置rewriteBatchedStatements=true参数
     */
    @Test
    public void batchInsertCustomers2() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            long start = System.currentTimeMillis();
            connection = JDBCUtils.getConnection();
            // 设置不允许自动提交数据
            connection.setAutoCommit(false);
            String batchSQL = "insert into goods(name)values (?)";
            preparedStatement = connection.prepareStatement(batchSQL);
            for (int i = 1; i <= 1000000; i++) {
                preparedStatement.setString(1,"name"+i);
                preparedStatement.addBatch();
                if (i % 1000 == 0) {
                    preparedStatement.executeBatch();

                    preparedStatement.clearBatch();
                }
            }
            connection.commit();// 最终提交 //
            long end = System.currentTimeMillis();
            System.out.println("批处理花费的时间为:"+(end-start));//批处理花费的时间为:630
            // 插入100w条数据 批处理花费的时间为:7672
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeConnection(connection, preparedStatement);
        }
    }
}
