package com.ohgiraffers.section02.initdestory;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();

        for(String beanName: beanNames){
            System.out.println("beanNames = " + beanNames);
        }

        /* 설명. 붕어빵, 딸기 우유, 지리산 암반수 진열 */
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

        /* 설명. ContextConfiguration의 destroyMethod를 확인하기 위해서 컨테이너를 강제 종료하여 Bean도 사라지게 유도*/
        ((AnnotationConfigApplicationContext)context).close();

    }
}
