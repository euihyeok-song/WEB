package com.ohgiraffers.section01.autowired.subsection03.constructor;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* 설명. 같은 component-scan 범위 안에 같은 타입에 같은 이름으로 두개 이상의 bean이 공존할 수 없다.(다른 이름 지정) */
@Service("bookServiceConstructor")
public class BookService {

    private BookDAO bookDAO;

    public BookService() {
    }

    /* 설명.
        의존성 DI - 생성자 주입 방식 => 가장 추천
    *    BookDAO 타입의 빈 객체를 생성자를 통해 주입 받는 것을 생성자 주입이라고 한다. (기본 생성자는 활용 X)
    *  설명.
    *   생성자 주입의 이점
    *   1. 필드에 final 키워드를 사용할 수 있다.
    *   2. 순환참조(객체가 객체를 참조한다)를 스프링 시작 시점에 파악하여 방지할 수 있다.
    *     => ex) Member 클래스와 Board 클래스가 서로를 참조하려고 할때, 어떤 걸 우선으로 할줄 모르는 문제점 => 중각객체로 해결 가능(VO)
    *   3. 필드 주입과 setter 주입의 단점(@Autowired를 남용(한번에 인자 확인 어려움), 자바 reflection 기술을 통해 캡슐화 적용 X, 불변객체 X)
    *   4. 테스트 코드 시에 생성자를 통해 편하게 테스트 가능 (Mock 객체 생성 불필요)
    * */
    @Autowired
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    public List<BookDTO> findAllBook() {
        return bookDAO.findAllBook();
    }

    public BookDTO searchBookSequence(int sequence) {
        return bookDAO.searchBookBySequence(sequence);
    }
}
