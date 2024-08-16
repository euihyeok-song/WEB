package com.ohgiraffers.section01.xml;

import java.util.List;

public interface MenuMapper {

    /* 설명. MenuMapper interface에서는 추상메소드 이름만, 이에 해당하는 쿼리는 MenuMaooer.xml*/
    List<MenuDTO> selectMenuByPrice(int price);

    List<MenuDTO> searchMenu(SearchCriteria searchCriteria);
}
