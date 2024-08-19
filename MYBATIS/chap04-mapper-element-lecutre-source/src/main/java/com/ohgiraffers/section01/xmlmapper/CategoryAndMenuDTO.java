package com.ohgiraffers.section01.xmlmapper;

import java.util.List;

/* 설명. 사실 실무에서는 Category -> Menu는 지향한다.(거의 발생하지 X) */
public class CategoryAndMenuDTO {

    private int categoryCode;
    private String categoryName;
    /* 설명. *** 값이 없을 경우 NULL값을 띄우고 싶으면, Wrapper클래스 사용(사용자로부터의 입력이 없거나, 값이 없는 상위 필드)****/
    private Integer refCategoryCode;                 // Wrapper class형태(nullable)
    /* 설명. Collction관계(1대 M)는 추가로 들어감 */
    private List<MenuDTO> menus;                    // Collection 관계인 경우는 속성이 늘어난다.

    public CategoryAndMenuDTO() {
    }

    public CategoryAndMenuDTO(int categoryCode, String categoryName, Integer refCategoryCode, List<MenuDTO> menus) {
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.refCategoryCode = refCategoryCode;
        this.menus = menus;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(int categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getRefCategoryCode() {
        return refCategoryCode;
    }

    public void setRefCategoryCode(Integer refCategoryCode) {
        this.refCategoryCode = refCategoryCode;
    }

    public List<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuDTO> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "CategoryAndMenuDTO{" +
                "categoryCode=" + categoryCode +
                ", categoryName='" + categoryName + '\'' +
                ", refCategoryCode=" + refCategoryCode +
                ", menus=" + menus +
                '}';
    }
}