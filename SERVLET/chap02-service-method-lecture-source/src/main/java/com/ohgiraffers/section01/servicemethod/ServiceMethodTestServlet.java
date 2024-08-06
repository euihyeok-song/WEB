package com.ohgiraffers.section01.servicemethod;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/request-service")                         // annotation 활용 <a 태그를 통해 이쪽으로 옴
public class ServiceMethodTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(1);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST 요청을 처리할 메소드 호출중...");
    }

    /* 설명. ServletRequest가 주체이며, ctrl+o를 통해서 보면 HttpServletRequest도 존재하고 ServletRequest에 속함*/
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("req = " + req);
        System.out.println("res = " + res);

        /* 설명. HttpServletRequest로 다운캐스팅 후 출력 => HttpServletRequest만 사용가능한 메소드가 존재 */
        HttpServletRequest httpRequest = (HttpServletRequest)req;
        HttpServletResponse httpResponse = (HttpServletResponse) res;

        String httpMethod = httpRequest.getMethod();    
        System.out.println("요청 방식: " + httpMethod);

        if("GET".equals(httpMethod)){
            doGet(httpRequest, httpResponse);
        } else if("POST".equals(httpMethod)) {
            doPost(httpRequest, httpResponse);
        }






    }
}
