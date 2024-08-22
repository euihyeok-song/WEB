package com.ohgiraffers.section02.column;

import jakarta.persistence.*;

import java.util.Date;

// 객체 만들고 설정 class 태그에 설정하기
@Entity(name="member_section02")
@Table(name="tbl_member_section02")
public class Member {

    @Id
    @Column(name="member_no")
    private int memberNo;

    @Column(name="member_id")
    private String memberId;

    @Column(name="member_pwd")
    private String memberPwd;

    @Column(name="nickname")
    private String nickname;

    // 원하는 형태로 제약조건 설정하기!
    @Column(name="phone", columnDefinition = "varchar(200) default '010-000-0000'")     // lombok 빌더로는 가능, but 이걸로 불가
    private String phone = "010-000-0000";  // 그냥 이렇게도 초기화 가능

    // 유니크 제약 조건다는 법
    @Column(name="email", unique = true)
    private String email;

    // NOT NULL 제약 조건 달기
    @Column(name="address", nullable = false)
    private String address;

    // DBMS마다 다르다. mariadb나 mysql은 datetime으로 된다.
    @Column(name="enroll_date")
//    @Temporal(TemporalType.TIMESTAMP)         // datetime
//    @Temporal(TemporalType.DATE)              // date
    @Temporal(TemporalType.TIME)                // time
    private java.util.Date enrollDate;

    @Column(name="member_role")
    private String memberRole;

    @Column(name="status")
    private String status;

    public Member() {
    }

    public Member(int memberNo, String memberId, String memberPwd, String nickname, String phone, String email, String address, Date enrollDate, String memberRole, String status) {
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

    public String getMemberId() {
        return memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public String getMemberRole() {
        return memberRole;
    }

    public String getStatus() {
        return status;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public void setMemberRole(String memberRole) {
        this.memberRole = memberRole;
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
                ", memberRole='" + memberRole + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}