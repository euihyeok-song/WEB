package com.ohgiraffers.section01.xmlconfig;

import java.util.List;

public class MenuController {

    private final MenuService menuService;
    private final PrintResult printResult;              // 출력을 잘하는 거를 따로 만듦

    /* 설명. 생성자 주입 방식 - 의존성 주입 */
    public MenuController() {
        menuService = new MenuService();
        printResult = new PrintResult();
    }

    public void findAllMenus() {
        List<MenuDTO> menuList = menuService.findAllMenus();

        if(!menuList.isEmpty()){                // 비워져있다 != Null (같은 의미 X)
            printResult.printMenus(menuList);
        } else {
            printResult.printErrorMessage("조회할 메뉴가 없습니다.");
        }
    }
}
