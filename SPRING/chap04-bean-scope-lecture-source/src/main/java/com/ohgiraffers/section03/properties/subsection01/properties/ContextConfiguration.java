package com.ohgiraffers.section03.properties.subsection01.properties;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import com.ohgiraffers.section02.initdestory.Owner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;


@Configuration
/* 설명. Bean을 만드는 것이 아닌 저장해둔 설정값을 불러옴 -@Value 필요*/
@PropertySource("section03/properties/subsection01/properties/product-info.properties")
public class ContextConfiguration {

    /* 설명.
        1. 리빌딩하지 않게 하기 위해서
    *   2. 보안 취약점(노출)을 줄이기 위해서
    *   3. 비개발자도 수정할 수 있게 하기 위해서 */

    /* 설명. "${}"는 바인딩 변수로 외부 resource의 key값을 적으면 그에 해당하는 value값을 넣어준다. - (product-info.properties내부)
     *       "#{}"는 리터럴 변수는 ?대신에 Mybatis에서 사용된다*/

    @Value("${bread.carpbread.name}")
    private String carpBreadName;

    @Value("${bread.carpbread.price}")
    private int carpBreadPrice;

    @Value("${beverage.milk.name}")
    private String milkName;

    @Value("${beverage.milk.price}")
    private int milkPrice;

    @Value("${beverage.milk.capacity}")
    private int milkCapacity;

    @Value("${beverage.water.name}")
    private String waterName;

    @Value("${beverage.water.price}")
    private int waterPrice;

    @Value("${beverage.milk.capacity}")
    private int waterCapacity;

    @Bean
    public Product carpBread(){
        return new Bread(carpBreadName,carpBreadPrice,new java.util.Date());
    }

    @Bean
    public Product milk(){
        return new Beverage(milkName,milkPrice,milkCapacity);
    }

    @Bean
    public Product water(){
        return new Beverage(waterName,waterPrice,waterCapacity);
    }

    /* 설명. 메소드의 매개변수로도 바로 전달받아 대입 가능 */
//    @Bean
//    public Product water(@Value("${beverage.water.name}") String waterName,
//                         @Value("${beverage.water.price}") int waterPrice,
//                         @Value("${beverage.water.capacity}") int waterCapacity) {
//        return new Beverage(waterName, waterPrice, waterCapacity);
//    }

    @Bean
    @Scope("prototype")
    public ShoppingCart cart(){
        return new ShoppingCart();
    }

    @Bean(initMethod = "openShop", destroyMethod = "closeShop")
    public Owner owner(){
        return new Owner();
    }

}
