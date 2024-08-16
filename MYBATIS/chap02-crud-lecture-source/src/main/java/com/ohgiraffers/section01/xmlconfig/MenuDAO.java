package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MenuDAO {
    public List<MenuDTO> selectAllMenus(SqlSession sqlSession) {
        /* 설명. 만약 비워있으면 비어있는 객체가 넘어감 - MenuController findAllMenus 참고*/
        return sqlSession.selectList("MenuMapper.selectAllMenus"); // List로 다중 행 조회를 진행함 (menu-mapper.xml 사용)
    }

    public MenuDTO selectMenuByMenuCode(SqlSession sqlSession, int menuCode) {
        /* 설명. selectOne()는 단일행을 조회함을 의미 -> menuCode를 입력받고 menu-mapper.xml에서의 # {menu_code}를 채움*/
        return sqlSession.selectOne("MenuMapper.selectMenu", menuCode);
    }

    public int insertMenu(SqlSession sqlSession, MenuDTO menu) {
        /* 설명. 넣어줄 Query와 재료(우리가 직접 만든 자료형)를 넣어줌 */
        return sqlSession.insert("MenuMapper.insertMenu",menu);
    }

    public int updateMenu(SqlSession sqlSession, MenuDTO menu) {
        return sqlSession.insert("MenuMapper.updateMenu",menu);
    }

    public int deleteMenu(SqlSession sqlSession, int menuCode) {
        return sqlSession.delete("MenuMapper.deleteMenu",menuCode);
    }
}
