package com.ohgiraffers.chap05;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Member;

/* 설명. Controller내부의 메소드는 지역적으로 우선순위를 정할 수 있다. */
@Controller
public class ExceptionHandlerController {
    @GetMapping("simple-null")
    public String simpleNullPointerExceptionTest(){

        if(true){       // 일부로 null pointer 예외 상황 만들어보기
            throw new NullPointerException();       // forwarding이 일어나 nullPointer.html로 감
        }

        return "/";         // 예외발생 X면 MainController의 mainRoot()로 forwarding 일어남
    }

    @GetMapping("simple-user")
    public String simpleUserExceptionTest() throws MemberRegistException{
        if(true){        // 일부로 예외 상황 만들어보기
            throw new MemberRegistException("당신 같은 사람은 회원으로 받을 수 없습니다.");
        }
        return "/";
    }

    /* 설명. 이후 핸들러 메소드들은 이 클래스만 해당되는 지역범위 예외처리 설정 */
    @GetMapping("annotation-null")
    public String annotationNullPointerExceptionTest() {
        String str = null;
        System.out.println("str.charAt(0): " + str.charAt(0));

        return "/";
    }

    /* 설명. 집접 Controller에 ExceptionHandler를 통해 우선순의를 올릴 수 있따.*/
    @ExceptionHandler(NullPointerException.class)
    public String nullPointerExceptionHandler(){
        System.out.println("이 Controller에서 NullPointerException 발생 시 여기 오는지 확인");

        return "error/nullPointer";
    }

    @GetMapping("annotation-user")
    public String userExceptionTest() throws MemberRegistException{

        if(true){
            throw new MemberRegistException("당신은 안된다니깐??");
        }

        return "/";
    }

    /* 설명. 예외 처리 핸들러에서도 Model과 발생한 예외를 활용할 수 있다. */
    @ExceptionHandler(MemberRegistException.class)
    public String userExceptionHandler(Model model, MemberRegistException exception){
        model.addAttribute("userException",exception);

        return "error/default";
    }

}
