package com.ohgiraffers.userservice.vo;

import lombok.Data;

/* 설명. 회원가입 시 사용자의 입력값을 받아낼 커맨드 객체용 클래스 */
/* 설명. 커멘드 객체는 HttpServletRequest를 통해 들어온 요청 값을 setter를 통해 객체에 정의된 속성과 매핑해주는 객체 */
@Data
public class RequestResistUserVO {

    private String email;
    private String name;
    private String pwd;                 // 암호화 되기 전 사용자가 입력한 평문(비밀번호) - (plain text)


}
