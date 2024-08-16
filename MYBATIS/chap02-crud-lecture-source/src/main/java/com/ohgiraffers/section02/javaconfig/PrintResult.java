package com.ohgiraffers.section02.javaconfig;

import java.util.List;

public class PrintResult {
    public void printMenus(List<MenuDTO> menuList) {
        /* 설명. 컬렉션은 자체에서 Stream을 사용할 수 있다 (stream()을 해줄 필요 X)*/
        menuList.forEach(System.out::println);
    }

    public void printErrorMessage(String message) {
        System.out.println("에러메시지: " + message);
    }

    public void printMenu(MenuDTO menu) {
        System.out.println("menu = " + menu);
    }

    /* 설명. DML 작업 성공 시 해당 성공 메시지 출력용 메소드 */
    public void printSuccessMessage(String statusCode) {
        String successMessage = "";

        switch (statusCode){
            case "regist": successMessage = "신규 메뉴 등록에 성공하였습니다."; break;
            case "modify": successMessage = "메뉴 수정에 성공하였습니다."; break;
            case "remove":successMessage = "메뉴 삭제에 성공하였습니다."; break;
        }

        System.out.println("successMessage = " + successMessage);
    }
}
