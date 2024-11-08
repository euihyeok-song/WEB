package com.ohgiraffers.section03.persistencecontext;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class A_EntityLifeCycleTests {
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

    /* 설명.
     *  영속성 컨텍스트는 엔티티 매니저가 엔티티 객체를 저장하는 공간으로 엔티티 객체를 보관하고 관리한다.
     *  엔티티 매니저가 생성될 때 하나의 영속성 컨텍스트가 만들어진다.
     *
     * 설명.
     *  엔티티의 생명주기
     *  비영속, 영속, 준영속, 삭제 상태(em.remove())
    * */

    @Test
    public void 비영속성_테스트() {
        Menu foundMenu = em.find(Menu.class, 11);

        Menu newMenu = new Menu();
        newMenu.setMenuCode(foundMenu.getMenuCode());
        newMenu.setMenuName(foundMenu.getMenuName());
        newMenu.setMenuPrice(foundMenu.getMenuPrice());
        newMenu.setCategoryCode(foundMenu.getCategoryCode());
        newMenu.setOrderableStatus(foundMenu.getOrderableStatus());

        boolean isTrue = (foundMenu == newMenu);

        assertFalse(isTrue);
    }

    @Test
    public void 영속성_연속_조회_테스트() {
        Menu foundMenu1 = em.find(Menu.class, 11);
        Menu foundMenu2 = em.find(Menu.class, 11);

        boolean isTrue = (foundMenu1 == foundMenu2);

        assertTrue(isTrue);
    }

    @Test
    public void 영속성_객체_추가_테스트() {

        Menu menuToRegist = new Menu();
        menuToRegist.setMenuCode(500);
        menuToRegist.setMenuName("수박죽");
        menuToRegist.setMenuPrice(10000);
        menuToRegist.setCategoryCode(10);
        menuToRegist.setOrderableStatus("Y");

        /* 설명. auto_increment 개념의 GenerationType.IDENTITY 부분을 주석해야 @Id 쪽에 값을 넣을 수 있다.(안하면 준영속 상태로 자동 detach) */
        em.persist(menuToRegist);
        Menu foundMenu = em.find(Menu.class, 500);

        boolean isTrue = (menuToRegist == foundMenu);

        assertTrue(isTrue);
    }

    @Test
    public void 영속성_객체_수정_테스트() {

        Menu menuToModify = new Menu();
        menuToModify.setMenuCode(501);
        menuToModify.setMenuName("수박죽");
        menuToModify.setMenuPrice(10000);
        menuToModify.setCategoryCode(10);
        menuToModify.setOrderableStatus("Y");

        em.persist(menuToModify);

        menuToModify.setMenuName("메론죽");

        Menu foundMenu = em.find(Menu.class, 501);

        boolean isTrue = (menuToModify == foundMenu);

        assertTrue(isTrue);
    }

    /* 설명. 
     *  준영속성 사용 이유?
     *   JPA에서 엔티티를 더 이상 영속성 컨텍스트에서 관리하지 않음을 의미하며, 특정 상황에서 성능 최적화나 데이터 무결성 유지,
     *   특정 작업 후 엔티티 변경 방지를 위해 사용된다.
    * */
    @Test
    public void 준영속성_detach_테스트() {
        Menu foundMenu1 = em.find(Menu.class, 11);
        Menu foundMenu2 = em.find(Menu.class, 12);

        em.detach(foundMenu2);          // 영속성 컨텍스트가 관리하지 않는 영역으로 잠시 빼둠

        foundMenu1.setMenuPrice(5000);
        foundMenu2.setMenuPrice(5000);

        assertEquals(5000, em.find(Menu.class, 11).getMenuPrice());
        assertNotEquals(5000, em.find(Menu.class, 12).getMenuPrice());// 기존 12번 메뉴가 준영속이니 새로 가져옴
    }

    @Test
    public void 준영속성_clear_close_테스트() {
        Menu foundMenu1 = em.find(Menu.class, 11);
        Menu foundMenu2 = em.find(Menu.class, 12);

//        em.clear();   // 영속성 컨텍스트는 유지되지만 안에 있는 모든 영속성 엔티티는 준영속 상태가 된다.

        em.close(); // 영속성 컨텍스트가 제거되고 새로운 영속성 컨텍스트를 원하면 엔티티매니저도 다시 생성해야 된다.
                    // 모든 영속성 엔티티는 준영속 상태가 된다.
        em = emf.createEntityManager();

        foundMenu1.setMenuPrice(5000);
        foundMenu2.setMenuPrice(5000);

        assertNotEquals(5000, em.find(Menu.class, 11).getMenuPrice());
        assertNotEquals(5000, em.find(Menu.class, 12).getMenuPrice());// 기존 12번 메뉴가 준영속이니 새로 가져옴
    }

    @Test
    public void 병합_merge_수정_테스트() {
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Menu menuToDetach = em.find(Menu.class, 5);

        em.detach(menuToDetach);
        
        menuToDetach.setMenuName("메론죽");
        Menu refoundMenu = em.find(Menu.class, 5);

        System.out.println("menuToDetach.hashCode() = " + menuToDetach.hashCode());
        System.out.println("refoundMenu.hashCode() = " + refoundMenu.hashCode());

        tx.commit();
    }

    @Test
    public void 병합_merge_삽입_테스트() {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Menu menuToDetach = em.find(Menu.class, 6);
        em.detach(menuToDetach);

        menuToDetach.setMenuCode(999);
        menuToDetach.setMenuName("은하철도햄버거");

        em.merge(menuToDetach);

        tx.commit();

        String insertedMenuName = em.find(Menu.class, 999).getMenuName();
        assertEquals("은하철도햄버거", insertedMenuName);
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
