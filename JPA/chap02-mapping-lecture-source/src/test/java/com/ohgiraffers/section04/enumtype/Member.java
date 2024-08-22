package com.ohgiraffers.section04.enumtype;

import jakarta.persistence.*;

import java.util.Date;

// enum 타입으로 값 설정
@Entity(name="member_section04")    // 엔티티 객체로 만들기 위한 어노테이션, 다른 패키지에 동일한 이름이 있으면 안됨
@Table(name="tbl_member_section04") // 데이터베이스에 매핑 될 테이블 이름 설정
public class Member {

    @Id                             // PK에 해당하는 속성에 지정(복합키일 경우 두 가지 방식 존재(이후에 다룰 예정)), 복합키 X인 경우
    @Column(name="member_no")       // 데이터베이스에 대응되는 컬럼명 지정(타입 및 여러 제약조건 설정 가능), (ex. varchar(20), check 제약조건)
    private int memberNo;           // 이후에 auto-increase 같은거 다룰 예정

    @Column(name="member_id")
    private String memberId;

    @Column(name="member_pwd")
    private String memberPwd;

    @Column(name="nickname")
    private String nickname;

    @Column(name="phone")
    private String phone;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @Column(name="enroll_date")
    private Date enrollDate;

    // 문자열이나 숫자 저장 가능(숫자 : ORDINAL, 문자: STRING)
    @Column(name="member_role")
//    @Enumerated(EnumType.ORDINAL)     // DB에 숫자로 값이 들어감(ex: 0 또는 1)
    @Enumerated(EnumType.STRING)        // DB에 문자열로 값이 들어감(ex: ADMIN 또는 MEMBER)
    private RoleType memberRole;

    @Column(name="status")
    private String status;

    public Member() {
    }

    public Member(int memberNo, String memberId, String memberPwd, String nickname, String phone, String email, String address, Date enrollDate, RoleType memberRole, String status) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.nickname = nickname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.enrollDate = enrollDate;
        this.memberRole = memberRole;
        this.status = status;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public RoleType getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(RoleType memberRole) {
        this.memberRole = memberRole;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", enrollDate=" + enrollDate +
                ", memberRole=" + memberRole +
                ", status='" + status + '\'' +
                '}';
    }
}
