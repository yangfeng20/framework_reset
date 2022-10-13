package com.maple.mybatis.quick;

import com.maple.mybatis.entity.StudentDO;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/10/13 16:58
 * desc:
 */

public class MybatisQuickDemoParamObject {
    public static void main(String[] args) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();


        //insertObject(sqlSession);

        //selectObject(sqlSession);

        selectObjectAll(sqlSession);

        sqlSession.commit();
        sqlSession.close();
    }

    private static void selectObjectAll(SqlSession sqlSession){
        Object obj = sqlSession.selectList("selectAll");
        System.out.println(obj);
    }

    private static void selectObject(SqlSession sqlSession) {
        StudentDO selectStudent = ((StudentDO) sqlSession.selectOne("selectStudent", 1555774336030315518L));
        System.out.println(selectStudent);
    }

    private static void insertObject(SqlSession sqlSession) {
        StudentDO studentDO = new StudentDO();
        studentDO.setAge(10);
        studentDO.setName("name_test");
        studentDO.setCreatedDate(new Date());
        studentDO.setTest(true);
        sqlSession.insert("com.maple.mybatis.quick.StudentMapper.insertStudentObject", studentDO);
    }
}
