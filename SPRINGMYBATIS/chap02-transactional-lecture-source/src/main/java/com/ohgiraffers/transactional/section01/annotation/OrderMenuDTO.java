package com.ohgiraffers.transactional.section01.annotation;

/* 설명. DTO는 setter 포함가능*/
public class OrderMenuDTO {
    /* 설명. 사용자가 여러 명일 수 있기 떄문에 DTO 설계시 주문번호는 만들지 X (Insert 전에 주문 번호가 들어올 수는 없다)*/
    private int menuCode;           // 고른 메뉴 번호
    private int orderAmount;         // 고른 메뉴의 갯수

    public OrderMenuDTO() {
    }

    public OrderMenuDTO(int menuCode, int orderAmount) {
        this.menuCode = menuCode;
        this.orderAmount = orderAmount;
    }

    public int getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public int getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(int orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public String toString() {
        return "OrderMenuDTO{" +
                "menuCode=" + menuCode +
                ", orderAmount=" + orderAmount +
                '}';
    }
}
