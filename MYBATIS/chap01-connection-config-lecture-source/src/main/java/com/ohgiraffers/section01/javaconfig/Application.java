package com.ohgiraffers.section01.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Application {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/menudb";
    private static String user = "root";
    private static String password = "root";

    public static void main(String[] args) {

        /* 설명. 환경 설정
        *   JdbcTransactionFactory: 수동 커밋
        *   ManagedTransactionFactory: 자동 커밋
        *   -----------------------------------------------------
        *   PooledDataSource: ConnectionPool(커넥션이 저장되는 공간) 사용       (+ 요즘은 hikaricp)
        *   UnpooledDataSource: ConnectionPool 미사용
        * */
        Environment environment = new Environment(
                                    "dev",
                                    new JdbcTransactionFactory(),
                                    new PooledDataSource(driver,url,user,password)
                            );

        /* 설명. 환경설정이 포함된 설계도(Builder들이 Factory를 만들기 위한 설계도  - JDBC 연결 (DB 연결)*/
        Configuration configuration = new Configuration(environment);
        /* 설명. Query에 해당하는 설계도인 (Mapper.xml)정보를 설계도에 넣어줘야함 - Query 연결 */
        configuration.addMapper(Mapper.class);
        configuration.addMapper(Mapper.class);
        configuration.addMapper(Mapper.class);

        /* 설명. 위의 설계도로 Builder들이 Factory를 만들고 버려짐 - Factory Pattern */
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        SqlSession session = sqlSessionFactory.openSession(false);    // 수동 커밋을 위한 추가 작업 -> Connection 객체 생성

        /* 설명. session에서 Query 덩어리 하나를 꺼낸 작업(session은 모든 쿼리를 담고 있다) */
        Mapper mapper = session.getMapper(Mapper.class);
        java.util.Date date = mapper.selectNow();                 // Query 하나를 지칭해서 꺼냄
        System.out.println("date = " + date);

        /* 설명. session은 Connection객체이므로 close()가 필요 */
        session.close();

    }
}
