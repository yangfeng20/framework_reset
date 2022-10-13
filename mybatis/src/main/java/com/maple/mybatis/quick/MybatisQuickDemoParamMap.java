package com.maple.mybatis.quick;

import org.apache.ibatis.session.SqlSession;

import java.util.Date;
import java.util.HashMap;

/**
 * @author 杨锋
 * @date 2022/10/13 16:39
 * desc:
 */

public class MybatisQuickDemoParamMap {

    public static void main(String[] args) {


        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        HashMap<String, Object> map = new HashMap<>(10);
        map.put("k1", "测试名");
        map.put("k2", 10);
        map.put("k3", new Date());
        map.put("k4", 20);
        map.put("k5", 1);
        sqlSession.insert("insertStudentMap", map);
        sqlSession.commit();
        sqlSession.close();
    }
}
