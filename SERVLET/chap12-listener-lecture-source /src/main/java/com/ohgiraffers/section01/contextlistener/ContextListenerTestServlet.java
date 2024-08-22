package com.ohgiraffers.section01.contextlistener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/context")
public class ContextListenerTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("context listener 확인용 서블릿");

        ServletContext context = req.getServletContext();

        context.setAttribute("test","value");
        context.setAttribute("test2","value");
        context.setAttribute("test2","value2");         // 같은 key값에 다른 value => 사물함에 다른 물품을 넣는 느낌
        context.removeAttribute("test");                    // session(=request)이라는 사물함에 들어있는 물품을 삭제하는 느낌

    }
}
