package com.maple.mybatis.mvc.service;

import com.maple.mybatis.mvc.dao.AccountMapper;
import com.maple.mybatis.mvc.entity.Account;
import com.maple.mybatis.mvc.proxy.GenerateDaoByJavassist;
import com.maple.mybatis.quick.SqlSessionUtil;

/**
 * @author 杨锋
 * @date 2022/10/14 11:44
 * desc:
 */

public class AccountServiceGenDaoProxy {

    private final AccountMapper accountMapper = GenerateDaoByJavassist.getMapper(SqlSessionUtil.getSqlSession(), AccountMapper.class);


    public void transfer(String from, String to, Integer money) {
        Account account = accountMapper.selectByName("from");
        System.out.println(account);
    }


}
