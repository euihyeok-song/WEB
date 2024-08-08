package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* 설명. Annotation방식도 설정(Configuration)이 필요함 - Bean으로 인식하지 않게 하기 위해서 ("configurationSection03")로 선언
*       @ComponentScan(basePackages : 내가 지정한 범위내에 bean으로 등록할 수 있는 클래스르 찾아줘 */
@Configuration("configurationSection03")
@ComponentScan(basePackages = "com.ohgiraffers.common")
public class ContextConfiguration {
}
