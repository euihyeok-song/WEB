package com.ohgiraffers.userservice.vo;

import lombok.Data;

/* 설명. 마지막에 보여질 페이지의 재료들 */
@Data
public class ResponseFindUserVO {

    private String email;
    private String name;
    private String userId;
}
