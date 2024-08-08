package com.ohgiraffers.section01.xmlconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Application {
    public static void main(String[] args) {
        /* 설명. 설정파일을 넘겨줬으니 알아서 Bean을 만들어서 관리해!라는 의미 */
        ApplicationContext context =
                new GenericXmlApplicationContext(
                        "section01/xmlconfig/spring-context.xml");

        MemberDTO member = context.getBean(MemberDTO.class);
        System.out.println(member.getPersonalAccount());
    }
}

/* 설명. Account account = new PersonalAccount(20,"12313"); 에서 = 와 new PersonalAccount(20,"12313");를
*       설정파일(spring-context.xml)로 대신 처리한다.*/
