package com.ohgiraffers.common;

public class PersonalAccount implements Account {
    private final int bankCode;     // 은행 코드
    private final String accNo;     //계좌번호
    private int balance;            // 잔액

    public PersonalAccount(int bankCode, String accNo) {
        this.bankCode = bankCode;
        this.accNo = accNo;
    }

    @Override
    public String toString() {
        return "PersonalAccount{" +
                "accNo='" + accNo + '\'' +
                ", bankCode=" + bankCode +
                ", balance=" + balance +
                '}';
    }

    @Override
    public String getBalance() {
        return accNo + "계좌의 현재 잔액은 " + balance + "원 입니다.";
    }

    @Override
    public String deposit(int money) {
        String str = "";

        if (money >= 0) {
            this.balance += money;
            str = money + "원이 입금되었습니다.";
        } else {
            str = "금액을 잘못 입력하셨습니다.";
        }

        return str;
    }

    /* 설명. 출금 재정의  */
    @Override
    public String withdraw(int money) {
        String str = "";

        if (balance >= money) {
            balance -= money;
            str = money + "원이 출금되었습니다.";
        } else {
            str = "잔액이 부족합니다. 잔액을 확인해 주세요";
        }

        return str;
    }


}
