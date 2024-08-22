package com.ohgiraffers.section01.manytoone;

import jakarta.persistence.*;

@Entity(name="menu_and_category")
@Table(name="tbl_menu")
public class MenuAndCategory {

    @Id
    @Column(name="menu_code")
    private int menuCdoe;

    @Column(name="menu_price")
    private int menuPrice;


    /* 설명. @ManyToOne도 실 개발에서는 많이 나오면 1번 나올까 말까 이다.*/
    /* 설명. JoinColumn에 쓰이는 컬럼명은 FK 제역조건이 걸린 자식 테이블의 컬럼명을 쓰게 된다. */
    @JoinColumn(name="category_code")
    /* 설명. @ManyToOne: 두 엔티티 간의 전체 카디널리티(N:1)를 고려해서 작성*/
    @ManyToOne
    /* 설명. Category: 하나의 메뉴는 하나의 카테고리를 지니고 있다.*/
    private Category category;

    @Column(name="orderable_status")
    private String orderableStatus;

    public MenuAndCategory() {
    }

    public MenuAndCategory(int menuCdoe, int menuPrice, Category category, String orderableStatus) {
        this.menuCdoe = menuCdoe;
        this.menuPrice = menuPrice;
        this.category = category;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCdoe() {
        return menuCdoe;
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
        return "MAnyAndCategory{" +
                "menuCdoe=" + menuCdoe +
                ", menuPrice=" + menuPrice +
                ", category=" + category +
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
