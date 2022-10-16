package com.maple.mybatis.table.mapping;

import com.maple.mybatis.ibatis.utils.Resources;
import com.maple.mybatis.table.mapping.entity.Student;
import com.maple.mybatis.table.mapping.entity.StudentMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author 杨锋
 * @date 2022/10/16 10:05
 * desc:
 */

public class CacheDemo {

    public static void main(String[] args) {

        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));


        SqlSession sqlSession1 = sessionFactory.openSession();
        StudentMapper mapper1 = sqlSession1.getMapper(StudentMapper.class);
        // 缓存到sqlSession1的一级缓存
        Student student1 = mapper1.selectById(1L);

        SqlSession sqlSession2 = sessionFactory.openSession();
        StudentMapper mapper2 = sqlSession2.getMapper(StudentMapper.class);
        // 缓存到sqlSession2的一级缓存
        Student student2 = mapper2.selectById(1L);

        // sqlSession关闭或者提交，将1级缓存缓存到二级缓存
        //sqlSession1.close();
        sqlSession2.commit();

        // 使用二级缓存
        SqlSession sqlSession3 = sessionFactory.openSession();
        StudentMapper mapper3 = sqlSession3.getMapper(StudentMapper.class);
        Student student3 = mapper3.selectById(1L);


    }
}
