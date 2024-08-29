package com.ohgiraffers.userservice.vo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data       // getter + setter + toString + 생성자등등 생성
public class Hello {

    /* 설명. 이는 controller에서 Environment를 사용하는 것과 동일하다. - bean으로 관리된다.*/
    @Value("${hello.message}")          //  yml 파일의 hello.message부분의 값을 받아온다.
    private String message;
}
