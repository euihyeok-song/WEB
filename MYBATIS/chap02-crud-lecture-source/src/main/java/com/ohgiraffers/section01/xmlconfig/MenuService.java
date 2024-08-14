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
        /* 설명. DAO에서는 실제 DB와 관련해서 CRUD를 진행하기 때문에 JAVA 프로젝트에서 결과값을 0,1로 받아왔던 것 처럼
        *       DB를 잘 받아왔는지 확인하고 처리해주기 위해서 Service 계층에서 선언한다.*/
        SqlSession sqlSession = getSqlSession();            // DB에서 query를 실행시키기 위해서 session 생성

        List<MenuDTO> menuList = menuDAO.selectAllMenus(sqlSession);

        sqlSession.close();

        return menuList;
    }
}
