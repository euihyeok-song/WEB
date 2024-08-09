package com.ohgiraffers.section02.annotation.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/* 설명. Bean으로 인지시키기 위한 Annotation */
@Component
/* 설명. Pokemon 타입 중에 Pikachu bean에 대한 우선권을 높이는 어노테이션 */
@Primary
public class Pikachu implements Pokemon{
    @Override
    public void attack() {
        System.out.println("피카츄 백만볼트!!");
    }
}
