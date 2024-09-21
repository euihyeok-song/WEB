package com.ohgiraffers.section02.uses;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/memeber/*")
public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        System.out.println("인코딩 필터 실행됨");
        /* 설명. 우린 Tomcat 10버전인데 Tomcat 10버전이 아닌 경우에는 post 요청에 대해 인코딩 설정을 해 주어야 한다.
        *       필터를 활용해 request 객체에 인코딩 설정을 적용하고(전처리) 다음 필터나 서블릿으로 넘겨준다. */
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        if("POST".equals(httpRequest.getMethod())){     // 사용자가 어떤 메소드를 사용햇는지 확인해서 인코딩 처리 필요
            httpRequest.setCharacterEncoding("UTF-8");
        }

        /* 다음 필터나 서블릿으로 넘어가라는 의미*/
        filterChain.doFilter(httpRequest,servletResponse);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
