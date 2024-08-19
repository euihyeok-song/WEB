package com.ohgiraffers.section01.xml;

import java.util.List;
import java.util.Map;

public interface MenuMapper {

    /* 설명. MenuMapper interface에서는 추상메소드 이름만, 이에 해당하는 쿼리는 MenuMapper.xml*/
    List<MenuDTO> selectMenuByPrice(int price);

    List<MenuDTO> searchMenu(SearchCriteria searchCriteria);

    List<MenuDTO> searchMenuBySupCategory(SearchCriteria searchCriteria);

    List<MenuDTO> searchMenuByRandomMenuCode(List<Integer> randomList);

    List<MenuDTO> searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria);

    List<MenuDTO> searchMenuByNameOrCategory(Map<String, Object> criteria);

    int updateMenu(Map<String, Object> criteria);
}
