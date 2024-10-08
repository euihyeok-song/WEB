package com.ohgiraffers.bootproject.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor      /* 매개변수가 있는 생성자 */
//@EqualsAndHashCode          // DTO는 동등비교를 할일이 없음으로 필요 X
public class CalculatorDTO {

    /* 설명. 1. 매개변수가 있는 생성자 적용법 (권장 X)*/
//    private final int num1 = 1;       // 사용하려면 @NoArgsConstructor를 쓰지 않아여함

    /* 설명. 2. 매개변수가 있는 생성자 적용법 (권장 O) */
    @NonNull
    private int num1;
    @NonNull
    private int num2;
    private int sum;

}
