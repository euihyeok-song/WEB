package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.aggregate.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/* 설명. JpaRepository를 상속받은 Repository여서 클릭해보면 여러 기능 사용 가능 ,<Entity명, Entity의 PK>로 지정*/
public interface MenuRepository extends JpaRepository<Menu, Integer> {

    List<Menu> findByMenuPriceGreaterThan(int menuPrice);

    /* 설명. 이부분은 비어있는 거 처럼 보이지만 사실상 많은 기능들이 숨겨져있음 */
//    Menu getById(Integer id);             // 이미 상속을 통해서 들어가있음

}
