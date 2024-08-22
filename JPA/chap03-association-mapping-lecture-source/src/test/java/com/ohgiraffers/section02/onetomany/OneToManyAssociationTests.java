package com.ohgiraffers.section02.onetomany;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OneToManyAssociationTests {
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


    /* 설명. 사실상 실무에서는 N+1문제 떄문에 거의 사용하지 X (카테고리 1개를 뽑고싶지만, N개가 같이 딸려옴) - 단방향 */
    @Test
    public void 일대다_연관관계_객체_그래프_탐색을_이용한_조회_테스트(){
        int categoryCode = 4;

        CategoryAndMenu categoryAndMenu = em.find(CategoryAndMenu.class,categoryCode);

        /* 설명. 조회가 잘 되는지 아닌지를 초록불로 판단*/
        assertNotNull(categoryAndMenu);

        System.out.println("categoryAndMenu = " + categoryAndMenu);

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
