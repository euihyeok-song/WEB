package com.ohgiraffers.section02.column;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColumnMappingTests {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    public static void intiFactory() {
        emf = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        em = emf.createEntityManager();
    }

    @Test
    public void 테이블_만들기_테스트() {
        // 매핑된 대로 테이블을 만들고 insert, update한다.

        // given
        Member member = new Member();
        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");
//        member.setPhone("010-1234-5678");     // default가 적용되는지 확인(선생님이 이따 찾아볼 예정)
        member.setEmail("hong@gmail.com");
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new java.util.Date());     // 진짜 시간만 넣어주는지 확인
        member.setMemberRole("ROLE_MEMBER");
        member.setStatus("Y");

        // when
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(member);

        // then
        Member foundMember = em.find(Member.class, 1);
        System.out.println("foundMember = " + foundMember);
        foundMember.setNickname("동해번쩍");

        tx.commit();
        assertEquals(member, foundMember);
    }

    @AfterEach
    public void closeManager(){
        em.close();
    }

    @AfterAll
    public static void closeFactory() {
        emf.close();
    }
}
