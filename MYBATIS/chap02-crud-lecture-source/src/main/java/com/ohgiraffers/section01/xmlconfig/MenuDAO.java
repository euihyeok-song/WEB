package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MenuDAO {
    public List<MenuDTO> selectAllMenus(SqlSession sqlSession) {
        /* 설명. 만약 비워있으면 비어있는 객체가 넘어감 - MenuController findAllMenus 참고*/
        return sqlSession.selectList("MenuMapper.selectAllMenus"); // List로 다중 행 조회를 진행함 (menu-mapper.xml 사용)
    }
}
