package com.ohgiraffers.springdatajpa.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    /* 설명. 엔티티와 DTO 매핑을 위한 modelmapper 라이블리 bean 객체 등록(build.gradle에 dependency로 추가) */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
