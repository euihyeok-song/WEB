package com.ohgiraffers.section01.xmlconfig;

import java.util.List;

public class PrintResult {
    public void printMenus(List<MenuDTO> menuList) {
        /* 설명. 컬렉션은 자체에서 Stream을 사용할 수 있다 (stream()을 해줄 필요 X)*/
        menuList.forEach(System.out::println);
    }

    public void printErrorMessage(String message) {
        System.out.println("에러메시지: " + message);
    }
}
