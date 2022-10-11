package com.maple.mybatis.quick;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author 杨锋
 * @date 2022/10/11 10:09
 * desc:
 */

public class MybatisQuickDemo {
    public static void main(String[] args) throws Exception{

        // 构建sqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        // buildSqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        // 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 执行sql
        int count = sqlSession.insert("insertStudent");

        System.out.println("count = " + count);
        // 提交事务
        sqlSession.commit();
    }
}
