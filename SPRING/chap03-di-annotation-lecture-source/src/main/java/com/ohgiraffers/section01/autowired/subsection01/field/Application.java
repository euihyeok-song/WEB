package com.ohgiraffers.section01.autowired.subsection01.field;

import com.ohgiraffers.section01.autowired.subsection02.setter.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        /* 설명. 1. basePackage를 넣으면 알아서 ComponentScan을 해줌.
        *       우리가 설정한 basePackage 내부에 Component를 찾아서 가줌( @Repository, @Service) - BookService로 넘어감 */
        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section01");

        /* 설명. getBean("클래스이름의 맨 앞글자 소문자", 메타 클래스(타입)) */
        BookService bookService = context.getBean("bookService", BookService.class);

        /* 설명. 전체 도서 목록 조회 후 출력 확인*/
        bookService.findAllBook().forEach(System.out::println);

        /* 설명. 도서 번호로 검색 후 출력 확인 */
        System.out.println(bookService.searchBookSequence(1));
        System.out.println(bookService.searchBookSequence(2));
    }
}

