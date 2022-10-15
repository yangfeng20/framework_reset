package com.maple.mybatis.dynamic.tag;

import com.maple.mybatis.param.entity.Person;
import com.maple.mybatis.param.entity.PersonMapper;
import com.maple.mybatis.quick.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 杨锋
 * @date 2022/10/15 0:09
 * desc:
 */

public class DynamicTagDemo {
    public static void main(String[] args) {


        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
        Person person1 = new Person().setHomeAddress("haha").setName("name");
        Person person2 = new Person().setHomeAddress("haha").setName("name");
        Person person3 = new Person().setHomeAddress("haha").setName("name");
        List<Person> collect = Stream.of(person1, person2, person3).collect(Collectors.toList());
        int count = mapper.insertBatch(collect);
        sqlSession.commit();
        System.out.println(count);
    }
}
