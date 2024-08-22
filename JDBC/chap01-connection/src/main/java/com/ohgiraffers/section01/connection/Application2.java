package com.ohgiraffers.section01.connection;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/* 수업목표. */
public class Application2 {
    public static void main(String[] args) {
        Properties prop = new Properties();
        Connection con = null;

        try {
            prop.load(
                    new FileReader("src/main/java/com/ohgiraffers/section01/connection/jdbc-config.properties"));

            /* 설명. 설정해둔 jdbc-config.properties파일에서 꺼내와서 사용함*/
            String driver = prop.getProperty("driver");
            String url = prop.getProperty("url");
            String user = prop.getProperty("user");
            String password = prop.getProperty("password");

            Class.forName(driver);
            DriverManager.getConnection(url,user,password);
            System.out.println("con = " + con);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
//            try {
//                if(con != null) con.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
            close(con);
        }

    }

    private static void close(Connection con) {
        try {
                if(con != null && !con.isClosed()) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
