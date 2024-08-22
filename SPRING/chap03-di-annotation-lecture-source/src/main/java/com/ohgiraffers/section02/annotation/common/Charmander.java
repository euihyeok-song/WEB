package com.ohgiraffers.section02.annotation.common;

import org.springframework.stereotype.Component;

/* 설명. Bean으로 인지시키기 위한 Annotation */
@Component
public class Charmander implements Pokemon{
    @Override
    public void attack() {
        System.out.println("파이리 불꽃 공격!!");
    }
}
