package com.ohgiraffers.section01.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

/* 설명. first 도메인에 있는 모든것을 의미
*       Tomcat(Servlet)은 filter도 관리해줌 */
@WebFilter("/first/*")
public class FirstFilter implements Filter {

    /* 설명.
     *   filter의 기능
     *    1. log를 찍어서 어느 servlet으로 가는지 어디서 온지를 출력해볼 수 있다.
     *    2. encoding을 할 수 있다.(Tomcat 8버전 아래에서는 직접 인코딩 해줬어야함)
     *    3. 비밀번호 암호화를 처리 가능하다. (인지 & 인가 기능)
     *    4. TimeZone을 다루어서 시간을 조절할 수 있다.
     * */

    public FirstFilter() {
        System.out.println("FirstFilter 인스턴스 생성!");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("FirstFilter init 호출!!");
    }

    /* 설명. req와 resp는 나중에 사용시 (Httpservlet)으로 다운캐스팅 필요 */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        System.out.println("FirstFilter doFilter 호출");

        /* 설명. FilterChain에서 제공하는 doFilter를 활용하여 다음 필터 또는 서블릿을 진행시킬 수 있다.
        *       하나의 filter는  여러개의 응답을 처리할 수 있다. */
        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("서블릿을 다녀온 후");
    }

    @Override
    public void destroy() {
        System.out.println("FirstFilter destroy 호출");
    }
}
