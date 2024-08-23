package com.ohgiraffers.springdatajpa.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* 설명. Controller를 통해서 프론트와 연결해주는 부분 */
@Controller
public class MainController {

    @GetMapping(value={"/","/main"})
    public String main(){
        return "main/main";
    }
}
