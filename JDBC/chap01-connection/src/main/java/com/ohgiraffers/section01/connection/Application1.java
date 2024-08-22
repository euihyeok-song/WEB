package com.ohgiraffers.section01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/* 수업목표. 해당 DBMS와 계정에 맞는 Connection 객체를 생성할 수 있다. */
public class Application1 {
    public static void main(String[] args) {
        Connection con = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");    // Class이름을 arguments를 String으로 받아서
                                                                    // jdbc 드라이버를 가져와서 java가 DB를 연결할 수 있게
                                                                    // 해주느 드라이버 - DriverManager를 사용 가능
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306","practice","practice");

            System.out.println("con = " + con);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(con != null) con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
