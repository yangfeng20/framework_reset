package com.maple.mybatis.mvc.servlet;

import com.maple.mybatis.mvc.service.AccountService;

/**
 * @author 杨锋
 * @date 2022/10/14 13:51
 * desc:
 */

public class AccountController {

    public static void main(String[] args) {
        AccountService accountService = new AccountService();

        accountService.transfer("from", "to", 100);
        System.out.println("\n\n---------------转账成功---------------\n\n");
    }
}
