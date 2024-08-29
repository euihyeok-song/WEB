package com.ohgiraffers.userservice.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/* 설명. Bean 추가용 파일 */
@Configuration
public class AppConfig {

    /* 설명. VO <-> DTO <-> Entity, 필요한 순간에 의존성 주입을 받기 위해 bean으로 등록(객체를 그떄그떄 안만들어도 됨) */
    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    /* 설명. Security 자체에서 사용할 암호화 방식용 bean 추가 - spring 한테 맡긴 개념*/
    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
