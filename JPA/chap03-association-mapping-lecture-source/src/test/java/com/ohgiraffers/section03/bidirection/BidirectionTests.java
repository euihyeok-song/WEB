package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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

   @Test
   public void 양방향_연관관계_매핑_조회_테스트() {
       int menuCode = 10;
       int categoryCode = 10;

       Menu foundMenu = em.find(Menu.class, menuCode);
       Category foundCategory = em.find(Category.class,categoryCode);

       /* 설명. toString()에서 오류가 발생한다. - stackoverflow 발생 => 순환 참조 발생*/
       System.out.println("foundMenu = " + foundMenu);
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

