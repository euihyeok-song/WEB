package com.ohgiraffers.section02.sessionlistener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionAttributeListener;
import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    public SessionListener() {
        System.out.println("session listener 인스턴스 생성!");
    }

    /* 설명. 어떤 Session 객체가 생성되었는지 확인 */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session created!");
        System.out.println("생성 session id: " + se.getSession().getId()); // 어떤 사용자에 의해 생성된 session인지
        /* 설명. 어떤 session의 타입인지 이름을 출력 */
        System.out.println("session 객체 타입: " + se.getSession().getClass().getName());
    }

    /* attribute의 setattribute가 발생하였는지 지켭는 listener 기능 */
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println("session attribute added!");
        System.out.println("session 추가 된 attr: " + event.getName()+ ", " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println("session attribute replaced!");
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println("session attribute removed!");
    }

    /* 설명. session.invalidate()를 시행하기 위한 메소드 */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("session destroyed!");
    }
}
