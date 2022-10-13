package com.maple.mybatis.quick;

import org.apache.ibatis.session.SqlSession;

/**
 * @author 杨锋
 * @date 2022/10/13 17:39
 * desc:
 */

public class MybatisQuickDemoNameSpace {
    public static void main(String[] args) {

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();


        sqlSession.insert("test1."+"insertStudentMap");

        sqlSession.commit();
    }
}
