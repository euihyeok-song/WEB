package com.ohgiraffers.chap03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* 설명. Configuration 역할을 하는 이곳은 자신이 포함된 부분component로 등록하여 componentscan이 되게 함*/
@SpringBootApplication
public class Chap03HandlerMethodLectureSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap03HandlerMethodLectureSourceApplication.class, args);
    }

}
