package com.ohgiraffers.springmybatis.section01.factorybean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/* 설명. SpringBootTest를 사용하면 DB까지 연결해서 Service 계층에서의 method 단위 Test가 가능 */
@SpringBootTest
class MenuServiceTest {

    /* 설명. 의존성 주입 - Test 주입은 필드 주입을 주로 사용 */
    @Autowired
    private MenuService menuService;

    @DisplayName("주문가능 상태형 메뉴 조회 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {"Y", "N"})
    void testFindAllMenus(String orderableStatus){
        Assertions.assertDoesNotThrow(
                () -> {
                    List<MenuDTO> menus = menuService.findAllMenusByOrderableStatus(orderableStatus);
                    menus.forEach(System.out::println);
                }
        );
    }
}