package com.ohgiraffers.section02.initdestory;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/* 설명. ComponentScan이 아닌 설정파일임으로 따로 구분해줄 필연없다 (section01과)*/
@Configuration
public class ContextConfiguration {

    @Bean
    public Product carpBread(){
        return new Bread("붕어빵2",1000,new java.util.Date());
    }

    @Bean
    public Product milk(){
        return new Beverage("딸기우유2",1500,500);
    }

    @Bean
    public Product water(){
        return new Beverage("지리산암반수2",3000,1000);
    }

    @Bean
    /* 설명. Annotation으로 "singleton"으로 할지 "prototype"으로 할지 설정 가능*/
//    @Scope("singleton")        // 스프링 컨테이너(IOC Container)는 bean 객체를 기본적으로 싱글톤으로 관리한다. (생략가능)
    @Scope("prototype")          // Bean scope를 수정하게 되면 bean 객체의 life cycle을 다른 주기로 가져갈 수 있다.
    public ShoppingCart cart(){
        return new ShoppingCart();
    }

    /* 설명. Owner Bean 추가 - Bean의 생성 및 소멸 시점에 자동 호출 될 메소드를 등록할 수 있다.
     *  - Bean 설정을 통해 생성시 openShop호출,
     *  - Container가 사라지고 Bean이 사라질 시 closeShop호출(delay 존재)
     * */
    @Bean(initMethod = "openShop", destroyMethod = "closeShop")
    public Owner owner(){
        return new Owner();
    }

}
