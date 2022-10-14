package com.maple.mybatis.mvc.service;

import com.maple.mybatis.mvc.dao.AccountMapper;
import com.maple.mybatis.mvc.dao.impl.AccountMapperImpl;
import com.maple.mybatis.mvc.entity.Account;
import com.maple.mybatis.quick.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * @author 杨锋
 * @date 2022/10/14 11:44
 * desc:
 */

public class AccountService {

    private final AccountMapper accountMapper = new AccountMapperImpl();


    public void transfer(String from, String to, Integer money) {

        SqlSession sqlSession = SqlSessionUtil.getSqlSession();

        Account fromAccount = accountMapper.selectByName(from);
        if (fromAccount.getBalance() < money) {
            throw new RuntimeException("余额不足");
        }

        Account toAccount = accountMapper.selectByName(to);
        fromAccount.setBalance(fromAccount.getBalance() - money);
        // 更新from
        int count1 = accountMapper.updateBalance(fromAccount);

        String test = null;
        test.toString();

        // 更新to
        toAccount.setBalance(toAccount.getBalance() + money);
        int count2 = accountMapper.updateBalance(toAccount);

        sqlSession.commit();


        if (count2 + count1 != 2) {
            throw new RuntimeException("转账失败，未知错误");
        }
    }


}
