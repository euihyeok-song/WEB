package com.ohgiraffers.section01.xmlmapper;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getSqlSession;

public class ElementService {

    public void selectResultMapTest() {
        SqlSession sqlSession = getSqlSession();

        ElementMapper mapper = sqlSession.getMapper(ElementMapper.class);

        List<MenuDTO> menus = mapper.selectResultMapTest();
        menus.forEach(System.out::println);

        sqlSession.close();
    }

    /* 설명. 하나의 entity가 다른 entity의 속송 1개만 필요한 경우(M:1) -> Menu를 기준으로 Category의 속성을 가져옴 */
    public void selectResultMapAssociationTest() {
        SqlSession sqlSession = getSqlSession();

        ElementMapper mapper = sqlSession.getMapper(ElementMapper.class);

        List<MenuAndCategoryDTO> menus = mapper.selectResultMapAssociationTest();
        menus.forEach(System.out::println);                 // repository에서 잘 돌아왔는지 확인해보는 문

        /* 설명. Association을 사용하면 객체 그래프 탐색(객체를 타고타고하면서 내부 객체의 정보를 꺼낼수 있음) 가능해짐
        *       MyBatis는 JOIN이 가능하고, 아래 처럼 객체 사용  */
        System.out.println("첫 번째 메뉴의 카테고림명: " + menus.get(0).getCategory().getCategoryName());

        sqlSession.close();
    }

    public void selectResultMapCollectionTest() {
        SqlSession sqlSession = getSqlSession();

        ElementMapper mapper = sqlSession.getMapper(ElementMapper.class);

        List<CategoryAndMenuDTO> categories = mapper.selectResultMapCollectionTest();
        categories.forEach(System.out::println);

        sqlSession.close();
    }
}
