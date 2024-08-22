package com.ohgiraffers.section01.othercite;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/othercite")
public class OtherCiteRedirectServlet extends HttpServlet {
    /* 설명. <a(a태그)이므로 doGET 메소드를 사용해야 한다. */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get 요청을 받으면 naver 사이트로 reidrect");

        /* 설명. 리다이렉트는 우리 서블릿 객체 뿐만 아니라 외부 사이트로도 연결이 가능하다. */
        resp.sendRedirect("http://www.naver.com");
//        resp.sendRedirect("http://www.naver.com/board/1?content=abc");

        /* 설명. URI = resource를 식별 할 수 있도록 해줌
        *   1. URN = 계층 구조가 아니고, 자원마다 이름(Name)이 존재하기 떄문에 이름으로 사용 (잘 쓰이지는 X) - Ipv4, Ipv6개념
        *   2. URL = URI와 동일한 개념으로 계층구조를 사용
        *
        *  설명. "http://www.naver.com/board/1?content=abc
        *   1. http: = scheme protocol
        *   2. www.naver.com = Domain주소 (회사주소 - DNS서버에서 Domain주소를 사서 Domain이름을 띄우는것 ex.14.19.34.51:8080)
        *   3. board/1 = borad중에 1번 board를 의미
        *   4. ?content=abc = parameter를 의미 */
    }
}
