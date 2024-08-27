package com.ohgiraffers.restapi.section01.response;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* 설명. 모든 핸들러 메소드에 @ResponseBody를 붙이는 개념 */
@RestController
@RequestMapping("/response")
public class ResponseRestController {

    @GetMapping("hello")        // "/response"도 동일
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping("random")
    public int getRandomNumber(){
        return (int)(Math.random() * 10) + 1;
    }

    @GetMapping("message")
    public Message getMessage(){
        return new Message(200,"메시지를 응답합니다.");
    }

    /* 설명.
    *   RestController에서 반환한 것(자바의 타입들)은 모두 JSON Array형태([]), 또는 JSON object 형태({})로 바뀌어
    *   JSON 문자열로 반환한다.
    *   1. Map 또는 일반 클래스 타입 -> {} 형태
    *   2. ArrayList -> [] 형태*/
    @GetMapping("jsonTest")
    public List<Map<String, Object>> getJsonTest(){
        List<Map<String ,Object>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("test1", new Message(200,"성공1"));
        map.put("test2", new Message(200,"성공2"));

        Map<String , Object> map2 = new HashMap<>();
        map2.put("nextUrl", "http://localhost:8080/hello");

        list.add(map);
        list.add(map2);

        return list;
    }

    /* 설명. 응답해주는 것이 MediaType(그림)일 경우  - 이미지 응답
    *   produces는 response header의 content-type 설정이다. (jpeg에 대한 MIME 타입 설정) */
    @GetMapping(value = "image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getIage() throws IOException {
        /* 설명. 실제로는 build의 static으로 간다. -> 오른쪽 코끼리의 tasks의 build 누르거나 Tomcat켜면 build에 들어감 */
        return getClass().getResourceAsStream("/static/pikachu.jpeg")
                .readAllBytes();
    }

    /* 설명. ResponseEntity를 활용한 응답(section02에서 자세히 다률 예정)
    *       1. StatusCode
    *       2. Header
    *       3. ResonseBody
    *        => 생성자 or builder를 이용 */
    @GetMapping("/entity")
    public ResponseEntity<Message> getEntity(){
        return ResponseEntity.ok(new Message(200, "응답 성공"));
    }
}
