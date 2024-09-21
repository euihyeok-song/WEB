package com.ohgiraffers.section02.sessionlistener;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/session")
public class SessionListenerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 설명. Session 과 request는 동일한 개념 */
        HttpSession session = req.getSession();                     // session 객체 생성
        System.out.println("서블릿에서 session 출력: " + session.getClass().getName());

        /* 설명. 추가(add) */
        session.setAttribute("userName","hongildong");
        session.setAttribute("age",20);
        session.setAttribute("gender","M");

        /* 설명. 수정(replace) */
        session.setAttribute("userName","hong");
        session.setAttribute("user",new UserDTO("honggildong",20));

        /* 설명. 삭제(delete) */
        session.removeAttribute("user");

        /* 설명. session 자체를 삭제 -> session 내부의 값들은 나중에 제거한다. */
        session.invalidate();
    }
}
