package com.ohgiraffers.section01.scope.singleton;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/* 설명. Bean을 구성하는 설정파일 */
@Configuration
public class ContextConfiguration {

    @Bean
    public Product carpBread(){
        return new Bread("붕어빵",1000,new java.util.Date());
    }

    @Bean
    public Product milk(){
        return new Beverage("딸기우유",1500,500);
    }

    @Bean
    public Product water(){
        return new Beverage("지리산암반수",3000,1000);
    }

    @Bean
    /* 설명. Annotation으로 "singleton"으로 할지 "prototype"으로 할지 설정 가능*/
//    @Scope("singleton")        // 스프링 컨테이너(IOC Container)는 bean 객체를 기본적으로 싱글톤으로 관리한다. (생략가능)
    @Scope("prototype")          // Bean scope를 수정하게 되면 bean 객체의 life cycle을 다른 주기로 가져갈 수 있다.
    public ShoppingCart cart(){
        return new ShoppingCart();
    }
}
