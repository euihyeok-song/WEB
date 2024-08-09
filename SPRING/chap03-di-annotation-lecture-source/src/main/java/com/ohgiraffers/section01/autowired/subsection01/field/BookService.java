package com.ohgiraffers.section01.autowired.subsection01.field;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* 설명. BookService가 Service 계층의 Bean임을 나타냄 */
/* 설명. 2.Application의 ComponentScan을 통해 이쪽으로 오게 되고, BookService Bean을 생성하려고 기본 생성자를 본다. */
@Service
public class BookService {

    /* 설명. BookService(Service계층) 와 BookDAOImpl(Repository계층) 이 Bean이 되어야함 */
//    BookDAO bookDAO = new BookDAOImpl();      위와 같이 객체를 새롭게 생성하는 것이 아닌 스프링에서 자동으로 생성하도록 해줌

    /* 설명. 3. "필드 주입 방식" - 권장은 X (java의 reflection에 의해서 위험함 - 캡슐화 적용X )
    *       @Autowired라는 annotation을 보고, bookDAO에 Bean을 넣어달라내? 그래서 다른 basePackages를 찾아보니, - BookDAOImpl로 이동
    *       5. @Repository로 찾아가서 쭉 돌면서 bookList에 데이터를 저장해서 여기의 bookDAO에 받는다.
    * */
    @Autowired
    private BookDAO bookDAO;                 // java의 리플렉션 기능: 접근제어자를 무시하고 부품으로 생각하여 자동으로 설정해줌 (좋지 않음)
//    BookDAO bookDAO;                          //이와 같이 스프링이 자동으로 해주기 위해 설정 해야함

    public List<BookDTO> findAllBook() {
        return bookDAO.findAllBook();
    }

    public BookDTO searchBookSequence(int sequence) {
        return bookDAO.searchBookBySequence(sequence);
    }
}
