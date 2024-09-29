package com.ohgiraffers.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/* 설명. DAO는 Repository 계층에 속하며, 위와 같이 선언하면 MemberDAO라는 Bean이 생성된다.
*       DAO 클래스는 Data Access Object를 줄인 말로 Repository 계층과 마찬가지로 java application과
*       Database를 연동시켜 주기 위한 계층을 구분하는 클래스로 활용한다.(controller - service - dao)
* */

/* 설명.
*   @Repository란?
*   1. @Component 계열로 스프링 컨테이너(IOC 컨테이너)가 bean으로 등록하는 클래스에 추가하는 어노테이션이다.
*   2. DAO(또는 Repository) 계층에 MVC 구조에 맞춰 구분하기 위해 추가하는 어노테이션이다.
*       (추가적으로는 DB에서 발생한 에러를 자바의 예외타입으로 바꿔주는 부가 기능이 있다.)
* */
@Repository
//@Component              // @Component라고 해도 bean으로는 관리될 수 있다.
public class MemberDAO {

    private final Map<Integer, MemberDTO> memberMap;

    public MemberDAO() {
        /* 설명. 생성자를 통한 객체 생성이 주로 이루어진다.*/
        memberMap = new HashMap<>();

        memberMap.put(1, new MemberDTO(1, "user01", "pass01", "홍길동"));
        memberMap.put(2, new MemberDTO(2, "user02", "pass02", "유관순"));
    }

    /* 설명. 회원 조회용 메소드 */
    public MemberDTO selectMember(int sequence){
        return memberMap.get(sequence);
    }

    /* 설명. 회원 가입용 메소드 */
    public int insertMember(MemberDTO registMember){
        int before = memberMap.size();

        memberMap.put(registMember.getSequence(), registMember);

        int after = memberMap.size();

        return after - before;
    }
}
