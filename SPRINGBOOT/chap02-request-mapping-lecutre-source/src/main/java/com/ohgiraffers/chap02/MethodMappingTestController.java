package com.ohgiraffers.chap02;

import org.apache.coyote.Request;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* 설명. (1)Controller안에서 (2)Bean으로 관리되어야지만 @RequestMapping을 사용 가능하다.*/
@Controller
public class MethodMappingTestController {

    /* 설명. 핸들러 메소드(어노테이션을 활용해서 요청 방식 및 경로에 따라 각각 메소드가 작성된다.) */

    /* 설명. GET 요청 뿐 아니라 다른 방식의 요청도 처리됨(경로만 같으면) */
//    @RequestMapping("/menu/regist")
    /* 설명. GET 요청일 경우에만 돌아감 */
    @RequestMapping(value = "/menu/regist", method = RequestMethod.GET)
    public String registMenu(Model model) {         // 전달할 요소가 있으면 Model이라는 객체를 사용하자!!
        System.out.println("/menu/regist 경로의 GET 요청 받아보기");

        /* 설명. forwarding 기능을 지원 (RequestDispatcher(jsp/servlet) -> model(spring))*/
        model.addAttribute("message", "신규 메뉴 등록용 핸들러 메소드 호출하고 응답한 페이지");

        /* 설명.templates 파일에 동일한 이름의 html파일이 존재해야함
         *      templates/mappingResult.html을 반환해줌
         *      핸들러 메소드에서 반환하는 String은 templates 폴더에 잇는 view(html 파일)의 이름이다. */
        return "mappingResult";
    }

    /* 설명. 404 error 경로 실수, 405 error는 방식(GET,POST)을 잘못 선택 - POST방식이므로 GET방식으로 하면 405error 발생 */
//    @RequestMapping(value="/menu/modify", method= RequestMethod.GET)
    public String modifyMenu(Model model) {
        model.addAttribute("message", "POST 방식의 메뉴 수정용 핸들러 메소드 호출함...");

        return "mappingResult";
    }

    @GetMapping("/menu/delete")
    public String getDeleteMethod(Model model) {
        model.addAttribute("message", "GET 방식의 메뉴 삭제용 핸들러 메소드 호출함...");

        return "mappingResult";
    }

    @PostMapping("/menu/delete")
    public String postDeleteMethod(Model model) {
        model.addAttribute("message", "POST 방식의 메뉴 삭제용 핸들러 메소드 호출함...");

        return "mappingResult";
    }
}
