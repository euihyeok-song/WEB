package com.ohgiraffers.section01.common;

import org.springframework.stereotype.Repository;

import java.util.*;

/* 설명. BookDAO는 interface여서 객체 생성이 불가함으로 BookDAOImpl에 Repository를 달아줘야함 (Bookservice 참고) */
/* 설명. 4. BookService에서의 @Autowired를 통해서 타고 들어와 쭉 bookList에 값을 넣어줘서 다시 BookService로 넘김*/
@Repository
public class BookDAOImpl implements BookDAO{

    private Map<Integer,BookDTO> bookList;

    public BookDAOImpl() {
        bookList = new HashMap<>();
        bookList.put(1,new BookDTO(1, 123456,"자바의 정석","남궁성"
        ,"도우출판", new Date()));
        bookList.put(2,new BookDTO(2, 222222,"칭찬은 고래도 춤추게 한다","고래"
                ,"고래출판", new Date()));
    }

    @Override
    public List<BookDTO> findAllBook() {

        /* 설명. HashMap을 ArrayList로 쉽게 바꿀 수 있다.(HashMap -> collection -> ArrayList) */
        return new ArrayList<>(bookList.values());
    }

    @Override
    public BookDTO searchBookBySequence(int sequence) {
        return bookList.get(sequence);
    }
}
