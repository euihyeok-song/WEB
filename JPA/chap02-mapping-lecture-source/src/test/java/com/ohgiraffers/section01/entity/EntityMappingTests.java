package com.ohgiraffers.section01.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityMappingTests {
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
        member.setPhone("010-1234-5678");
        member.setEmail("hong@gmail.com");
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new java.util.Date());
        member.setMemberRole("ROLE_MEMBER");
        member.setStatus("Y");

        // when
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(member);                                 // INSERT 날라간다.

        // then
        Member foundMember = em.find(Member.class, 1);      // 위 와 동일한 놈(영속성 상태)인 놈을 뽑은 것
        foundMember.setNickname("동해번쩍");                // UPDATE 한방 날라간다.(항상 하나의 업데이트로 날라간다.)

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
