package com.ohgiraffers.chap05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

/* 설명. Bean을 정의하기 위한 @Configuration*/
@Configuration
public class RootConfiguration {

    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver(){
        SimpleMappingExceptionResolver exceptionResolver = new SimpleMappingExceptionResolver();

        Properties props = new Properties();
        /* 설명. 1번 인자에 해당하는 예외 발생시 2번 인자로 가게됨을 의미 */
        props.setProperty("java.lang.NullPointerException","error/nullPointer");
        props.setProperty("MemberRegistException","error/memberRegist");

        /* 설명. 1. 예외에 따른 페이지 설정한 것 적용 */
        exceptionResolver.setExceptionMappings(props);
        /* 설명. 직접 설정하지 않은 error(예외)가 발생시 default로 보냄*/
        exceptionResolver.setDefaultErrorView("error/default");
        /* 설명. 예외 메시지를 뽑기 위한 Attribute key값(화면에서 예외 메시지 확인 시 사용할 키 값) */
        exceptionResolver.setExceptionAttribute("exceptionMessage");

        return exceptionResolver;
    }
}
