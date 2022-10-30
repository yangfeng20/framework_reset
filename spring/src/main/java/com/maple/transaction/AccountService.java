package com.maple.transaction;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author 杨锋
 * @date 2022/10/30 14:02
 * desc:
 */


@Service
public class AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void tract(String from, String to, Integer money) {
        Account fromAccount = accountMapper.selectByName(from);
        Account toAccount = accountMapper.selectByName(to);
        if (fromAccount.getBalance() < money) {
            throw new RuntimeException("余额不足");
        }

        fromAccount.setBalance(fromAccount.getBalance() - money);
        toAccount.setBalance(toAccount.getBalance() + money);

        int i = accountMapper.updateById(fromAccount);
        if (accountMapper.updateById(toAccount) + i != 2) {
            throw new RuntimeException("转账失败");
        }
    }
}
