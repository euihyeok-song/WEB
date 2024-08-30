package com.ohgiraffers.userservice.vo;

import lombok.Data;

/* 설명. 사용자가 입력한 값이 아닌 시스템에 의해 추가된 값을 추가해서 넘겨줌 */
@Data
public class ResponseRegistUserVO {
    private String email;
    private String name;
    private String userId;                  // 회원가입 이후 생성된 회원 고유 번호(닉네임 개념)
}
