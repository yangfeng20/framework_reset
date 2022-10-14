package com.maple.mybatis.mvc.servlet;

import com.maple.mybatis.mvc.service.AccountServiceGenDaoProxy;

/**
 * @author 杨锋
 * @date 2022/10/14 15:33
 * desc:
 */

public class AccountProxyController {
    public static void main(String[] args) {

        AccountServiceGenDaoProxy accountServiceGenDaoProxy = new AccountServiceGenDaoProxy();

        accountServiceGenDaoProxy.transfer("from", "to", null);
    }
}
