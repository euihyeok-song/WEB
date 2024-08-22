package com.ohgiraffers.section02.sessionlistener;

import jakarta.servlet.http.HttpSessionBindingEvent;
import jakarta.servlet.http.HttpSessionBindingListener;

//public class UserDTO {

/* 설명. HttpSessionBindingListener는 해당 클래스에 리스너를 추가해야 한다. (객체가 session에 담겼는지 확인) */
public class UserDTO implements HttpSessionBindingListener {
    private String name;
    private int age;

    public UserDTO() {
    }

    public UserDTO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }

    /* 설명. 이 객체가 session에서 사용되고 있으면 발생하는 메소드 */
    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        System.out.println("UserDTO가 담김");
    }

    /* 설명. 이 객체가 session에서 분리되어 나오면 발생하는 메소드 */
    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        System.out.println("UserDTO가 제거됨");
    }
}
