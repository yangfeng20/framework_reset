package com.maple.mybatis.mvc.dao;

import com.maple.mybatis.mvc.entity.Account;

/**
 * @author 杨锋
 * @date 2022/10/14 13:36
 * desc:
 */

public interface AccountMapper {

    /**
     * 选择名字
     *
     * @param name 名字
     * @return {@link Account}
     */
    public Account selectByName(String name);

    /**
     * 更新平衡
     *
     * @param account 账户
     * @return int
     */
    public int updateBalance(Account account);
}
