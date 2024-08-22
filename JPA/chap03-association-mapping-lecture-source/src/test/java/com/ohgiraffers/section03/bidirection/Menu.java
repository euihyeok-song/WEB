package com.ohgiraffers.section03.bidirection;

import jakarta.persistence.*;

@Entity(name="menu_section03")
@Table(name="tbl_menu")
public class Menu {

    @Id
    @Column(name="menu_code")
    private int menuCdoe;

    @Column(name="menu_price")
    private int menuPrice;

    @JoinColumn(name="category_Code")
    @ManyToOne
    private Category categoryCode;

    @Column(name="orderable_status")
    private String orderableStatus;


    public Menu() {
    }

    public Menu(int menuCdoe, int menuPrice, Category categoryCode, String orderableStatus) {
        this.menuCdoe = menuCdoe;
        this.menuPrice = menuPrice;
        this.categoryCode = categoryCode;
        this.orderableStatus = orderableStatus;
    }

    public int getMenuCdoe() {
        return menuCdoe;
    }

    public int getMenuPrice() {
        return menuPrice;
    }

    public Category getCategoryCode() {
        return categoryCode;
    }

    public String getOrderableStatus() {
        return orderableStatus;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuCdoe=" + menuCdoe +
                ", menuPrice=" + menuPrice +
//              ", categoryCode=" + categoryCode.toStirng() +  // 이런식으로 되어서 서로가 서로를 참조해서 계속 순환참조 발생 (삭제필요)
                                                                // 엔티티가 엔티티를 막도록 구현됨
                ", orderableStatus='" + orderableStatus + '\'' +
                '}';
    }
}
