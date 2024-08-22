package com.ohgiraffers.section01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

public class A_EntityManagerLifeCycleTests {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    /* 설명. 딱 한번만 Factory를 사전에 만들어 줌(싱글톤)*/
    @BeforeAll
    public static void initFactory() {
        emf = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager(){
        em = emf.createEntityManager();
    }

    /* 필기.
     *  엔티티 매니저 팩토리(EntityManagerFactory)란?
     *   엔티티 매니저를 생성할 수 있는 기능을 제공하는 팩토리 클래스이다.
     *   thread-safe하기 때문에 여러 스레드가 동시에 접근해도 안전하므로 서로 다른 스레드 간 공유해서 재사용한다.
     *   thread-safe한 기능을 요청 스코프마다 생성하기에는 비용(시간, 메모리) 부담이 크므로
     *   application 스코프와 동일하게 싱글톤으로 생성해서 관리하는 것이 효율적이다.
     *   따라서 데이터베이스를 사용하는 애플리케이션 당 한 개의 EntityManagerFactory를 생성한다.
     *
     * 필기.
     *  엔티티 매니저(EntityManager)란?
     *   엔티티 매니저는 엔티티를 저장하는 메모리 상의 데이터베이스를 관리하는 인스턴스이다.
     *   엔티티를 저장하고, 수정, 삭제, 조회하는 등의 엔티티와 관련된 모든 일을 한다.
     *   엔티티 매니저는 thread-safe하지 않기 때문에 동시성 문제(겹칠수 있다)가 발생할 수 있다.
     *   따라서 스레드 간 공유를 하지 않고, web의 경우 일반적으로 request scope와 일치시킨다.
     *   => Service에 @Transaction을 붙여주면 알아서 처리해줌
     *
     * 필기.
     *  영속성 컨텍스트(PersistenceContext)란?
     *   엔티티 매니저를 통해 엔티티를 저장하거나 조회하면 엔티티 매니저는 영속성 컨텍스트에 엔티티를 보관하고 관리한다.
     *   영속성 컨텍스트는 엔티티를 key-value 방식으로 저장하는 저장소이다.
     *   영속성 컨텍스트는 엔티티 매니저를 생성할 때 같이 하나 만들어진다.
     *   그리고 엔티티 매니저를 통해서 영속성 컨텍스트에 접근할 수 있고, 또 관리할 수 있다.
     * */


    @Test
    public void 엔티티_매니저_팩토리와_엔티티_매니저_생명주기_확인1(){
        System.out.println("factory의 hashCode1: " + emf.hashCode());
        System.out.println("manager의 hashCode1: " + em.hashCode());
    }

    @Test
    public void 엔티티_매니저_팩토리와_엔티티_매니저_생명주기_확인2(){
        System.out.println("factory의 hashCode2: " + emf.hashCode());
        System.out.println("manager의 hashCode2: " + em.hashCode());
    }

    /* 설명. Manager들도 전부 Stream 개념이여서 다 쓰고 닫아줘야함 */
    @AfterEach
    public void closeManager(){
        em.close();
    }

    @AfterAll
    public static void closeFactory(){
        emf.close();
    }


}
