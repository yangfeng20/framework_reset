package com.maple.mybatis.table.mapping;

import com.maple.mybatis.quick.SqlSessionUtil;
import com.maple.mybatis.table.mapping.entity.Student;
import com.maple.mybatis.table.mapping.entity.StudentMapper;
import org.apache.ibatis.session.SqlSession;

/**
 * @author 杨锋
 * @date 2022/10/16 11:12
 * desc:
 */

public class InterceptorDemo {
    public static void main(String[] args) {

        // 持久化代理对象class
        // System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        Student student = mapper.selectById(1L);
        Student student2 = mapper.selectById(1L);
    }
}
