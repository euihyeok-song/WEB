package com.ohgiraffers.section02.otherservlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        /* 설명.
        *   포워딩 vs 리다이렉트
        *    - request나 response 객체를 유지하며 요청처리를 하고 싶다면 포워딩을 사용한다.
        *    - 클라이언트(브라우저)가 새로고침을 할 때마다 처음 요청하는 서블릿을 다시 호출하게 하고 싶지 않다면
        *      (insert와 같은 경우) 리다이렉트를 사용한다.
        *
        *  설명
        *   - 포워딩은 request와 attribute를 활용하여 다음 서블릿으로 값을 넘겨줄 수 있다.
        *   - 리다이렉트는 쿼리스트링 형태로 parameter의 개념(또는 쿠키나 세션 활용)을 활용하여 값을 넘겨줄 수 있다. */
        System.out.println("Redirect 되어 온 곳에서 request 값 홛인: " + req.getAttribute("test"));
        System.out.println("Redirect 되어 온 곳에서 request 값 홛인: " + req.getParameter("test"));
    }
}
