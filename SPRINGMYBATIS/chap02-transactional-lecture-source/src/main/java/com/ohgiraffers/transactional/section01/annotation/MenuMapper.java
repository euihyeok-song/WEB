package com.ohgiraffers.transactional.section01.annotation;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/* 설명. Query를 담는 Mapper 개념 - Query를 받아올 Mapper.xml을 resource에 동일한 경로와 파일명으로 정의 필요 */
@Mapper
public interface MenuMapper {

    List<Menu> selectMenuByMenuCodes(Map<String, List<Integer>> map);

}
