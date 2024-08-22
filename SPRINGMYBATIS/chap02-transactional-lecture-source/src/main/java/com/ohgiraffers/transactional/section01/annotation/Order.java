package com.ohgiraffers.transactional.section01.annotation;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/* 설명. DB에 한행을 가져와서 하나의 값으로 담아내야하기 떄문에 setter를 만들지 X (DTO 개념이 X -> Entity 개념) */
public class Order {
    private int orderCode;
    private String orderDate;    // String 선언: local date나 local time을 DB로 그대로 넣지않고, 포멧팅 후 원하는 자료를 넣기위함
    private String orderTime;
    private int totalOrderPrice;

    public Order() {
    }

    public Order(int orderCode, String orderDate, String orderTime, int totalOrderPrice) {
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalOrderPrice = totalOrderPrice;
    }

    public Order(LocalDate orderDate, LocalTime orderTime, int totalOrderPrice) {

        /* 설명. LocalDate 또는 LocalTime형을 DB에 맞춰서 저장하기 위한 변환작업 */
        this.orderDate = orderDate.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        this.orderTime = orderTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        this.totalOrderPrice = totalOrderPrice;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderCode=" + orderCode +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", totalOrderPrice=" + totalOrderPrice +
                '}';
    }
}
