package com.ohgiraffers.section01.aop;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
/* 설명. Aop를 switch on하고, Aspect를 실행하게 해주는 Annotation*/
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ContextConfiguration {
}
