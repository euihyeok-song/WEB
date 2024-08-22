package com.ohgiraffers.section01.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section01.aop");

    MemberService memberService = context.getBean("memberService",MemberService.class);

    /* 설명. 1. 회원 전체 조회(매개변수 없는 타겟 메소드인 경우) */
    System.out.println("========== select all members ============");

    List<MemberDTO> members = memberService.findAllMembers();
    members.forEach(System.out::println);

    /* 설명. 2. 회원 한명 조회(매개변수 한개 있는 타겟 메소드인 경우 */
    System.out.println();
    System.out.println(memberService.findMembersBy(1));

    /* 설명. 3. 존재하지 않는 회원(3번 인덱스)을 조회하여 예외 발생 시켜보기(AfterThrowing Advice 동작) */
//    memberService.findMembersBy(3);

    }
}
