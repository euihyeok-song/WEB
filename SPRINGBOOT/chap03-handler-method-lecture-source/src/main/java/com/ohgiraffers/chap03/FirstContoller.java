package com.ohgiraffers.chap03;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

/* 설명. 현재의 Controller 클래스에 작성할 핸들러 메소드들이 모두 /first/***의 요청을 받게 될 것이다.(보통은 도메인)*/
@Controller
/* 설명. 중복을 줄이기 위해서 /first를 떼어낸다 */
@RequestMapping("/first")
/* 설명. request 영역에 id라는 key값이 있으면 자동으로 session에 저장해준다. */
@SessionAttributes("id")
public class FirstContoller {

    /* 설명. 반환 형이 */
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


    /* 설명. 1. @RequestParam의 속성들
    *   1. defaultValue: 사용자의 입력값이 없거나("") 아니면 request의 parameter 키 값과 일치 하지 않는
                         매개변수 일 때 사용하고 싶은 값을 default 값으로 설정할 수 있다.
     *  2. name: request parameter의 키 값과 다른 매개변수 명을 사용하고 싶을 때 request parameter의
     *           키 값을 작성한다.
     *  (@RequestParam 어노테이션은 생략 가능)
     *
     * 설명. 2. templates의 modify.html에서 화면을 통해서 사용자가 입력한 값을 @RequestParam을 통해서 받아와서 사용 가능
     *       만약 가격 입력값으로 int값이 아닌 "10원"과 같은 문자열이 추가된 형태로 넘어오면 "400번(Client잘못- Bad request)" 오류 발생
     *       예외 상황 정리 문서화 필요 (트러블 슈팅)
     * */
    @PostMapping("modify")
    public String modifymenu(Model model,
//                           @RequestParam(defaultValue = "디폴트", name="name") String modifyName,
                             @RequestParam(name = "name") String modifyName,        // html에서 name으로 지정한 값을 넘김
//                           String name,                                           // @RequestParam은 생력가능
                             @RequestParam int modifyPrice){
//                           @RequestParam(defaultValue = "0") int modifyPrice){
        String message = modifyName + "메뉴의 가격을 " + modifyPrice + "로 변경하였습니다.";
        model.addAttribute("message",message);

        return "first/messagePrinter";
    }

    @PostMapping("modify2")
    public String modifyMenu(Model model, @RequestParam Map<String, String> parameter){
        String modifyName = parameter.get("name2");
        int modifyPrice = Integer.parseInt(parameter.get("modifyPrice2"));

        String message = modifyName + "메뉴의 가격을 " + modifyPrice + "로 변경하였습니다.";
        model.addAttribute("message",message);

        return "first/messagePrinter";
    }

    @GetMapping("search")
    public void search(){}

    /* 설명.
    *   핸들러 메소드의 매개변수에 우리가 작성한 클래스를 스프링이 객체로 만들어 주고 내부적으로
    *   setter를 활용해 값도 주입해 준다.(커멘드 객체 - MenuDTO menu)
    *
    * 설명.
    *  @ModelAttribute 어노테이션에는 Attribute 키 값을 지정할 수 있다.(키 값이 없을 땐 타입을 활용 가능)
     */
    @PostMapping("search")
//    public String searchMenu(Model model, MenuDTO menu){
    public String searchMenu(@ModelAttribute("menu") MenuDTO menu){ // 객체로 묶어주기 + model에 값을 넣어서 return으로 전달해주기
        System.out.println("menu = " + menu);
        return "first/searchResult";
    }

    @GetMapping("login")
    public void login() {}

    @PostMapping("login")
    public String sessionTest1(HttpSession session, @RequestParam String id){ //getSession()으로 session넣고, 입력 id 받아옴
        session.setAttribute("id",id);      // return이 아닌 Session에 결과값을 담음
        return "first/loginResult";

    }

    /* 설명. 로그아웃하면 세션을 만료시키고 직전 페이지로 forward시킴 -> 회원 id가 null이 됨 */
    @GetMapping("logout1")
    public String logoutTest1(HttpSession session){
        session.invalidate();           // 로그아웃하면 세션을 만료

        return "first/loginResult";
    }

    @PostMapping("login2")
    public String sessionTest2(Model model, @RequestParam String id){
        /* 설명. @SessionAttribute를 사용하지 않으면, Model을 사용하므로 session에 담기지 않아서 초반에 출력되지 X
        *       @SessionAttribute("id")로 설정해주면 출력됨. (맨위 Annotation 참고)
        * */
        model.addAttribute("id", id);

        return "first/loginResult";
    }

    /* 설명. @SessionAttribute 방식으로 session에 담긴 값은 SessionStatus에서 제공하는 setComplete으로 만료 시켜야 한다. */
    @GetMapping("logout2")
    public String logoutTest2(SessionStatus sessionStatus){
        sessionStatus.setComplete();            // 세션 만료 시킴

        return "first/loginResult";
    }

    @GetMapping("body")
    public void body(){}

    @PostMapping("body")
    public void body(@RequestBody String body,                               // request body의 정보
                     @RequestHeader("content-type") String contentType,      // request header의 정보
                     @CookieValue(value="JSESSIONID") String sessionId) {
        System.out.println("body = " + body);
        System.out.println("contentType = " + contentType);
        System.out.println("sessionId = " + sessionId);
    }
}
