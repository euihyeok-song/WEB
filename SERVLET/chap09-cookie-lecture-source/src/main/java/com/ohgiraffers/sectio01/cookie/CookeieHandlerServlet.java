package com.ohgiraffers.sectio01.cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/cookie")
public class CookeieHandlerServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 설명. response의 Header에 태워서 보내면 cookie로 관리한다. */
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);

        /* 설명.
        *   쿠키(클라이언트의 브라우저에 저장)를 생성하고 사용하는 방법
        *   1. 쿠키 인스턴스를 생성한다.
        *   2. 해당 쿠기의 만료시간을 설정한다.
        *   3. 응답 헤더의 쿠기를 담는다.
        *   4. 응답한다.*/

        /* 설명. jakarataEE는 cookie를 class로 가지고 있음.  */
        Cookie firstNameCookie = new Cookie("firstName", firstName);
        Cookie lastNameCookie = new Cookie("lastName", lastName);

        /* 설명. cookie에 저장할 시간(만료시간 설정) */
        firstNameCookie.setMaxAge(60 * 60 * 24);            // 초 단위, 하루
        lastNameCookie.setMaxAge(60 * 60 * 12);

        /* 설명. 응답(response) 헤더에 추가 */
        resp.addCookie(firstNameCookie);
        resp.addCookie(lastNameCookie);

        resp.sendRedirect("redirect");


    }
}
