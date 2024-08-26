package com.ohgiraffers.springdatajpa.menu.repository;

import com.ohgiraffers.springdatajpa.menu.aggregate.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/* 설명. JpaRepository(엔티티명, PK 타입) */
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    /* 설명. 1. JPQL을 활용한 카테고리 조회 (권장 O )
        이미 JPARepository 내부에 findAll과 다른 기능으로 만들기 위해서 이름을 바꿔줌
    *   Query를 native Query로 날려줌  */
//    @Query(value = "SELECT c FROM Category c ORDER BY c.categoryCode Asc")
//    List<Category> findAllCategory();

    /* 설명. 2. Native Query를 활용한 카테고리 조회 (권장 X - JPA임에도 불구하고 DBMS의 제약을 받기 때문에)
    *        JPA로 보낼수 없느 쿼리는 이런식으로 Native Query로 날려야 한다.(JPA의 한계) */
    @Query(value="SELECT C.CATEGORY_CODE, C.CATEGORY_NAME, C.REF_CATEGORY_CODE"
    + "FROM TBL_CATEGORY C ORDER BY C.CATEGORY_CODE ASC", nativeQuery = true)
    List<Category> findAllCategory();
}
