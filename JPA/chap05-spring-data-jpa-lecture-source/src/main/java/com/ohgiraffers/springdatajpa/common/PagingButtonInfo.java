package com.ohgiraffers.springdatajpa.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
/* 설명. 화면에 담을 페이지 재료를 넣어좀 */
public class PagingButtonInfo {

    private int currentPage;
    private int startPage;
    private int endPage;

}
