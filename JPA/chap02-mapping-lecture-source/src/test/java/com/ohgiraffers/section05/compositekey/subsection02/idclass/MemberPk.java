package com.ohgiraffers.section05.compositekey.subsection02.idclass;

import java.io.Serializable;
import java.util.Objects;

public class MemberPk implements Serializable {
    private int memberNo;
    private String memberId;

    public MemberPk() {
    }

    public MemberPk(int memberNo, String memberId) {
        this.memberNo = memberNo;
        this.memberId = memberId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        MemberPk memberPk = (MemberPk) object;
        return memberNo == memberPk.memberNo && Objects.equals(memberId, memberPk.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberNo, memberId);
    }
}
