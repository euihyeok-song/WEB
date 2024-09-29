package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        /* 설명. ContextConfiguration 클래스를 나의 설정파일로 사용하겠다는 의미 */
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        /* 설명. @Bean을 통해서 만든 메소드는 메소드의 이름를 Bean의 이름으로 설정하기 때문에 이름으로 호출*/
        MemberDTO member = (MemberDTO)context.getBean("memberGenerator");
        System.out.println("member = " + member);
        System.out.println("member.getPersonalAccount() = " + member.getPersonalAccount());

        System.out.println("입금: " + member.getPersonalAccount().deposit(100000));
        System.out.println("출금: " + member.getPersonalAccount().withdraw(70000));
        System.out.println("잔액확인: " + member.getPersonalAccount().getBalance());
    }
}
