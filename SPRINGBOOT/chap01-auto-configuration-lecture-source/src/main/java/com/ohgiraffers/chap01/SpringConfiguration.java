package com.ohgiraffers.chap01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
    /* 설명. application.properties - 우선순위 때문에 properties는 지움 -> yml파일 사용*/
    @Value("${test.value}")
    public String value;

//    @Value("${username}")
//    public String username;

    /* 설명. application.yaml*/
    @Value("${test.age}")
    public int age;

    @Bean
    public Object propertyReadTest(){
        System.out.println("value = " + value);
//        System.out.println("username = " + username);
        System.out.println("age = " + age);

        return new Object();
    }


}
