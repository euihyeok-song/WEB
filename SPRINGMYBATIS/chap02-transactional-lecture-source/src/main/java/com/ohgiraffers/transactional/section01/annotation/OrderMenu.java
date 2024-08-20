package com.ohgiraffers.transactional.section01.annotation;

/* 설명. DB에 한행을 가져와서 하나의 값으로 담아내야하기 떄문에 처음부터 setter를 만들지 X (DTO 개념이 X -> Entity 개념)
*       필요할 떄마다 setter를 만들어라 */
public class OrderMenu {
    private int menuCode;
    private int orderCode;
    private int orderAmount;

    public OrderMenu() {
    }

    public OrderMenu(int menuCode, int orderCode, int orderAmount) {
        this.menuCode = menuCode;
        this.orderCode = orderCode;
        this.orderAmount = orderAmount;
    }

    public OrderMenu(int menuCode, int orderAmount) {
        this.menuCode = menuCode;
        this.orderAmount = orderAmount;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    @Override
    public String toString() {
        return "OrderMenu{" +
                "menuCode=" + menuCode +
                ", orderCode=" + orderCode +
                ", orderAmount=" + orderAmount +
                '}';
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }
}
