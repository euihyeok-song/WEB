package com.ohgiraffers.section03.primarykey.subsection01.identity;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrimaryKeyMappingTests {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    @BeforeAll
    public static void initFactory() {
        emf = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        em = emf.createEntityManager();
    }

    @Test
    public void 테이블_만들기_테스트() {

        // given
        Member member = new Member();
//        member.setMemberNo(1);
        member.setMemberId("user01");
        member.setMemberPwd("pass01");
        member.setNickname("홍길동");
        member.setPhone("010-1234-5678");
        member.setEmail("hong@gmail.com");
        member.setAddress("서울시 서초구");
        member.setEnrollDate(new java.util.Date());
        member.setMemberRole("ROLE_MEMBER");
        member.setStatus("Y");

        Member member2 = new Member();
        member2.setMemberId("user02");
        member2.setMemberPwd("pass02");
        member2.setNickname("유관순");
        member2.setPhone("010-1111-2222");
        member2.setEmail("yu@gmail.com");
        member2.setAddress("서울시 강남구");
        member2.setEnrollDate(new java.util.Date());
        member2.setMemberRole("ROLE_ADMIN");
        member2.setStatus("Y");

        // when
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        em.persist(member);
        em.persist(member2);

        // then
        Member foundMember = em.find(Member.class, 1);
        foundMember.setNickname("동해번쩍");

        tx.commit();

        /* 설명.
         *  이 예제에서 @GeneratedValue(strategy = GenerationType.IDENTITY)를 통해 auto_increment 개념을
         *  확인하기 위해 insert 작업 이후의 select이 필요하다.
         *  그러기 위해서는 insert가 반드시 flush 되고 나서 select이 일어나야 되고 그 때 기본 설정인 FLushModeType.AUTO에 따라
         *  jpql 구문을 활용하면 jpql 실행 전 flush가 발생한다.(이미 insert 및 번호 생성 완료)
         *
         * 설명.
         *  또한, 다중행 조회를 위해서는 반드시 jpql을 활용해야 한다.(다만, Spring Data Jpa는 다중행 조회를 따로 제공함)
        * */
//        em.setFlushMode(FlushModeType.AUTO);
        String jpql = "SELECT A.memberNo FROM member_section03_subsection01 A";
        List<Integer> memberNoList = em.createQuery(jpql, Integer.class).getResultList();
        memberNoList.forEach(System.out::println);

        /* 설명. 유관순씨가 2번 회원으로 insert되어 있는지 확인 */
        assertEquals(2, memberNoList.get(1));
    }

    @AfterEach
    public void closeManager() {
        em.close();
    }

    @AfterAll
    public static void closeFactory() {
        emf.close();
    }
}
