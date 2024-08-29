package com.ohgiraffers.userservice.vo;

import lombok.Data;

/* 설명. HttpServlet Request를 Stream으로 담아서 보냄 => post로 보내는 값과 이름이 동일해야함 */
@Data
public class RequestLoginVO {

    private String email;
    private String pwd;
}
