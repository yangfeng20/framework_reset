package com.maple.mybatis.ibatis;

import com.maple.mybatis.ibatis.core.SqlSessionFactory;
import com.maple.mybatis.ibatis.core.SqlSessionFactoryBuild;
import com.maple.mybatis.ibatis.utils.Resources;
import org.junit.Test;

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
    }
}
