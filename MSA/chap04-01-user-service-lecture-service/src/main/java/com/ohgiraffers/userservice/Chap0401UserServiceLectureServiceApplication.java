package com.ohgiraffers.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Chap0401UserServiceLectureServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Chap0401UserServiceLectureServiceApplication.class, args);
    }

}