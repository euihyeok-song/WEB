package com.ohgiraffers.springmybatis.section01.factorybean;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* 설명. Service 계층부터 만들었기 떄문에 Test를 만들어서 메소드 테스트를 해봐야함 - ctrl + shift + t 누르면 test만들어짐*/
@Service
public class MenuService {

    private final SqlSessionTemplate sqlSession;


    /* 설명. 생성자 주입 방식 사용 */
    @Autowired
    public MenuService(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<MenuDTO> findAllMenusByOrderableStatus(String orderableStatus){
        return sqlSession.getMapper(MenuMapper.class).findAllMenusByOrderableStatus(orderableStatus);
    }
}
