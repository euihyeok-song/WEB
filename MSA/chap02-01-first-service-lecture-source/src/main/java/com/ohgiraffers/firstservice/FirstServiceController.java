package com.ohgiraffers.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
/* 설명. Gateway를 이용한 통신을 위해 gateway 강의 자료의 application.yml 참고
*       안쓰고 gateway application.yml에 filter를 쓰면 굳이 지정해줄 필요 X
* */
//@RequestMapping("/first-service")
@Slf4j
public class FirstServiceController {

    /* 설명. application.yml파일내의 값이나 os상의 환경변수, springboot의 값등 값을 가져올 수 있는 객체 (@Value와 동일한 역할)
    *       port번호 확인을 위한 Environment 객체 생성자 주입*/
    public Environment env;

    @Autowired
    public FirstServiceController(Environment env) {
        this.env = env;
    }

    @GetMapping("/health")
    public String healthCheck(){

        /* 설명. port번호는 킬떄마다 랜덤으로 배정됨으로 켜질떄마다 본인의 port번호를 주도록 함*/
        /* 설명. Gateway의 lb(로드 밸런싱)을 통해 RoundRobin 방식으로 실행 될 마이크로 서비스의 포트번호 확인 */
        return "I'm OK 포트는 " + env.getProperty("local.server.port");
    }

    @GetMapping("message")
    public String message(@RequestHeader("first-request") String header){       // Postman 사용
        log.info("넘어온 헤더값: {}", header);
        return "First Service Message";
    }

}
