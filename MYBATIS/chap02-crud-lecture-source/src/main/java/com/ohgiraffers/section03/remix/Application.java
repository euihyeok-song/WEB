package com.ohgiraffers.section03.remix;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/* 설명. remix란 Template은 java로, config는 xml을 이용하는 방식*/
/* 필기.
 *  xml 방식과 javaconfig 방식 두 가지를 혼용한 remix 방식은 각각의 이전 방식의 단점을 보완할 수 있다.
 *  (단점들 -> xml 방식: namespace를 매번 작성해야 한다.
 *           javaconfig 방식: 쿼리가 어노테이션에 깔끔하게 정리되기 힘들어 가독성이 떨어진다.(메소드 구분도 쉽지 않음))
 *  (namespace와 mapper용 인터페이스를 맞춰주고 쿼리는 xml로 따로 분리하는 것으로 해결)
 *
 * 필기.
 *  remix 방식을 적용하기 위한 규칙
 *  1. mapper용 인터페이스와 mapper용 xml 파일 이름 동일(경로도 맞춰서 적어야함 resources/com/ohgiraffers/section03/remix/~)
 *  2. mapper용 인터체이스와 풀 클래스명이 mapper용 xml 파일의 namespace가 되어야 한다.
 *  3. mapper용 인터페이스와 mapper용 xml 파일의 위치가 동일
 *  4. mapper용 인터페이스의 추상 메소드명이 실행 될 xml 파일의 쿼리 id와 동일
*/
public class Application {
    public static void main(String[] args) {

        MenuController menuController = new MenuController();
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("====== 메뉴 관리 ======");
            System.out.println("1. 메뉴 전체 조회");
            System.out.println("2. 메뉴 코드로 메뉴 조회");
            System.out.println("3. 신규 메뉴 추가");
            System.out.println("4. 메뉴 수정");
            System.out.println("5. 메뉴 삭제");
            System.out.println("9. 프로그램 종료");
            System.out.print("매뉴 관리 번호를 입력하세요: ");
            int no = sc.nextInt();

            switch(no){
                case 1:
                    menuController.findAllMenus();
                    break;
                case 2:
                    menuController.findMenuByMenuCode(inputMenuCode());
                    break;
                case 3:
                    menuController.registMenu(inputMenu());
                    break;
                case 4:
                    menuController.modifyMenu(inputModifyMenu());
                    break;
                case 5:
                    menuController.removeMenu(inputMenuCode());
                    break;
                case 9:
                    System.out.println("프로그램을 종료하겠습니다."); return;
                default:
                    System.out.println("번호를 잘못 입력하였습니다. ");
            }
        } while(true);
    }


    /* 설명. 웹에서 사용자의 입력값은 항상 Map형태의 key,value값으로 넘어간다. */
    private static Map<String,String> inputMenuCode() {
        Scanner sc = new Scanner(System.in);
        System.out.print("메뉴 코드를 입력하세요: ");
        String menuCode = sc.nextLine();

        Map<String,String> parameter = new HashMap<>();
        parameter.put("menuCode",menuCode);

        return parameter;
    }

    private static Map<String,String> inputMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("신규 메뉴의 이름을 입력해 주세요: ");
        String menuName = sc.nextLine();
        System.out.print("신규 메뉴의 가격을 입력해 주세요: ");
        String menuPrice = sc.nextLine();
        System.out.print("신규 메뉴의 카테고리 코드를 입력해 주세요: ");
        String categoryCode = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("menuName",menuName);
        parameter.put("menuPrice",menuPrice);
        parameter.put("categoryCode", categoryCode);

        return parameter;
    }

    private static Map<String,String> inputModifyMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 메뉴의 번호를 입력해 주세요: ");
        String menuCode = sc.nextLine();
        System.out.print("변경할 메뉴의 이름를 입력해 주세요: ");
        String menuName = sc.nextLine();
        System.out.print("변경할 메뉴의 가격를 입력해 주세요: ");
        String menuPrice = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("menuCode",menuCode);
        parameter.put("menuName",menuName);
        parameter.put("menuPrice",menuPrice);

        return parameter;
    }
}
