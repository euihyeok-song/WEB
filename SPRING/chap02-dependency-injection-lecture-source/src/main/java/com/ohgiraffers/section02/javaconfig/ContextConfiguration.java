package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.common.Account;
import com.ohgiraffers.common.MemberDTO;
import com.ohgiraffers.common.PersonalAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContextConfiguration {

    /* 설명. 일일이 메소드로 Bean을 만드는 방식 - 메소드의 이름(accountGenerator)이 Bean의 이름으로 생성됨 */
    @Bean
    public Account accountGenerator(){
        return new PersonalAccount(20,"123-45-6789");
    }

    /* 설명. 앞의 4개의 인자들을 5번째의 객체에 넣어라라는 의미 */
    @Bean
    public MemberDTO memberGenerator(PersonalAccount account){

        /* 설명. 1. 생성자 주입*/
//        return new MemberDTO(1,"홍길동", "010-1234-5678",
//                "hong@gmail.com", accountGenerator());

        /* 설명. setter 주입*/
        MemberDTO member = new MemberDTO();
        member.setSequence(1);
        member.setName("홍길동");
        member.setPhone("010-111-2222");
        member.setEmail("hong@gmail.com");
        member.setPersonalAccount(accountGenerator());

        return member;
    }
}
