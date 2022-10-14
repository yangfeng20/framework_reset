package com.maple.mybatis.mvc.dao.impl;

import com.maple.mybatis.mvc.dao.AccountMapper;
import com.maple.mybatis.mvc.entity.Account;
import com.maple.mybatis.quick.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author 杨锋
 * @date 2022/10/14 13:36
 * desc:
 */

public class AccountMapperImpl implements AccountMapper {
    @Override
    public Account selectByName(String name) {

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        return sqlSession.selectOne("selectByName", name);
    }

    @Override
    public int updateBalance(Account account) {
        SqlSession sqlSession = SqlSessionUtil.getSqlSession();
        return sqlSession.update("updateBalance", account);
    }
}
