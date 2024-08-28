package com.ohgiraffers.eurekaclient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/* 설명. front없이 back끼리만 하는 경우 */
@RestController
public class HelloController {

    /* 설명. 하나의 msa를 사용해서 출력해보기 */
    @GetMapping("/hello")
    public String hello(){
        return "헬로우!";
    }
}
