package com.ohgiraffers.transactional.section01.annotation;

/* 설명. DB에 한행을 가져와서 하나의 값으로 담아내야하기 떄문에 setter를 만들지 X (DTO 개념이 X -> Entity 개념)
*       DB 테이블에서 한행씩 실제 값을 가져와서 담는 것은 아래 처럼 Setter를 넣지 않고 만들고,
*       이 가져온 테이블에서 값을 뽑아서 사용할 경우에는 DTO로 만들어서 사용한다.
* */
public class Menu {
    private int menuCode;
    private String menuName;
    private int menuPrice;
    private int categoryCode;
    private String orderableStatus;

    public Menu() {
    }

    public Menu(int menuCode, String menuName, int menuPrice, int categoryCode, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", categoryCode=" + categoryCode +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
