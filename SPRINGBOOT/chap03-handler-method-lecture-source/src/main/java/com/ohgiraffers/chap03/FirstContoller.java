package com.ohgiraffers.chap03;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/* 설명. 현재의 Controller 클래스에 작성할 핸들러 메소드들이 모두 /first/***의 요청을 받게 될 것이다.(보통은 도메인)*/
@Controller
/* 설명. 중복을 줄이기 위해서 /first를 떼어낸다 */
@RequestMapping("/first")
public class FirstContoller {
//    @GetMapping("regist")               // 위의 @RequestMapping 덕분에 /first를 안적어도 OK
//    public String regist(){
//        return "/first/regist";           // templates 파일 내부의 first파일의 regist.html로 가라
//    }

    @GetMapping("regist")
    public void regist(){}                // 반환형이 void일 경우 위를 이처럼 줄여서 쓸 수 있다.

    @PostMapping("regist")
    public String registMenu(HttpServletRequest request, Model model) {
        String name = request.getParameter("name");
        System.out.println("name = " + name);

        int price = Integer.parseInt(request.getParameter("price"));
        int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));

        String message
                    = name + "을(를) 신규 메뉴 목록의 " + categoryCode + "번 카테고리에 "
                    + price +"원으로 등록하였습니다!!";

        /* 설명. forwarding할 대상에 넣어줄 재료를 받아서 저장해주는 기능 */
        model.addAttribute("message",message);

        return "first/messagePrinter";
    }

    @GetMapping("modify")
    public void modify() {}
}
