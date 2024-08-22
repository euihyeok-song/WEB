package com.ohgiraffers.section01.autowired.subsection02.setter;

import com.ohgiraffers.section01.common.BookDAO;
import com.ohgiraffers.section01.common.BookDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* 설명. 같은 component-scan 범위 안에 같은 타입에 같은 이름으로 두개 이상의 bean이 공존할 수 없다.(다른 이름 지정) */
@Service("bookServiceSetter")
public class BookService {

    /* 설명. setter와 필드 주입 방식의 단점 = 하나의 객체에 여러개를 담을 수 있기 때문에 final사용이 불가하다.*/
//    private final BookDAO bookDAO;                    // 사용 X
    private BookDAO bookDAO;

    public BookService() {
    }

    /* 설명. setter 주입 방식 - 권장하지는 X
    *   1.접근제어자(public이나 default로만 가능)로 등록 하였지만, 무시하고 부품처럼 끌어서 쓴다.
    *   2.setter룰 통해 여러개를 만들어 두면, 주소가 동일한 하나에 여러가지가 들어갈 것임으로, 객체를 하나의 값으로 볼때 문제가 발생
    *   메모리를 써서라도 객체를 생성할 때마다 새로운 주소가 각각 생성되도록 하는 것이 좋다.*/
    @Autowired
    public void setBookDAO(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    public List<BookDTO> findAllBook() {
        return bookDAO.findAllBook();
    }

    public BookDTO searchBookSequence(int sequence) {
        return bookDAO.searchBookBySequence(sequence);
    }
}
