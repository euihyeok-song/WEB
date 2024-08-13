package com.ohgiraffers.chap04;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    /* 설명.
    *   ViewResolver(뷰 리졸버) 인터페이스를 구현한 ThymeleafViewResolver가 현재 처리하게 된다.
    *   접두사(prefix): resources/templates/
    *   접미사(suffix): .html
    *   핸들러 메소드가 반환하는 String 값 위 뒤에 접두사 및 접미사를 붙여 view를 찾게 된다.
    *
    *  설명. 첫 페이지부터 DB를 방문하고 와야될 상황이 많기 때문에 index.html 대신 아래를 사용
    *  */
    @RequestMapping(value={"/","/main"})        // localhost:8080/ 이나 8080/main을 하면 아래가 실행
    public String main(){
        return "main";
    }



}
