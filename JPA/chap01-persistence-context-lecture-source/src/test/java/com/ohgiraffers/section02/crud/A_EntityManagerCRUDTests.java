package com.ohgiraffers.section02.crud;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

/* 설명. JPA에서 다중행 조회를 하려면 JPQL을 사용해야 함 -> 아니면 단일행 조회만 가능 */
public class A_EntityManagerCRUDTests {
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

    @Test
    public void 메뉴코드로_메뉴_조회_테스트(){

        // given
        int menuCode = 2;

        // when -- menuCode가 2번(2가 PK값인걸 확인)인 걸 찾아달라고 Manager에게 요청
        //         없으면 DB에 find()를 통해서 select을 보내서 조회해서 가져옴
        /* 설명. find()를 정확하게 알 필요가 있다.
        *       캐쉬에 이미 있으면 DB에 더이상 가지 않고, 캐쉬에서 가져옴(5번 출력해도 1번만 나옴)
        *       find는 PK값을 가져와서 소통한다.  */
        Menu foundMenu = em.find(Menu.class, menuCode);
        Menu foundMenu2 = em.find(Menu.class, menuCode);
        Menu foundMenu3 = em.find(Menu.class, menuCode);
        Menu foundMenu4 = em.find(Menu.class, menuCode);
        Menu foundMenu5 = em.find(Menu.class, menuCode);

        // then
        Assertions.assertNotNull(foundMenu);
        Assertions.assertEquals(menuCode,foundMenu.getMenuCode());
        System.out.println("foundMenu = " + foundMenu);

    }

    @Test
    public void 새로운_메뉴_추가_테스트() {

        // given
        /* 설명. 여기는 연속성 컨텍스트가 관리해주는 부분이 아니여서 Transaction 등록이 중요함 - PK제외하고 받아줌*/
        Menu menu = new Menu();
        menu.setMenuName("꿀발린추어탕");
        menu.setMenuPrice(7000);
        menu.setCategoryCode(4);
        menu.setOrderableStatus("Y");

        // when
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            /* 설명. 영속상태의 객채를 영속성 컨텍스트로 만들어좀 - flush하기 전에 영속성 컨텍스트에 맡아준 과정 */
            em.persist(menu);
//            menu.setMenuName("식초발린매운탕");
            /* 설명. DB에 캐시내의 Entity객체가 있는지 찾고, 없으면 flush를 통해 insert 날라감 */
            tx.commit();
        } catch (Exception e){
            tx.rollback();
        }

        // then
        /* 설명. 영속성 컨텍스트에 맡아달라고 요청했는데 맡아달라고 햇는것이 있는지 물어봄 (있으면 insert 날리지 않음, 없으면 insert 날림) */
        Assertions.assertTrue(em.contains(menu));
    }

    @Test
    public void 메뉴_이름_수정_테스트(){

        // given
        /* 설명. 가져온 것은 영속성 객체이다. */
        Menu menu = em.find(Menu.class, 1);
        System.out.println("menu = " + menu);

        String menuNameToChange = "갈치스무디";

        // when
        /* 설명. Transaction 등록도 중요 */
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            /* 설명. 1. 영속성 상태에서 수정을 하면 스냅샷과 다름으로, update문을 날리는데, update는 쓰기 지연 저장소에 담긴다.
            *       2. 쓰기 지연 저장소에는 update만 담겨있음으로 flush를 통해서 DB에 날아간다.(update됨) */
            menu.setMenuName(menuNameToChange);
            /* 설명. update를 날려주는 것은 commit임으로, commit을 하지않으면, 실제 DB에는 바뀌지 않는다.*/
            tx.commit();
//            menu.setMenuName("홍삼스무디");
//            tx.commit();
//            em.remove("홍삼스무디");
        } catch (Exception e){
            tx.rollback();
        }

        // then
        /* 설명. 영속상태인 것을 가져와서 update가 되었는지 확인하기 위한 구문 */
        Assertions.assertEquals(menuNameToChange, em.find(Menu.class, 2).getMenuName());
    }

    @Test
    public void 메뉴_삭제하기_테스트(){

        // given
        Menu menuToRemove = em.find(Menu.class, 2);

        // when
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            em.remove(menuToRemove);
            tx.commit();
        } catch(Exception e){
            tx.rollback();
        }

        // then
        Menu removeMenu = em.find(Menu.class, 2);
        Assertions.assertEquals(null, removeMenu);
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
