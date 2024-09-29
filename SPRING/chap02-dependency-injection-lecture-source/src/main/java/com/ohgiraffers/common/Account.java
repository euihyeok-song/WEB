package com.ohgiraffers.common;

/* 추상화 계층을 끼워서 Account와 PersonalAccount의 의존성을 받음 */
/* Account와 personalAccount는 서로에게 바로 직접적인 영향을 미치지 X */
public interface Account {

    /* 설명. 잔액 조회 */
    String getBalance();

    /* 설명. 입금 */
    String deposit(int money);

    /* 설명. 출금 */
    String withdraw(int money);
}
