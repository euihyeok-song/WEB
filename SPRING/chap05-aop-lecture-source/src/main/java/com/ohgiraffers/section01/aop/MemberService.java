package com.ohgiraffers.section01.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;

@Service
public class MemberService {

    private final MemberDAO memberDAO;

    /* 설명. 생성자 주입 방식 - final 사용하기 위해서 3가지의 주입 방식 중 생성자 주입 방식 서낵*/
    @Autowired
    public MemberService(MemberDAO memberDAO) {
        this.memberDAO = memberDAO;
    }

    /* 설명. Proxy로 감쌀 대상 */
    public List<MemberDTO> findAllMembers() {
        System.out.println("target -> findAllMembers 실행");
        return memberDAO.selectAllMembers();
    }

    public MemberDTO findMembersBy(int index) {
        System.out.println("traget =>findMemberBy 실행");
        return memberDAO.selectMembersBy(index);
    }
}
