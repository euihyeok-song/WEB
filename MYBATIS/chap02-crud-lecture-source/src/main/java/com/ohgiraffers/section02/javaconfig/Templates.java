package com.ohgiraffers.section02.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

public class Templates {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/menudb";
    private static String user = "root";
    private static String password = "root";

    private static SqlSessionFactory sqlSessionFactory;

    public static SqlSession getSqlSession(){
        if(sqlSessionFactory == null){
            Environment environment =
                    new Environment("dev"
                            , new JdbcTransactionFactory()
                            , new PooledDataSource(driver,url,user,password));

            Configuration configuration = new Configuration(environment);

            /* 설명. MenuMapper = DAO 개념의 interface + Query를 담고있는 Mppaer
            *       구현하는 클래스 필요없이 알아서 만들어줌  */
            configuration.addMapper(MenuMapper.class);

            /* 설명. javaconfig에서는 mybatis-config.xml에 package형식으로 해도 가능하다.*/
//            configuration.addMappers("com.ohgiraffers.section02.javaconfig");
//            configuration.getTypeAliasRegistry().registerAlias("MenuDTO", MenuDTO.class);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }

        return sqlSessionFactory.openSession(false);
    }

}
