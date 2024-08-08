package com.ohgiraffers.common;

import lombok.*;

/* 설명. 실제 서비스 단계에서는 @Data는 블필요한 것들이 많이 만들어짐으로, 권장 X*/
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@Builder                    // getter setter없이 builder 할 경우
public class MemberDTO {
    private int sequence;
    private String name;
    private String phone;
    private String email;
    private Account personalAccount;
}

/* 설명. MemberDTO -> has a(의존 관계 = 연관 관계)  -> Account 관계 객체
*       has a 관계인 객체를 필드에 넣어주는 것을 DI라고 한다.*/
