package com.ohgiraffers.section02.otherservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/otherservlet")
public class OtherServletRedirectServlet extends HttpServlet {
    /* 설명. redirect는 url을 가지고 요청하는 것이기 때문에 무조건 doGET으로 요청함*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("test","abc");

        /* 설명.
        *   redirect 시에는 request가 유지되지 않는다. 이유는 서블릿에서 클라이언트가
        *   새로 요청하면 기존의 request와 response 객체가 아닌 새로운 request와 response
        *   객체가 만들어지며 요청되기 때문이다.
        *
        *  설명.
        *   추가로, cookie나 session같은 공간을 활용하면 이러한 redirect의 단점을 보완할 수 있다.
        *   (적당히 적은 데이터는 쿼리스트링(Querystring)으로 해결할 수도 있다.)
        * */
//        resp.sendRedirect("redirect");                  // 챙겨가는 것없이 경로만 넣어준다
        resp.sendRedirect("redirect?test=abc");
    }
}
