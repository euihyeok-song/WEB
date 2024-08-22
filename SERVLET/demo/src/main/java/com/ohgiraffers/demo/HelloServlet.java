package com.ohgiraffers.demo;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

/* 설명. 실행되면 index.jsp의 <a(a태그)로 가르키는 곳으로 이동하고, servlet 객체가 1개 생성되고 이러한 객체를 관리하는 것이 Tomcat contanier*/
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}