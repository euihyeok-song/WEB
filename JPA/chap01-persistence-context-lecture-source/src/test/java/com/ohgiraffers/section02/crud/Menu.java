package com.ohgiraffers.section02.crud;

import jakarta.persistence.*;

/* 설명. Entity: DB의 Table과 매칭이 되는 부분 */
/* 설명. Bean과 같이 @Entity를 등록하면 Menu도 menu라는 Entity가 등록되어 관리된다.(Entity의 이름은 달리해줘야 함) */
@Entity(name = "section02_menu")
/* 설명. 연관있는 Table의 이름을 적어줌*/
@Table(name="tbl_menu")
public class Menu {

    /* 설명. Entity도 곧 Table이기 떄문에 PK의 개념이 필요 */
    @Id
    /* 설명. 필드 이름을 갖고 알아서 column과 매칭이 시킴 (menuCode -> menu_code : 표기법은 설정 가능 )*/
    @Column(name="menu_code")
    /* 설명. Auto_increment와 같은 제약조건 등록 가능 (직접 타고 들어가서 확인 가능) */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuCode;

    @Column(name="menu_name")
    private String menuName;

    @Column(name="menu_price")
    private int menuPrice;

    @Column(name="category_code")
    private int categoryCode;

    @Column(name="orderable_status")
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

    public void setMenuCode(int menuCode) {
        this.menuCode = menuCode;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void setMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public void setOrderableStatus(String orderableStatus) {
        this.orderableStatus = orderableStatus;
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
