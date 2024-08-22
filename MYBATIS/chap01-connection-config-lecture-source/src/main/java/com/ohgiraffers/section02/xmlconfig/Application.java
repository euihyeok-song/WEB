package com.ohgiraffers.section02.xmlconfig;

import com.mysql.cj.xdevapi.SessionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.jdbc.SQL;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Application {
    public static void main(String[] args) {

        String resource = "mybatis-config.xml";

        try {
            /* 설명. 설계도를 가져오기 위한 stream 통로*/
            InputStream inputStream = Resources.getResourceAsStream(resource);
            /* 설명. 공장 설계됨 - 여기서 session은 connection 객체를 의미함*/
            SqlSessionFactory sqlSessionFactory
                    = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = sqlSessionFactory.openSession(false);          // 수동 커밋

            /* 설명. 어떤 쿼리를 가지고 한행을 조회할지 먹음 -> 소속 중요(mapper) -> resources/mapper.xml의 namespaces에 들어있음 */
            java.util.Date date = session.selectOne("mapper.selectNow");
            System.out.println("date = " + date);

            session.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
