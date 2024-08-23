package com.ohgiraffers.section01.simple;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleJPQLTests {
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

    /* 설명. JPQL => DBMS에 맞춰서 알아서 Query(native Query)를 해석해줌 */
    @Test
    public void TypedQuery를_이용한_단일행_단일열_조회_테스트(){
        /* 설명. JPQL Query => mysql을 기준으로 만든 문법 */
        String jpql = "SELECT menuName FROM Menu WHERE menuCode = 6";   // Menu부분이 Entity 이름을 적어줌
        TypedQuery<String> query = em.createQuery(jpql,String.class);   // 타입을 지정해줌 ( TypedQuery )

        String resultMenuName = query.getSingleResult();      // 어떤타입인지 알면 JAVA의 하나의 결과로 받아낼 수 있다.

        System.out.println("resultMenuName = " + resultMenuName);

        assertEquals("생마늘샐러드", resultMenuName);
    }

    @Test
    public void Query를_이용한_단일행_단일열_조회_테스트(){
        String jpql = "SELECT menuName FROM Menu WHERE menuCode = 6";
        Query query = em.createQuery(jpql);                    // 타입을 지정하지 않으면 Object형을 반환

        Object resultMenuName = query.getSingleResult();

        assertTrue(resultMenuName instanceof String);
        assertEquals("생마늘샐러드", resultMenuName);     // 동적바인딩 일어남

        /* 설명. 단일행임에도 불구하고, 2개의 필드값이 들어가면, Object배열로 받아내야함 -> Projection의 1가지(4개중) 경우
        *       JPQL은 앵간하면 하나의 필드만 받거나, 전체의 필드를 다 받을 경우 사용하여라. */
        /* 설명. 단일행 부분열 조회일 경우(프로젝션 활용(그 중에 Object[]로 받는 방식)*/
//        String jpql = "SELECT menuName,menuPrice FROM Menu WHERE menuCode = 6";
//        Query query = em.createQuery(jpql);                    // 타입을 지정하지 않으면 Object형을 반환
//
//        List<Object[]> resultColumns = query.getResultList();  // 단일행임에도 List<Object[]>로 반환받아 다루게 된다.
//
//        /* 설명. menuName,menuPrice가 배열형태로 resultMenuName으로 들어감 */
//        Arrays.stream(resultColumns.get(0)).forEach(System.out::println);
//
//        assertTrue(resultColumns.get(0)[0] instanceof String);      // menuName가 String 타입이냐?
//        assertTrue(resultColumns.get(0)[1] instanceof Integer);     // menuPrice가 Interger 타입이냐?
    }

    @Test
    public void TypedQuery를_이용한_다중행_다중열_조회_테스트(){

        /* 설명. jpql에서는 entity의 별칭을 적으면 모든 속성(컬럼)을 조회하는 것이다. */
        String jpql = "SELECT m FROM Menu as m";  // SELECT A.* FROM Menu as A;와 동일한 개념(전체 조회)

        TypedQuery<Menu> query = em.createQuery(jpql,Menu.class);

        List<Menu> foundMenuList = query.getResultList();

        assertTrue(!foundMenuList.isEmpty());
        foundMenuList.forEach(System.out::println);
    }


    @Test
    public void distinct를_활용한_중복제거_여러_행_조회_테스트() {

        /* 설명. 메뉴로 존재하는 카테고리의 종류(중복 제거) 조회 */
        String jpql = "SELECT DISTINCT m.categoryCode FROM Menu m";
        TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
        List<Integer> categoryCodeList = query.getResultList();

        assertTrue(!categoryCodeList.isEmpty());
        categoryCodeList.forEach(System.out::println);
    }

    @Test
    public void in_연산자를_활용한_조회_테스트(){

        /* 설명. 카테고리 코드가 6번 또는 10번인 메뉴를 조회 */
        String jpql = "SELECT m FROM Menu m WHERE m.categoryCode IN (6,10)";

//        TypedQuery<Integer> query = em.createQuery(jpql,Integer.class);
//        List<Integer> categoryCodeList = query.getResultList();

        /* 설명. 위 내용 한줄 코딩 가능 */
        List<Menu> foundMenuList = em.createQuery(jpql, Menu.class).getResultList();

        assertTrue(!foundMenuList.isEmpty());
        foundMenuList.forEach(System.out::println);
    }

    @Test
    public void like_연산자를_활용한_조회_테스트() {

        String jpql = "SELECT m FROM Menu m WHERE m.menuName LIKE '%마늘%'";

        List<Menu> foundMenuList = em.createQuery(jpql, Menu.class).getResultList();

        assertTrue(!foundMenuList.isEmpty());
        foundMenuList.forEach(System.out::println);
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
