package com.ohgiraffers.section05.compositekey.subsection01.embedded;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

// 그냥 클래스 x, vo와 같이 값이 들어가는 것(복합키 전용 클래스, 내장 클래스)
/* 설명.
 *  Embeddable 타입은 하나의 값의 묶음이자 불변 객체로 다루는 타입이다.
 *  (불변 객체: setter가 없고 필드값에 변화를 주고 싶으면 새로운 필드 값을 가지는
 *   새로운 객체가 생성되어야 한다.)
 *  그리고 equals, hashCode를 반드시 오버라이딩 해야 한다.
 *  VO와 유사하며 @EmbeddedId로 복합키를 표현하고자 할 때 쓰이기도 한다.
* */
@Embeddable
public class MemberPK implements Serializable {

    // 둘다 pk값이 되는 것들(동등 비교 가능, 값으로 취급, 내부적으로 시리얼라이저블 가능해야한다. (불변 객체)

    @Column(name="member_no")
    private int memberNo;

    @Column(name="member_id")
    private String memberId;

    public MemberPK() {
    }

    public MemberPK(int memberNo, String memberId) {
        this.memberNo = memberNo;
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MemberPK memberPK = (MemberPK) object;
        return memberNo == memberPK.memberNo && Objects.equals(memberId, memberPK.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, memberId);
    }
}
