package com.ohgiraffers.section03.annotationconfig.subsection01.java;

import com.ohgiraffers.section01.javaconfig.ContextConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for(String beanName: beanNames){
            System.out.println("beanName = " + beanName);
        }
    }
}
