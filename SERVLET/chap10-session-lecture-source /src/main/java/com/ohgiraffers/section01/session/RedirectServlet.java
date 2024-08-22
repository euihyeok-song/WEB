package com.ohgiraffers.section01.session;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        /* 설명. Attribute로 꺼내면 Object형으로 반환됨으로 다운캐스팅 필요 */
        String firstName = (String)session.getAttribute("firstName");
        String lastName = (String)session.getAttribute("lastName");

        StringBuilder responseText = new StringBuilder();
        responseText.append("<h3> your firstName is ")
                .append(firstName)
                .append("\n lastName is ")
                .append(lastName)
                .append("</h3>");

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print(responseText);
        out.flush();
        out.close();
    }
}
