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

    /* 설명. 딱 한번만 Factory를 사전에 만들어 줌(싱글톤)*/
    @BeforeAll
    public static void initFactory() {
        emf = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager(){
        em = emf.createEntityManager();
    }

    /* 설명.
     *  영속성 컨텍스트는 엔티티 매니저가 엔티티 객체를 저장하는 공간으로 엔티티 객체를 보관하고 관리한다.
     *  엔티티 매니저가 생성될 때 하나의 영속성 컨텍스트가 만들어진다.
     *
     * 설명.
     *  엔티티의 생명주기
     *   비영속, 영속, 준영속, 삭제상태(em.remove())
    * */

    /* 설명. 비영속성 상태의 객체는  동일성 적용 X*/
    @Test
    public void 비영속성_테스트(){
        Menu foundMenu = em.find(Menu.class, 11);

        Menu newMenu = new Menu();
        newMenu.setMenuCode(foundMenu.getMenuCode());
        newMenu.setMenuName(foundMenu.getMenuName());
        newMenu.setMenuPrice(foundMenu.getMenuPrice());
        newMenu.setCategoryCode(foundMenu.getCategoryCode());
        newMenu.setOrderableStatus(foundMenu.getOrderableStatus());

        boolean isTrue = (foundMenu == newMenu);

        /* 설명. import static을 이용하면 Assertions. 을 생략가능 */
        assertFalse(isTrue);
    }

    /* 설명. 관리되고 있는 영속성 객체는 싱글톤으로 한번만 생성되고 select도 한번만 된다. */
    @Test
    public void 영속성_연속_조회_테스트(){
        Menu foundMenu1 = em.find(Menu.class, 11);
        Menu foundMenu2 = em.find(Menu.class, 11);

        boolean isTrue = (foundMenu1 == foundMenu2);

        /* 설명. import static을 이용하면 Assertions. 을 생략가능 */
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

        /* 설명. commit이 되지 않아서 DB로 insert되지는 않았지만 persist 컨텍스의 1차캐시에 저장된 값(얇은 복사)은 확인 가능하다.
        *       auto_increment 개념의 GenerationType.IDENTITY 부분을 주석해야 @Id쪽에 값을 넣을 수 있다.
        *       (안하면 준영속 상태로 자동 detach)
        * */
        em.persist(menuToRegist);
        /* 설명. 500번은 DB에 없는 자료이기 때문에 INSERT해줌 */
        Menu foundMenu = em.find(Menu.class,500);


        boolean isTrue = (menuToRegist == foundMenu);

        assertTrue(isTrue);
    }

    @Test
    public void 영속성_객체_수정_테스트() {

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Menu menuToModify = new Menu();
        menuToModify.setMenuCode(500);
        menuToModify.setMenuName("수박죽");
        menuToModify.setMenuPrice(10000);
        menuToModify.setCategoryCode(10);
        menuToModify.setOrderableStatus("Y");


        em.persist(menuToModify);

        menuToModify.setMenuName("메론죽");

        /* 설명. DB에서 500번을 가져온다.(수박죽) */
        Menu foundMenu = em.find(Menu.class,500);

        tx.commit();

        /* 설명. 수박죽 != 메론죽 */
        boolean isTrue = (menuToModify == foundMenu);

        assertTrue(isTrue);
    }

    /* 설명.
     *  준영속성 사용 이유?
     *   JPA에서 엔티티를 더 이상 영속성 컨텍스트에서 관리하지 않음을 의미하며, 특정 상황에서 성능 최적화나 데이터 무결성 유지,
     *   특정 작업 후 엔티티 변경 방지를 위해 사용된다. (어짜피 DB에 반영되지 않을 엄청 큰 Transaction을 영속성 컨텍스트에 빼두면 성능이 향상 가능
     *   + 건들이거나 수정하면 안되는 Transaction이 영속성 컨텍스트에 남아있으면, DB가 변경될 수 있는 경우  )
    */

    @Test
    public void 준영속성_detach_테스트(){
        /* 설명. 영속상태 */
        Menu foundMenu1 = em.find(Menu.class,11);
        Menu foundMenu2 = em.find(Menu.class,12);

        /* 설명. 영속성 컨텍스트에서 이 영속성 데이터를 삭제시킨다 -> commit하면 delete로 날아감 */
//        em.remove(foundMenu1)

        /* 설명. detach는 준영속 상태로 뗴어냄(영속 -> 준영속) => 관리대상이 아니기 때문에, find()를 하면 다시 DB에서 가져옴*/
        em.detach(foundMenu2);          // 영속성 컨텍스트가 관리하지 않는 영역으로 잠시 뺴둠

        foundMenu1.setMenuPrice(5000);
        foundMenu2.setMenuPrice(5000);

        assertEquals(5000, em.find(Menu.class, 11).getMenuPrice());
        /* 설명. 기존 12번 메뉴가 준영속 상태이므로, 12번을 find()하면 찾을 수 없어서 DB에서 새로 가져옴*/
        assertNotEquals(5000, em.find(Menu.class, 12).getMenuPrice());
    }


    @Test
    public void 준영속성_clear_close_테스트(){
        /* 설명. 영속상태 */
        Menu foundMenu1 = em.find(Menu.class,11);
        Menu foundMenu2 = em.find(Menu.class,12);

        /* 설명. 연속성 켄텍스트는 유지되지만 연속성 컨텍스테 있는 모든 영속성 엔티티들을 전부 준영속성 상태로 만들 수 있다. */
//        em.clear();

        /* 설명. 영속성 컨텍스트를 닫아버리며, 영속성 컨텍스트가 제거되고, 새로운 영속성 컨텍스트를 원하면 엔티티 매니저도 다시 생성해야 한다.*/
        em.close();
//        em = emf.createEntityManager();           // 엔티티 매니저 생성

        foundMenu1.setMenuPrice(5000);
        foundMenu2.setMenuPrice(5000);

        /* 설명. 기존 11번 메뉴가 준영속 상태이므로, 11번을 find()하면 찾을 수 없어서 DB에서 새로 가져옴*/
        assertNotEquals(5000, em.find(Menu.class, 11).getMenuPrice());
        /* 설명. 기존 12번 메뉴가 준영속 상태이므로, 12번을 find()하면 찾을 수 없어서 DB에서 새로 가져옴*/
        assertNotEquals(5000, em.find(Menu.class, 12).getMenuPrice());
    }


    /* 설명. detach한 것을 다시 붙여서 가져오는 역할 */
    @Test
    public void 병합_merge_수정_테스트(){

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Menu menuToDetach = em.find(Menu.class, 5);

        em.detach(menuToDetach);

        menuToDetach.setMenuName("메론죽");
        Menu refoundMenu = em.find(Menu.class, 5);

        System.out.println("menuToDetach.hashCode() = " + menuToDetach.hashCode());
        System.out.println("refoundMenu.hashCode() = " + refoundMenu.hashCode());

        /* 설명. detach 한것을 다시 merge - merge하면 수정한 부분을 인지하고 DB에 update가 날아감 */
        em.merge(menuToDetach);

        tx.commit();
    }

    @Test
    public void 병합_merge_삽입_테스트(){

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        Menu menuToDetach = em.find(Menu.class, 6);
        em.detach(menuToDetach);

        /* 설명. Menu 클래스의 @GeneratedValue를 주석했기 때문에 PK값도 변동가능 */
        menuToDetach.setMenuCode(999);
        menuToDetach.setMenuName("은하철도햄버거");

        em.merge(menuToDetach);

        /* 설명. PK를 999로 바꿨기 때문에, DB에 존재하지 않은 번호이므로, 동기화에 의해서 update가 아니라 Insert로 들어간다.*/
        tx.commit();

        String insertMenuName = em.find(Menu.class,999).getMenuName();
        assertEquals("은하철도햄버거",insertMenuName);
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
