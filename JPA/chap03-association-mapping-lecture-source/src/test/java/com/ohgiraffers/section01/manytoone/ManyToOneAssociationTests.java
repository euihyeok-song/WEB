package com.ohgiraffers.section01.manytoone;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManyToOneAssociationTests {

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

    /* 설명. 객체내의 다른 객체를 타고 들어가면서 탐색하는 것을 객체 그래프 탐색이라고 한다.(JOIN을 한번에 수행) - 단방향 */
    @Test
    public void 다대일_연관관계_객체_그래프_탐색을_이용한_조회_테스트(){
        int menuCode = 15;

        MenuAndCategory foundMenu = em.find(MenuAndCategory.class, menuCode);
        Category menuCategory = foundMenu.getCategory();

        assertNotNull(menuCategory);
        System.out.println("foundMenu = " + foundMenu);
        System.out.println("menuCategory = " + menuCategory);
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
