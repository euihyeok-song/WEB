package com.ohgiraffers.section01.common;

import java.util.List;

/* 설명. 타입의 의미를 가지면서 중간의 완충 작업을 해주는 역할 */
/* 설명. BookDAOImpl가 생성되었는지 런타임 전까지는 인지하지 못함으로,
        의존관계 (service -> BookDAO) (BookDAOImpl -> BookDAO) */
public interface BookDAO {

    List<BookDTO> findAllBook();

    BookDTO searchBookBySequence(int sequence);


}
