package com.maple.mybatis.table.mapping;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maple.mybatis.quick.SqlSessionUtil;
import com.maple.mybatis.table.mapping.entity.Student;
import com.maple.mybatis.table.mapping.entity.StudentMapper;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author 杨锋
 * @date 2022/10/16 10:37
 * desc:
 */

public class PageDemo {

    public static void main(String[] args) {
        //myPage();
        pageHelper();
    }

    public static void pageHelper(){

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);


        Integer pageNum = 2;
        Integer pageSize = 2;

        // 开启分页
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        System.out.println("page = " + page);

        List<Student> studentList = studentMapper.selectPageHelper();
        System.out.println("studentList = " + studentList);

        // 获取分页相关数据
        PageInfo<Student> pageInfo = new PageInfo<>(studentList, 5);
        System.out.println("pageInfo = " + pageInfo);
        System.out.println("pageInfo.getList() = " + pageInfo.getList());
    }


    public static void myPage() {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        StudentMapper studentMapper = sqlSession.getMapper(StudentMapper.class);

        Integer pageNum = 1;
        Integer pageSize = 2;
        Integer startIndex = (pageNum - 1) * pageSize;


        List<Student> studentList = studentMapper.selectPage(startIndex, pageSize);
        studentList.forEach(System.out::println);
    }
}
