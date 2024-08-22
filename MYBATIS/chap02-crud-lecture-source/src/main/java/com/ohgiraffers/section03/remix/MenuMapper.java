package com.ohgiraffers.section03.remix;

import java.util.List;

/* 설명. 훨씬 더 Simple하게 확인 가능하다. */
public interface MenuMapper {

    List<MenuDTO> selectAllMenus();                 // 추상메소드- 호출하는 쿼리의 이름이 ID가 아닌 실제 메소드 이름이 된다.

    MenuDTO selectMenu(int menuCode);

    int insertMenu(MenuDTO menu);

    int updateMenu(MenuDTO menu);

    int deleteMenu(int menuCode);
}
