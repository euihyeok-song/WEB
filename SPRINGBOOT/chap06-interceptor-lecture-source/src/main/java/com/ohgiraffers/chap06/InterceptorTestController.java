package com.ohgiraffers.chap06;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* 설명. Bean을 다룰수 있어서 Ioc Container에서 관리됨 */
@Controller
public class InterceptorTestController {

    @GetMapping("stopwatch")
    public String handlerMethod() throws InterruptedException{
        System.out.println("핸들러 메소드 호출함...");

        Thread.sleep(1000);

        return "result";
    }
}
