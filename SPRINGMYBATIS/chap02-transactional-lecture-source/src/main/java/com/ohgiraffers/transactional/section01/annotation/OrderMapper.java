package com.ohgiraffers.transactional.section01.annotation;

import org.apache.ibatis.annotations.Mapper;

/* 설명. Query를 담는 Mapper 개념 */
/* 설명. Getmapper()를 사용하지 않고, @Mapper Annotation을 달아주면 MapperScan을 통해서 인지해줌(MybatisConfig 참고) */
@Mapper
public interface OrderMapper {
    void registOrder(Order order);

    void registOrderMenu(OrderMenu orderMenu);
}
