package com.ohgiraffers.section01.forward;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/print")
public class PrintLoginSuccessServlet extends HttpServlet {

    /* 설명. 전달해온(ReceiveInformationServlet) servlet에서 doPost로 전송했음으로 doPost로 받음 */
    /* 설명. forward된 서블릿의 req객체에는 parameter와 attribute가 모두 담겨잇다 */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("=== 포워딩 된 servlet에서 넘겨받은 request 객체에 담긴 값 확인 ===");
        /* 설명. 포워딩 시 새로 정의해서 넘겨준 값은 getAttribute로 받음 - ex. id와 pw로 인증 후 DB에서 해당 회원 이름 받아옴 */
        System.out.println("이름: " + req.getAttribute("userName"));
        /* 설명. 포워딩 된 servlet에 포함된 내용은 getParameter로 받음*/
         System.out.println("아이디: " + req.getParameter("userId"));
        System.out.println("패스워드: " + req.getParameter("password"));

        String userName = (String)req.getAttribute("userName");

        /* 설명. 출력을 잘하는 Servlet이 jsp와 동일한 개념이다. */
        StringBuilder responseText = new StringBuilder();
        responseText.append("<h3 align=\"center\">")
                    .append(userName)
                    .append("님 환영합니다.</h3>");

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print(responseText);
        out.flush();
        out.close();
    }
}
