package com.ohgiraffers.section03.annotationconfig.subsection02.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/* 설명. XML파일(spring-context.xml) 기반으로 Annotation 인지 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new GenericXmlApplicationContext(
                        "section03/annotationconfig/subsection02/xml/spring-context.xml");

        String[] beanNames = context.getBeanDefinitionNames();
        for(String beanName: beanNames){
            System.out.println("beanName = " + beanName);
        }
    }


}
