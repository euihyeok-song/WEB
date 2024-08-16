package com.ohgiraffers.section01.xml;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section01.xml.Template.getSqlSession;

public class MenuService {
    public void findMenuByPrice(int price) {
        SqlSession sqlSession = getSqlSession();
//        System.out.println("sqlSession = " + sqlSession);
        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);

        List<MenuDTO> menus = mapper.selectMenuByPrice(price);
        menus.forEach(System.out::println);

        sqlSession.close();
    }

    public void searchMenu(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();

        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);
        /* 설명. like '%밥%'와 같이 검색하기 떄문에 다중행 조회를 가정하여 만들어야함 */
        List<MenuDTO> menus = mapper.searchMenu(searchCriteria);
        menus.forEach(System.out::println);


        sqlSession.close();
    }
}
