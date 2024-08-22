package com.ohgiraffers.section01.javaconfig;

import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface Mapper {

    /* 설명. Query의 이름으로 @Select를 통해 Query의 결과를 반환형(Date)으로 바꿔줌*/
    @Select("SELECT NOW()")
    Date selectNow();
}
