package com.c3p0.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtilsTest {


    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            try {
                conn = JdbcUtils.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println(conn);
            System.out.println(conn.getClass().getName());
        } finally {
            JdbcUtils.release(conn, st, rs);
        }
    }

}
