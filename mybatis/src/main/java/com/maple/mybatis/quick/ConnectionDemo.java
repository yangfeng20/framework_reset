package com.maple.mybatis.quick;

import org.apache.ibatis.session.SqlSession;

/**
 * @author 杨锋
 * @date 2022/10/13 19:59
 * desc:
 */

public class ConnectionDemo {
    public static void main(String[] args) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        int count1 = sqlSession.insert("insertStudent");
        sqlSession.commit();
        int count2 = sqlSession.insert("insertStudent");
        sqlSession.commit();
        System.out.println(count1);
        System.out.println(count2);
    }
}
