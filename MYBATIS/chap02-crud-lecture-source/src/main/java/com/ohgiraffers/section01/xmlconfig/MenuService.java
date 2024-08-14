package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/* 설명. import static을 통해서 클래스 내의 메소드를 한번에 가져옴 */
import static com.ohgiraffers.section01.xmlconfig.Templates.getSqlSession;

public class MenuService {

    private final MenuDAO menuDAO;

    /* 설명. 생성자 주입 */
    public MenuService() {
        menuDAO = new MenuDAO();
    }

    /* 설명. 조회하는 기능 */
    public List<MenuDTO> findAllMenus() {
        SqlSession sqlSession = getSqlSession();

        List<MenuDTO> menuList = menuDAO.selectAllMenus(sqlSession);

        sqlSession.close();

        return menuList;
    }
}
