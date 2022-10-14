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

    private final static ThreadLocal<SqlSession> sessionThreadLocal = new ThreadLocal<>();


    static {
        // 构建sqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // buildSqlSessionFactory
        try {
            sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static SqlSession getSqlSession() {
        SqlSession sqlSession = sessionThreadLocal.get();
        if (sqlSession == null) {
            sqlSession = sqlSessionFactory.openSession();
            sessionThreadLocal.set(sqlSession);
        }
        return sqlSession;
    }

    public static void close() {
        if (sessionThreadLocal.get() == null) {
            sessionThreadLocal.get().close();
            sessionThreadLocal.remove();
        }
    }
}
