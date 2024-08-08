package com.ohgiraffers.section01.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        /* 설명. Annotation방식으로 Bean을 넣는 container 선언*/
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        /* 설명. 하나의 콩도 배열로 선언함 - 컨테이너 생성시(프로그램 실행) 자체적으로 볶아지는 콩(객체)들
                + 우리가 만든 콩들의 이름을 출력함 */
        String[] beanNames = context.getBeanDefinitionNames();
        for(String beanName: beanNames){
            System.out.println("beanName: " + beanName);
        }

        /* 설명. 1. Bean의 id(이름)으로 bean을 가져오는 방법
                - getBean의 반환형은 Object이므로 다운캐스팅 필요 */
//        MemberDTO member = (MemberDTO)context.getBean("member");

        /* 설명. 2. Bean의 클래스 메타 정보(bean의 타입)을 전달하여 가져오는 방법
                -  동일한 타입의 여러 객체 존재시 ArrayList와 같은 collection으로 받아야 함*/
//        MemberDTO member = context.getBean(MemberDTO.class);

        /* 설명. 3. Bean의 id와 클래스 메타 정보를 전달하여 가져오는 방법 */
        MemberDTO member = context.getBean("member",MemberDTO.class);
//        @Autowired
//                MemberDTO member;                         // 이와 같은 개념 - 이는 뒤에 다룸

        System.out.println("member = " + member);

    }
}
