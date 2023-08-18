package com.canvs.utils;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class JDBCUtils {
    public static Connection getConnection() throws Exception {
        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://bj-cynosdbmysql-grp-an5acmhk.sql.tencentcdb.com:28067/demo?serverTimezone=UTC";
        return DriverManager.getConnection(url, "root", "Lbwnb123");
    }

    public static void closeResource(Connection conn, Statement ps) {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeResource(Connection conn, Statement ps, ResultSet rs) {
        try {
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
