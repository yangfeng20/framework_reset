package com.maple.transaction;

import com.maple.transaction.config.SpringTransactionConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 杨锋
 * @date 2022/10/30 13:43
 * desc:
 */

public class TransactionDemo {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringTransactionConfig.class);

        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        accountService.tract("from", "to", 100);
        System.out.println("转账成功");

    }
}
