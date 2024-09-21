package com.ohgiraffers.section02.uses;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class RequestWrapper extends HttpServletRequestWrapper {

    /* 설명. 추상메소드의 개념이라 Wrapper의 기본형이 기본 생성자를 쓰게 만들어둠*/
    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    /* 설명. String은 key값을 의미하며, 'password'라는 키 값이 들어오면 암호화를 하는 우리만의 getParameter 메소드 재정의 */
    @Override
    public String getParameter(String key) {

        String value = "";
        /* 설명. request를 통해 들어오는 key값이 'password'일 겨우 다이제스트(암호화 된 값)을 반환할 문자열로 저장 */
        if("password".equals(key)){
            /* 설명. 암호화하는 객체를 하나 생성 -> encoder()라는 메소드에 평문((explain text) - password를 넣어서 암호화 진행 */
            System.out.println("패스워드 꺼낼 시!");
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            value = passwordEncoder.encode(super.getParameter("password")); // override한 getParameter 사용
        }
        /* 설명. password가 아닌경우는 기존과 동일한 getParameter() 실행 */
        else {
            value = super.getParameter(key);
        }

        return value;
    }
}
