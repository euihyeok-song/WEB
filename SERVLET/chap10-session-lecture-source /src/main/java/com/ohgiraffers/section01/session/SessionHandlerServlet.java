package com.ohgiraffers.section01.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/session")
public class SessionHandlerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("이름: " + req.getParameter("firstName"));
        System.out.println("성: " + req.getParameter("lastName"));

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        /* 설명.
        *   요청이 서블릿으로 들어올 때 HttpServletRequest의 header에는 JSESSIONID라는 값이 들어왔고
        *   이를 활용해 JSESSIONID별로 HttpSession 객체를 생성해서 해당 사용자를 위한 전용 저장 공간을
        *   제공하게 된다.
        *
        *  설명.
        *   JSESSIONID는 만료시간을 지정해주지 않으면 default로 30분(1800초)을 유지해준다.
        *   만료시간이 지나면 끊어진다.
        * */
        /* 설명. 객체 선언이 아니라 getSession()을 사용하는 이유는 객체가 이미 있을 경우(만료된 후 다시 실행)를 대비하기 위해 */
        HttpSession session = req.getSession();
        System.out.println("session의 default 유지시간: " + session.getMaxInactiveInterval());

        session.setMaxInactiveInterval(60 * 10);            // (초 단위) 10분
        System.out.println("Session의 JSESSIONID 확인: " + session.getId());

        /* 설명. session(사물함)에 물건을 집어 넣는 개념 */
        session.setAttribute("firstName",firstName);
        session.setAttribute("lastName",lastName);

        resp.sendRedirect("redirect");
    }
}
