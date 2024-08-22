package com.ohgiraffers.section01.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/* 설명. 설정용 class임을 설정 */
@Configuration
public class ContextConfiguration {
    /* 설명. Spring container가 켜지는 순간(프로그램 켜짐) 아래의 객체를 자동 생성해줌
    *       이 객체를 member라는 이름의 bean(콩)으로 관리한다. ( bean ID는 유일한 pk값과 같다)
    *       이는 자바 Bean형태의 구현이라고 한다. */
    @Bean(name="member")
    public MemberDTO getMember(){
        return new MemberDTO(1,"user01","pass01","홍길동");
    }
}
