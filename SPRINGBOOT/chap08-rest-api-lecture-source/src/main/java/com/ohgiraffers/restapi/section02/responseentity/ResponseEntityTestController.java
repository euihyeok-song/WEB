package com.ohgiraffers.restapi.section02.responseentity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.*;

@RestController
@RequestMapping("/entity")
public class ResponseEntityTestController {

    /* 설명. Service와 Repository를 만들지 않고 이미 이를 통해서 DB에서 가져와 List에 저장했다고 가정 */
    private List<UserDTO> users;

    public ResponseEntityTestController() {
        this.users = new ArrayList<>();

        users.add(new UserDTO(1, "user01", "pass01", "홍길동", new java.util.Date()));
        users.add(new UserDTO(2, "user02", "pass02", "유관순", new java.util.Date()));
        users.add(new UserDTO(1, "user03", "pass03", "이순신", new java.util.Date()));
    }

    /* 설명. 1. 직접 ResponseEntity 객체 만들기 */
    @GetMapping("users")
    public ResponseEntity<ResponseMessage> findAllUsers(){
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application","json"
                , Charset.forName("UTF-8")));

        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("users", users);

        /* 설명. 응답 바디에 응답메시지, 헤더, 상태코드 까지 다 넣어서 보낼 수 있다. */
        ResponseMessage responseMessage = new ResponseMessage(200,"조회성공!", responseMap);

        /* 설명. ResponseEntity(응답바디, 헤더, 상태코드)*/
        return new ResponseEntity<>(responseMessage, headers, HttpStatus.OK);
    }
}
