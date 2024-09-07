package com.ohgiraffers.userservice.vo;

import lombok.Data;

import java.util.List;

@Data
public class ResponseOrder {
    private String orderDate;
    private String orderTime;

    private List<OrderMenuVO> orderMenus;


}
