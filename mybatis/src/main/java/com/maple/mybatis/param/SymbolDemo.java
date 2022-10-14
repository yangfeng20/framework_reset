package com.maple.mybatis.param;

import com.maple.mybatis.mvc.entity.Account;
import com.maple.mybatis.quick.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author 杨锋
 * @date 2022/10/14 17:05
 * desc: mybatis ${} 和 #{}的区别
 */

public class SymbolDemo {
    public static void main(String[] args) {

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        AccountParamMapper mapper = sqlSession.getMapper(AccountParamMapper.class);
        Account account = mapper.selectList("from");
        System.out.println("account = " + account);

    }
}
