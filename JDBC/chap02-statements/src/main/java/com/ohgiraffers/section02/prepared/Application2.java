package com.ohgiraffers.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {
    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.print("사번을 입력하세요: ");
        String empId = sc.nextLine();

        /* 설명. ?는 "placeHolder라고 하며, 값이 동적으로 들어옴(값을 알지 못해서 ?들어옴 -> 아래서 들어옴) - 구멍이난 짐짝 트럭에 실어 보냄*/
        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ?";

        try {
            pstmt = con.prepareStatement(query);
            /* 설명. 위의 ?에 들어갈 내용을 전달- (구멍이 나있었던 짐짝을 수리하는 느낌) */
            pstmt.setString(1,empId);

            rset = pstmt.executeQuery();
            if(rset.next()){
                System.out.println(rset.getString("EMP_ID")
                +", " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
            close(con);
        }


    }
}
