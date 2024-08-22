package com.ohgiraffers.common;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    /* 설명. final로 선언하여 이 List 객체만 사용하겠다는 의미 */
    private final List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    /* 설명. Shoppingcart에 물품을 담거나 꺼내는 기능 */
    public void addItem(Product item) {
        items.add(item);
    }

    public List<Product> getItems(){
        return items;
    }

}
