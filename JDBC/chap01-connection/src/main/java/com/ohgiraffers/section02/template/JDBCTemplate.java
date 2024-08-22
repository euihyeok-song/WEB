package com.ohgiraffers.section02.template;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTemplate {
    public static Connection getConnection(){
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
//            System.out.println("con = " + con);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /* 설명. connection을 닫는 개념은 별도로 분리하고 실제로 닫는 시점을 나중에 우리가 추후 service계층에서 진행할 예정*/

        return con;
    }

    public static void close(Connection con) {
        try {
            if(con != null && !con.isClosed()) con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
