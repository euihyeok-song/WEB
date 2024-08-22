package com.ohgiraffers.section01.xml;

import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

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

    public void searchMenuBySupCategory(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();

        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);
        List<MenuDTO> menus = mapper.searchMenuBySupCategory(searchCriteria);

        /* 설명. 조회가 안되는 경우를 대비 - NullpointerException을 예방하는 코드 */
        if(menus != null && menus.size() > 0){
            menus.forEach(System.out::println);
        } else{
            System.out.println("DB와의 연동 실패 또는 검색 결과 없음");
        }

        sqlSession.close();
    }

    public void searchMenuByRandomMenuCode(List<Integer> randomList) {

        SqlSession sqlSession = getSqlSession();

        /* 설명. 쿼리를 알고 있는 하위 구현체가 돌아오는 개념 */
        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);
        List<MenuDTO> menus = mapper.searchMenuByRandomMenuCode(randomList);

        if(menus != null && menus.size() > 0){
            menus.forEach(System.out::println);
        } else{
            System.out.println("DB와 연동 실패 또는 검색 결과 없음");
        }

        sqlSession.close();
    }

    public void searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();

        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);
        List<MenuDTO> menus = mapper.searchMenuByCodeOrSearchAll(searchCriteria);

        if(menus != null && menus.size() > 0){
            menus.forEach(System.out::println);
        } else{
            System.out.println("DB와 연동 실패 또는 검색 결과 없음");
        }

        sqlSession.close();

    }

    public void searchMenuByNameOrCategory(Map<String, Object> criteria) {

        SqlSession sqlSession = getSqlSession();

        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);
        List<MenuDTO> menus = mapper.searchMenuByNameOrCategory(criteria);

        if(menus != null && menus.size() > 0){
            menus.forEach(System.out::println);
        } else{
            System.out.println("DB와 연동 실패 또는 검색 결과 없음");
        }

        sqlSession.close();
    }

    public void modifiyMenu(Map<String, Object> criteria) {

        SqlSession sqlSession = getSqlSession();

        MenuMapper mapper = sqlSession.getMapper(MenuMapper.class);

        int result = mapper.updateMenu(criteria);

        if(result > 0){
            System.out.println(criteria.get("menuCode") + "메뉴 정보 변경에 성공하였습니다.");
            sqlSession.commit();
        } else{
            System.out.println("메뉴 정보 수정에 실패하였습니다.");
            sqlSession.rollback();
        }

        sqlSession.close();
    }
}
