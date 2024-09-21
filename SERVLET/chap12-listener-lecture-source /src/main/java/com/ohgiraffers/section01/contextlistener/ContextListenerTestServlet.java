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
    /* index.jsp를 보면 a 태그로 특정 태그로 보내주는 것임으로 doGet을 사용 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("context listener 확인용 서블릿");

        /* context라는 저장공간을 Request로 꺼낼수 있다. */
        ServletContext context = req.getServletContext();

        /* backend로 부터 값을 받아오는 모든 값들은 Attribute로 Key와 Value를 담는다 */
        context.setAttribute("test","value");
        context.setAttribute("test2","value");
        context.setAttribute("test2","value2");         // 같은 key값에 다른 value => 사물함에 다른 물품을 넣는 느낌
        context.removeAttribute("test");                    // session(=request)이라는 사물함에 들어있는 물품을 삭제하는 느낌

    }
}
