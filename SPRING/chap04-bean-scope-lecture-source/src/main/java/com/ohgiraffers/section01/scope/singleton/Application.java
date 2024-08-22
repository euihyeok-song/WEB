package com.ohgiraffers.section01.scope.singleton;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    /* 설명.
    *   bean scope란?
    *    스프링 bean이 인스턴스를 생성하는 시점 및 유지 기간을 설정할 수 있는 Scope 개념이다.
    *    1. singleton: 하나의 인스턴스만을 생성하며 해당 인스턴스가 공유된다.
    *    2. prototype: 매번 bean이 필요로 할 때 새로 인스턴스를 생성한다.
    *    3. request: http 요청을 처리할 때마다 새로운 인스턴스를 생성하며 웹 어플리케이션 컨텍스트에만 해당한다.
    *    4. session: http 세션 당 하나의 인스턴스를 새로 생성하고 세션이 종료되면 인스턴스를 파기한다.
    *                웹 어플리케이션 컨텍스트에만 해당된다.
    *    5. globalSession: 전역 세션 당 하나의 인스턴스를 생성하고 전역 세션이 종료되면 인스턴스를 파기한다.
    *                      포털 애플리케이션 컨텍스트에만 해당된다.
    *  */

    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();

        for(String beanName: beanNames){
            System.out.println("beanNames = " + beanNames);
        }

        /* 설명. 붕어빵, 딸기 우유, 지리산 암반수를 받아 쇼핑카트에 담는다. */
        Product cartBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        /* 설명. 첫 번째 손님이 쇼핑카트를 꺼내 물건을 담는다.*/
        ShoppingCart cart1 = context.getBean("cart",ShoppingCart.class);
        cart1.addItem(cartBread);
        cart1.addItem(milk);

        /* 설명. 첫 번째 손님의 쇼핑 카트에 담긴 물품 확인 */
        System.out.println("cart1에 담긴 물품: " + cart1.getItems());

        /* 설명. 두 번째 손님도 쇼핑카트를 꺼내 물건을 담는다. - 같은 객체(final-singleton)이므로 위의 cart1에 추가되는 형식이다.*/
        ShoppingCart cart2 = context.getBean("cart",ShoppingCart.class);
        cart2.addItem(water);

        System.out.println("cart2에 담긴 물품: " + cart2.getItems());

        /* 설명. True이면 singleton이며 동일한 객체이다. False이면 prototype이며 다른 객체이다. */
        System.out.println(System.identityHashCode(cart1) == System.identityHashCode(cart2));

    }
}
