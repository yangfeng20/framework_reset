package com.maple.mybatis.param;

import com.maple.mybatis.param.entity.Person;
import com.maple.mybatis.param.entity.PersonMapper;
import com.maple.mybatis.quick.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.Date;

/**
 * @author 杨锋
 * @date 2022/10/14 23:44
 * desc:
 */

public class PersonMapperDemo {
    public static void main(String[] args) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);

        Person person = new Person().setName("测试").setCreatedDate(new Date()).setAccountId(20L).setHomeAddress("湖北省武汉市");
        mapper.insertPerson(person);
        System.out.println("person.getId() dbId = " + person.getId());
        //sqlSession.commit();

        Person personDb = mapper.selectOne(1L);
        System.out.println("personDb = " + personDb);
    }
}
