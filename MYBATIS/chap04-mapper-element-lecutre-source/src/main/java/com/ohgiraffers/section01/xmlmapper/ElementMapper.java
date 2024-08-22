package com.ohgiraffers.section01.xmlmapper;

import java.util.List;

public interface ElementMapper {

    /* 설명. MenuDTO에서 4개의 필드값으로 ResultMap을 만들고 나머지 1개를 넣는 개념 */
    List<MenuDTO> selectResultMapTest();

    List<MenuAndCategoryDTO> selectResultMapAssociationTest();

    List<CategoryAndMenuDTO> selectResultMapCollectionTest();
}
