package com.ohgiraffers.section02.xmlconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/* 설명. XML파일의 객체를 불러오는 방식*/
public class Application {
    public static void main(String[] args) {
        /* 설명. 어떤 설정 파일에서 왔는지 적어줘야 함 */
        ApplicationContext context
                = new GenericXmlApplicationContext("section02/xmlconfig/spring-context.xml");

        MemberDTO member2 = (MemberDTO) context.getBean("member2");
        System.out.println("member2 = " + member2);
    }
}
