package com.ohgiraffers.section03.persistencecontext;

import jakarta.persistence.*;

/* 설명. Entity: DB의 Table과 매칭이 되는 부분 */
/* 설명. Bean과 같이 @Entity를 등록하면 Menu도 menu라는 Entity가 등록되어 관리된다.
 *        Entity의 이름은 달리해줘야 함 ( section02_menu로 선언하면 오류 발생 )
 * */
@Entity(name = "section03_menu")
/* 설명. 연관있는 Table의 이름을 적어줌*/
@Table(name="tbl_menu")
public class Menu {

    /* 설명. persist에 값을 넣을 경우 PK는 건들면 안되므로, @Id는 뗴고 넣어야한다.*/
    @Id
    @Column(name="menu_code")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
