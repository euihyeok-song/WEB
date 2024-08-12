package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

/* 설명. Aspect는 Annotation을 새로운 build.gradle의 추가로 통해 라이브러리르 다운받아 명시해줘야한다.
*       시점을 지정해줘서 proxy로 감싸는 느낌
* */
@Aspect
@Component
public class LoggingAspect {

    /* 설명.
    *   타겟 클래스의 메소드에서 어드바이스를 적용할 수 있는 지점들을 조인포인트(joinpoint)라고 한다.
    *   ***포인트 컷(pointcut)은 여러 조인포인트들에 어드바이스(advice)를 적용할 곳을 지정한 것이다.***
    *   해당 조인포인트에서 어드바이스가 동작한다.
    *
    *  설명.
    *   <포인트컷 표현식>
    *   execution([수식어] 리턴타입 [클래스이름], 이름(파라미터))
    *   1. 수식어: public, private등 수식어를 명시(생략 가능)
    *   2. 리턴 타입: 리턴 타입을 명시
    *   3. 클래스 이름(패키지명 포함) 및 메소드 이름: 클래스 이르과 메소드 이름을 명시
    *   4. 파라미터(매개변수): 메소드의 파라미터를 명시
    *   5. "* ": 1개이면서 모든 값이 올 수 있음
    *   6. "..": 0개 이상의 모든 값이 올 수 있음
    *
    *  설명.
    *   execution(public Integer com.ohgiraffers.setion01.advice.*.*(*))
    *   => com.ohgiraffers.section01.advice 패키지에 속해 있는 바로 다음 하위 클래스에 파라미터가 1개인 모든
    *      메소드이자 접근 제어자가 public이고 반환형이 Integer인 경우
    *   execturion(* com.ohgiraffers.section01.advice.anootation..stu*(..))
    *   => com.ohgiraffers.section01.advice 패키지 및 하위 패키지에 속해 있고, 이름이 stu로 시작하는 파라미터가
    *      0개 이상인 모든 메소드이며 접근제어자와 반환형은 상관 없음
    * */

    /* 설명. 이런식으로 Pointcut을 따로 빼서 지정할 지정할 수 있다.*/
    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    public void logPointcut(){}

    /* 설명. 1. Before Advice */
    /* 설명. point cut
        => 실행하여라(반환형 상관X(*)/ com.ohgiraffers.section01.aop의/ 모든(*) Service계층의/ 모든(*) Method(..)에 적용한다.*/
//    @Before("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    @Before("LoggingAspect.logPointcut()")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("Before joinPoint.getTarget(): " + joinPoint.getTarget());
        System.out.println("Before joinPoint.getSignature(): " + joinPoint.getSignature());
        if(joinPoint.getArgs().length > 0){                     // getArgs로 매개변수의 갯수도 뽑을 수 있다.
            System.out.println("Before joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0] );
        }
    }

    /* 설명. 2. After Advice */
    @After("LoggingAspect.logPointcut()")
    public void logAfter(JoinPoint joinPoint){
        System.out.println("After joinPoint.getTarget(): " + joinPoint.getTarget());
        System.out.println("After joinPoint.getSignature(): " + joinPoint.getSignature());
        if(joinPoint.getArgs().length > 0){                     // getArgs로 매개변수의 갯수도 뽑을 수 있다.
            System.out.println("After joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0] );
        }
    }

    /* 설명. 3. AfterReturning Advice */
    @AfterReturning(pointcut="logPointcut()", returning="result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After Returning result: " + result);

        if(result != null && result instanceof List) {
            ((List<MemberDTO>) result).add(new MemberDTO(3L, "반환 값 가공"));
        }
    }

    /* 설명. 4. AfterThrowing Advice */
    @AfterThrowing(pointcut = "logPointcut()", throwing="exception")
    public void logAfterThrowing(Throwable exception){
        System.out.println("AfterThrowing exception: " + exception);
    }

    /* 설명. 5. Around Advice */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("Around Before: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();                // 타겟 메소드 동작
        System.out.println("Around After: " + joinPoint.getSignature().getName());

        return result;
    }

}
