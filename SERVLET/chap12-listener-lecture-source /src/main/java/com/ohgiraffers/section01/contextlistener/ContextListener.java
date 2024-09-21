package com.ohgiraffers.section01.contextlistener;

import jakarta.servlet.ServletContextAttributeEvent;
import jakarta.servlet.ServletContextAttributeListener;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener, ServletContextAttributeListener {

    public ContextListener() {

        System.out.println("context listener 인스턴스 생성!!");
    }

    /* 설명. 사물함(session)안의 물품을 넣어주는 메소드 (setAttribute()) - attribute가 추가, 생성등 될때마다 호출*/
    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {    // attribute에 대한 event 발생시 정보 저장
        System.out.println("context attribute added!");
        System.out.println("event.getName() = " + event.getName());
    }

    /* 설명. 사물함(session)안의 물품을 바꿔주는 메소드 (value값이 달라지면)*/
    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println("context attribute replaced!");
    }

    /* 설명. 사물함(session)안의 물품을 지워주는 메소드 (removeAttribute 발생시 )*/
    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println("context attribute replaced!");
    }
}
