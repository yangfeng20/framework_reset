package com.maple.mybatis.annotation;

import com.maple.mybatis.quick.SqlSessionUtil;
import com.maple.mybatis.table.mapping.entity.Student;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author 杨锋
 * @date 2022/10/16 11:40
 * desc:
 */

public class AnnotationDemo {
    public static void main(String[] args) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentAnnotationMapper mapper = sqlSession.getMapper(StudentAnnotationMapper.class);
        List<Student> studentList = mapper.selectAll();
        studentList.forEach(System.out::println);
    }
}
