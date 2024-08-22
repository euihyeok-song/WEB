package com.ohgiraffers.common;

import lombok.*;

/* 설명. lombok을 gradle에 다운받아서 이렇게 선언하면 자동으로 만들어줌 */
@NoArgsConstructor                  // 기본생성자
@AllArgsConstructor                 // 매개변수 생성자
@Setter
@Getter
@ToString
public class MemberDTO {
    private int sequence;
    private String id;
    private String pwd;
    private String name;


}
