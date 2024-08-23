package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/* 설명. 양방향 연관관계는 완전 지향 (Bad!!) */
public class BidirectionTests {
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

    /* 설명. 양방향 연관관계는 지양되는 방식이며 순환참조(특히 toString() 작성시 조심해야 한다.) */
   @Test
   public void 양방향_연관관계_매핑_조회_테스트() {
       int menuCode = 10;
       int categoryCode = 10;

       /* 설명. 연관관계의 주인인 엔티티는 한번에 join문으로 관계를 맺은 엔티티를 조회해 온다. */
       Menu foundMenu = em.find(Menu.class, menuCode);
       /* 설명.
       *   양방향에서 부모에 해당하는 엔티티는 가짜 연관관계이고 필요 시 연관 관계 엔티티를
       *   조회하는 쿼리를 다시 실행하게 된다.(N+1 문제 야기(feat.OneToMany))
       * */
       Category foundCategory = em.find(Category.class,categoryCode);

       /* 설명. toString()에서 오류가 발생한다. - stackoverflow 발생 => 순환 참조 발생*/
       System.out.println("foundMenu = " + foundMenu);

       /* 설명. 객체 그래프 탐색도 가능은 하다.*/
       foundCategory.getMenuList().forEach(System.out::println); // 10번 카테고리에 해당하는 것이 나오고,
                                                                // Category부터는 Category에 넘어가서 나오지 않는다
       System.out.println("foundCategory = " + foundCategory);
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

