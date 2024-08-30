package com.ohgiraffers.orderservice.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
/* 설명. 알아서 패키지 내부의 Mapper를 찾아서 파악하도록 MapperScan 설정해줌 */
@MapperScan(basePackages = "com.ohgiraffers.orderservice", annotationClass = Mapper.class)
public class MybatisConfiguration {
}
