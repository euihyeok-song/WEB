package com.ohgiraffers.eurekaclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
/* 설명. Annotation을 등록해줘야 client가 돌아간다. (생략도 가능하지만, 되도록 써주자) */
@EnableDiscoveryClient
public class Chap0102EurekaClientLectureSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap0102EurekaClientLectureSourceApplication.class, args);
    }

}
