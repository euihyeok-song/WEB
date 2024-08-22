package com.ohgiraffers.section01.xml;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;

/* 설명. HttpServlet을 상속받으면 JAVA로 서버를 생성 가능*/
public class LifeCycleTestServlet extends HttpServlet {

    public LifeCycleTestServlet() {
        System.out.println("xml 방식 기본 생성자 실행!");
    }

    /* 설명. 서블릿의 요청이 최초인 경우 서블릿 객체를 생성하고 자동으로 호출하게 되는 메소드 */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("xml 매핑 init() 메소드 호출!!");
    }

    /* 설명.
    *   서블릿 컨테이너에 의해 호출되며 최초 요청시에만 init() 이후에 동작하고,
    *   두 번째 요청부터는 바로 service()만 호출하게 된다. - request가 들어올 때 2가지가 한번에 들어옴 (request / response)
    *   1. req에는 사용자가 넘긴 값이 들어가 있음 ex) Id, Pw
    *   2. res에는 요청을 처리하고 다시 돌아갈 위치에 대한 정보 + 돌아갈 경우 가지고 가야할 정보 ex) 페이지 리소스 */
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        System.out.println("xml 매핑 service() 메소드 호출");
        System.out.println("실제로는 요청에 따라 doGet() 또는 doPost()가 실행됨!!");
    }

    /* 설명. 컨테이너가 종료될 때 호출하는 메소드이며 주로 자원을 반납하는 용도로 사용된다. */
    @Override
    public void destroy() {
        System.out.println("xml 매핑 destroy() 메소드 호출");
    }
}
