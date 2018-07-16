package com.c3p0.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtils {
    private static ComboPooledDataSource ds = null;

    static {
        try {
            ds = new ComboPooledDataSource();
            ds.setDriverClass("com.mysql.jdbc.Driver"); //为C3P0配置MySQL数据库驱动
            ds.setJdbcUrl("jdbc:mysql://localhost:3306/hikari"); //为C3P0配置MySQL数据库URL
            ds.setUser("root");
            ds.setPassword("root");
            ds.setMaxPoolSize(50);  //设置连接池最大连接数
            ds.setMinPoolSize(5);    //设置连接池最小连接数
            ds.setInitialPoolSize(10);   //设置连接池初始化连接数

        } catch (PropertyVetoException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();  //使用ComboPooledDataSource对象获取连接
    }

    public static void release(Connection conn, Statement st, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}