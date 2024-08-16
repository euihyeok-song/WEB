package com.ohgiraffers.section01.xmlconfig;

import java.util.List;
import java.util.Map;

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

    public void findMenuByMenuCode(Map<String,String> parameter) {
        int menuCode = Integer.parseInt(parameter.get("menuCode"));     // value값를 받아옴

        /* 설명. Service계층은 순수 자바 코드로 DB와 관련되어 있음으로 정수형으(menuCode)로 바꿔서 넘김*/
        MenuDTO menu = menuService.findMenuByMenuCode(menuCode);

        if(menu != null){
            printResult.printMenu(menu);                // 출력을 잘하는 친구에게 보낸다.
        } else{
            printResult.printErrorMessage(menuCode + "번의 메뉴는 없스빈다.");
        }

    }

    public void registMenu(Map<String, String> parameter) {

        /* 설명. 사용자가 입력해 넘긴 다양한 값들을 파싱하여 하나의 타입으로 넘긴다.(가공처리) */
        String menuName = parameter.get("menuName");
        int menuPrice = Integer.parseInt(parameter.get("menuPrice"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu = new MenuDTO();
        menu.setMenuName(menuName);
        menu.setMenuPrice(menuPrice);
        menu.setCategoryCode(categoryCode);

        if(menuService.registMenu(menu)) {
            printResult.printSuccessMessage("regist");
        } else{
            printResult.printErrorMessage("메뉴 추가 실패! ");
        }
    }

    public void modifyMenu(Map<String, String> parameter) {

        /* 설명. 사용자가 입력해 넘긴 다양한 값들을 파싱하여 하나의 타입으로 넘긴다.(가공처리) */
        int menuCode = Integer.parseInt("menuCode");
        String menuName = parameter.get("menuName");
        int menuPrice = Integer.parseInt("menuPrice");

        MenuDTO menu = new MenuDTO();
        menu.setMenuCode(menuCode);
        menu.setMenuName(menuName);
        menu.setMenuPrice(menuPrice);

        if(menuService.modifyMenu(menu)) {
            printResult.printSuccessMessage("modify");
        } else{
            printResult.printErrorMessage("메뉴 수정 실패! ");
        }
    }

    public void removeMenu(Map<String, String> parameter) {
        int menuCode = Integer.parseInt(parameter.get("menuCode"));

        if(menuService.removeMenu(menuCode)) {
            printResult.printSuccessMessage("remove");
        } else {
            printResult.printErrorMessage("메뉴 삭제 실패!");
        }
    }
}
