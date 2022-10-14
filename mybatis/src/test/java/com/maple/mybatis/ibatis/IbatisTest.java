package com.maple.mybatis.ibatis;

import com.maple.mybatis.ibatis.core.SqlSession;
import com.maple.mybatis.ibatis.core.SqlSessionFactory;
import com.maple.mybatis.ibatis.core.SqlSessionFactoryBuild;
import com.maple.mybatis.ibatis.utils.Resources;
import com.maple.mybatis.quick.entity.StudentDO;
import org.junit.Test;

import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/10/13 22:39
 * desc:
 */

public class IbatisTest {

    @Test
    public void test(){
        SqlSessionFactoryBuild sqlSessionFactoryBuild = new SqlSessionFactoryBuild();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuild.build(Resources.getResourceAsStream("ibatis-config.xml"));
        System.out.println("sqlSessionFactory = " + sqlSessionFactory);

        SqlSession sqlSession = sqlSessionFactory.openSqlSession();


        StudentDO studentDO = new StudentDO();
        studentDO.setAge(10);
        studentDO.setClassId(20L);
        studentDO.setCreatedDate(new Date());
        studentDO.setTest(false);
        studentDO.setName("test_test");
        int count = sqlSession.insert("com.maple.mybatis.quick.StudentMapper.insertStudentObject", studentDO);
        System.out.println("count = " + count);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testSelect(){
        SqlSessionFactoryBuild sqlSessionFactoryBuild = new SqlSessionFactoryBuild();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuild.build(Resources.getResourceAsStream("ibatis-config.xml"));
        System.out.println("sqlSessionFactory = " + sqlSessionFactory);

        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        Object result = sqlSession.selectOne("com.maple.mybatis.quick.StudentMapper.selectAll", null);
        System.out.println(result);
    }
}
