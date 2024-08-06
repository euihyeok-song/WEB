package com.ohgiraffers.section01.querystring;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;

@WebServlet("/querystring")
public class QueryString extends HttpServlet {

    /* 설명. querystring으로부터 입력값 + 위치값이 들어옴
    *       Controller(servlet)가 하는 일 => 1. 입력값 받아오기 2. 받아온 입력을 가공처리 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 설명. name(key)=홍길동(value)&age(key)=12(value)와 같은 형식으로 들어와서 &을 구분자로 짤라서 key만 받아옴
        *       getParameter는 반환형을 String으로 받아옴 */
        String name = req.getParameter("name");
        System.out.println("name = " + name);
        
        int age = Integer.parseInt(req.getParameter("age"));
        System.out.println("age = " + age);

        java.sql.Date birthday = java.sql.Date.valueOf(req.getParameter("birthday"));
        System.out.println("birthday = " + birthday);

        char gender = req.getParameter("gender").charAt(0);
        System.out.println("gender = " + gender);

        String national = req.getParameter("national");
        System.out.println("national = " + national);

        String[] hobbies = req.getParameterValues("hobbies");
        System.out.println("Arrays.toString(hobbies)= " + Arrays.toString(hobbies));
        
    }

}
