package com.maple.mybatis.mvc.service;

import com.maple.mybatis.mvc.dao.AccountMapper;
import com.maple.mybatis.mvc.entity.Account;
import com.maple.mybatis.quick.SqlSessionUtil;

/**
 * @author 杨锋
 * @date 2022/10/14 16:07
 * desc:
 */

public class AccountServiceMybatisProxy {

    private static final AccountMapper accountMapper = SqlSessionUtil.getSqlSession().getMapper(AccountMapper.class);


    public static void main(String[] args) {
        Account account = accountMapper.selectByName("from");
        System.out.println(account);
    }
}
