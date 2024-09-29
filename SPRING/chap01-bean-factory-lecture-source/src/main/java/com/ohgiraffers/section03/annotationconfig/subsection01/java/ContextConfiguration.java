package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* 설명. Annotation방식도 설정(Configuration)이 필요함 - Bean으로 인식하지 않게 하기 위해서 ("configurationSection03")로 선언
*       @ComponentScan(basePackages : 내가 지정한 범위내에 bean으로 등록할 수 있는 클래스르 찾아줘
*         - ComponentScan은 Component를 찾으려고 함으로, common폴더 내의 DAO와 DTO중에서 @Respository(@Component를 포함)가
*           존재하는 DAO로 간다.
* */
@Configuration("configurationSection03")  // ContextConfiguration이 javaconfig 파일에도 있음으로 파일을 인지시킬 이름달아줌
@ComponentScan(basePackages = "com.ohgiraffers.common")   // Bean으로 읽어z 올 범주를 지정
public class ContextConfiguration {
}
