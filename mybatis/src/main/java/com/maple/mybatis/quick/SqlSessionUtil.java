package com.maple.mybatis.quick;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @author 杨锋
 * @date 2022/10/13 16:36
 * desc:
 */

public class SqlSessionUtil {

    private static SqlSessionFactory sqlSessionFactory;


    static{
        // 构建sqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // buildSqlSessionFactory
        try {
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
