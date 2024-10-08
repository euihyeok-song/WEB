package com.ohgiraffers.chap06;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/* 설명.
*   인터셉터를 사용하는 경우(목적)
*   : 로그인 체크, 권한 체크, 프로그램 실행 시간 계산 작업 로그 처리, 업로드 파일 처리, 로케일(지약) 설정 등
* */
@Configuration
public class StopwatchInterceptor implements HandlerInterceptor {

    private final MenuService menuService;

    @Autowired
    public StopwatchInterceptor(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request,response,handler,ex);
    }

    /* 설명. modelAndView에 "result"가 들어있고 아래의 addObject를 통해 "interval"까지 넣는다.*/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        System.out.println("postHandler 호출함...(핸들러 메소드 이후)");

        long startTime = (long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();

        modelAndView.addObject("interval",endTime - startTime);
    }

    /* 설명. Handle Method을 가기전에 이미 동작함 (main보다 먼저 동작함) */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        menuService.method();

        System.out.println("preHandler() 호출함...(핸들러 메소드 이전)");

        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime",startTime);

        /* 설명. 반환형을 false로 하면 특정 조건에 의해 이후 핸들러 메소드가 실행되지 않게 할 수도 있다.*/
//        return false;
        return true;
    }
}
