package com.ohgiraffers.section01.exception;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/showErrorPage")
public class ExceptionHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /* 설명. 어떤 것이 들어있는지 확인하는 과정 - error가 발생하면 request에 parameter와 attribute중에서 attribute가 들어감 */
        Enumeration<String> attrNames = req.getAttributeNames();
        while(attrNames.hasMoreElements()){
            System.out.println(attrNames.nextElement());
        }

        /* 설명. error발생시 error의 번호를 담음 - error이므로 parameter가 아닌 getAttribute로 뽑아와서 담음 */
        int statusCode = (int)req.getAttribute("jakarta.servlet.error.status_code");
        String message = (String)req.getAttribute("jakarta.servlet.error.message");
        String servletName = (String)req.getAttribute("jakarta.servlet.error.servlet_name");

        StringBuilder errorPage = new StringBuilder();
        errorPage.append("<h1 align=\"center\">")
                .append(statusCode)
                .append("-")
                .append(message)
                .append("<br>\n")
                .append("<p>에러 발생한 서블릿 명: ")
                .append(servletName)
                .append("</p>\n")
                .append("</h1>\n");

        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();
        out.print(errorPage);

        out.flush();
        out.close();
    }
}
