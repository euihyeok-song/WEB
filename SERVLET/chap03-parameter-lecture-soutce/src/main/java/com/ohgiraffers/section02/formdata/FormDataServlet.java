package com.ohgiraffers.section02.formdata;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/formdata")
public class FormDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* 설명. tomcat 9버전 이하는 post 요청일 시 인코딩 설정을 하지 않으면 한글이 깨진다. */
//        req.setCharacterEncoding("UTF-8");

        /* 설명. get 방식일 때와 동일하다. */
        String name = req.getParameter("name");
        System.out.println("name = " + name);

        /* 설명. front의 키값을 모르고, 갯수가 많은 경우 사용 (키값을 한방에 확인하는 방법) - enumerate의 구버전
        *       요청으로 사용자가 넘겨준 모든 parameter들의 키 값 추출*/
        Enumeration<String> names = req.getParameterNames();
        while(names.hasMoreElements()){
            System.out.println(names.nextElement());
        }


    }
}
