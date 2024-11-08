package com.ohgiraffers.section01.manytoone;

import jakarta.persistence.*;

@Entity(name="menu_and_category")
@Table(name="tbl_menu")
public class MenuAndCategory {

    @Id
    @Column(name="menu_code")
    private int menuCode;

    @Column(name="menu_name")
    private String menuName;

    @Column(name="menu_price")
    private int menuPrice;

    /* 설명. JoinColumn에 쓰이는 컬럼명은 FK 제약조건이 걸린 자식 테이블의 컬럼명을 쓰게 된다. */
    @JoinColumn(name="category_code")
    @ManyToOne                         // 두 엔티티 간의 전체 카디널리티(N:1)를 고려해서 작성
    private Category category;         // 하나의 메뉴는 하나의 카테고리를 지니고 있다.

    @Column(name="orderable_status")
    private String orderableStatus;

    public MenuAndCategory() {
    }

    public MenuAndCategory(int menuCode, String menuName, int menuPrice, Category category, String orderableStatus) {
        this.menuCode = menuCode;
        this.menuName = menuName;
        this.menuPrice = menuPrice;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "MenuAndCategory{" +
                "menuCode=" + menuCode +
                ", menuName='" + menuName + '\'' +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
